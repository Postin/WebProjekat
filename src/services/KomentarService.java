package services;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Apartman;
import beans.Komentar;
import beans.Korisnik;
import dao.ApartmanDAO;
import dao.KomentarDAO;

@Path("/komentar")
public class KomentarService {
	
	@Context
    ServletContext ctx;

	 	@PostConstruct
	 	public void init() {
	 		String path = ctx.getRealPath("");
	      
	        if (ctx.getAttribute("komentarDAO") == null) {
	           ctx.setAttribute("komentarDAO", new KomentarDAO(path));
	        }
	        if(ctx.getAttribute("apartmanDAO") == null) {
				ctx.setAttribute("apartmanDAO", new ApartmanDAO(path));
			}
	    }
	 	
	 	
	 //iscitavanje komantaraa	
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	    public Response listaKomentara(@Context HttpServletRequest request) {
	        
		 	Korisnik ulogovan = (Korisnik) request.getSession().getAttribute("loggedUser");
	        KomentarDAO komDAO = (KomentarDAO) ctx.getAttribute("komentarDAO");
	        ApartmanDAO aptDAO = (ApartmanDAO) ctx.getAttribute("apartmanDAO");
	        
	        	if(ulogovan==null) {
	        		return Response.status(403).entity("Forbidden").build();
	        	}
	        	if (ulogovan.getUloga().equals("DOMACIN")) {
	        		
	        		ArrayList<Komentar> sviKomentari = new ArrayList<Komentar>(komDAO.getKomentari().values());
	        		ArrayList<Komentar> komentariDomacina = new ArrayList<>(); //domacin pregleda komentare na njegove apartmane
	        		ArrayList<Apartman> apartmaniDomacina = aptDAO.getApartmentsByHost(ulogovan.getKorisnickoIme());
	        		
	        		for (Komentar c : sviKomentari) {
	        			for(Apartman a : apartmaniDomacina) {
	        				if(a.getId().equals(c.getApartmanId())) {
	        					komentariDomacina.add(c);
	        				}
	        			}
	        		}
	        		return Response.status(200).entity(komentariDomacina).build();
	        	
	        	} else if (ulogovan.getUloga().equals("ADMINISTRATOR")) {
	        		
	        		ArrayList<Komentar> sviKomentari = new ArrayList<Komentar>(komDAO.getKomentari().values());
	        		return Response.status(200).entity(sviKomentari).build();
	        	
	        	} else if(ulogovan.getUloga().equals("GOST")){
	        		
	        		ArrayList<Komentar> komentariGosta = new ArrayList<>();
	        		ArrayList<Komentar> sviKomentari = new ArrayList<Komentar>(komDAO.getKomentari().values());
	        		
	        		for(Komentar c : sviKomentari) {
	        			if(c.isVidljivost()) {
	        				komentariGosta.add(c);
	        			}
	        		}
	        		return Response.status(200).entity(komentariGosta).build();
	        		
	        	} else {
	        		return Response.status(403).entity("Forbidden")	.build();
	        }

	    }
	 
	 
	 
	 //komentari za odredjeni Apartman
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	 @Path("/apt/{aptId}")
	    public Response getCommentsForApt(@PathParam(value = "aptId") Integer aptId) {
	        
	        ArrayList<Komentar> komentariApartmana = new ArrayList<>();

	        KomentarDAO komDAO = (KomentarDAO) ctx.getAttribute("komentarDAO");
	        ArrayList<Komentar> komentari = new ArrayList<Komentar>(komDAO.getKomentari().values());
	        
	        for (Komentar c: komentari) {
	            if(c.getApartmanId().equals(aptId)) {
	            	komentariApartmana.add(c);
	            }
	        }
	        System.out.println(komentariApartmana);
	        return Response.status(200).entity(komentariApartmana).build();
	    }
	 
	 
	 
	 @PUT
	 @Path("hide/{id}")
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	    public Response sakrijKomentar(@PathParam(value = "id") Integer id, @Context HttpServletRequest request) {
	        
		 
		 	Korisnik ulogovan = (Korisnik) request.getSession().getAttribute("loggedUser");
	        KomentarDAO komDAO = (KomentarDAO) ctx.getAttribute("komentarDAO");
	        Komentar komentar = komDAO.findById(id);
	        
	        if (komentar != null && komentar.isVidljivost()) {
	            if (ulogovan.getUloga().equals("DOMACIN")) {
	                komentar.setVidljivost(false);
	                HashMap<Integer, Komentar> komentari = komDAO.getKomentari();
	                komentari.remove(id);
	                komentari.put(id, komentar);
	                komDAO.setkomentari(komentari);
	                String path = ctx.getRealPath("");
	           //     komDAO.saveKomentar(path);
	                ctx.setAttribute("komentarDAO", komDAO);
	                komDAO.saveKomentar(path);
	                return Response.status(200).build();
	          
	            } else {
	                return Response.status(403).entity("Forbidden").build();
	            }
	        } else {
	            return Response.status(400).entity("Bad request").build();
	        }
	    }


}

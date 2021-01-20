package services;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Korisnik;
import beans.Rezervacija;
import beans.Uloga;
import dao.ApartmanDAO;
import dao.KorisnikDAO;
import dao.RezervacijaDAO;

@Path("users")
public class KorisnikService {
	
	//TODO uraditi logout na serveru.
	//TODO prebaciti svuda return u tip Response.
	
	@Context
	ServletContext ctx;
	
	@PostConstruct
	public void init() {
		String path = ctx.getRealPath("");
		//System.out.println(path);
		
		if(ctx.getAttribute("korisnikDAO")==null) {
			ctx.setAttribute("korisnikDAO", new KorisnikDAO(path));
		}
		
		if(ctx.getAttribute("apartmanDAO") == null) {
			ctx.setAttribute("apartmanDAO", new ApartmanDAO(path));
		}
		
		if(ctx.getAttribute("rezervacijaDAO") == null) {
			ctx.setAttribute("rezervacijaDAO", new RezervacijaDAO(path));
		}
	}
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(Korisnik user) {
		
		KorisnikDAO korisnici = (KorisnikDAO)ctx.getAttribute("korisnikDAO");
		Korisnik k = korisnici.findUser(user);
		
		if(k != null) {
			System.out.println("Korisnik je vec zapisan u sistemu!");
			return Response.status(400).build();			
		}
		korisnici.addUser(user);
		
		ctx.setAttribute("korisnikDAO", korisnici);
		
		String contextPath = ctx.getRealPath("");
		korisnici.saveUsers(contextPath);
		System.out.println("Uspesna registracija!");
		return Response.status(200).build();
	}
	
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(Korisnik user, @Context HttpServletRequest request) {
		System.out.println(user.getKorisnickoIme());
		KorisnikDAO korisnici = (KorisnikDAO)ctx.getAttribute("korisnikDAO");
		Korisnik k = korisnici.findUser(user);
		
		if(k == null) {
			System.out.println("Greska, korisnik ne postoji u sistemu!");
			//return null;
			return Response.status(401).build();	
		}
		
		ctx.setAttribute("korisnikDAO", korisnici);
		request.getSession().setAttribute("loggedUser", k);
		System.out.println("Uspesan login!");
		return Response.status(200).build();
		//return Response.status(200).build();	
	}
	
	 @GET
	 @Path("/ulogovan")
	 @Produces(MediaType.APPLICATION_JSON)
	  	public Korisnik currentUser(@Context HttpServletRequest request) {
	        return (Korisnik) request.getSession().getAttribute("loggedUser");
	    }
	
	@GET
	@Path("/uloga/{korisnickoIme}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Korisnik getUloga(@PathParam("korisnickoIme") String korisnickoIme) {
		System.out.println(korisnickoIme);
		KorisnikDAO korisnici = (KorisnikDAO)ctx.getAttribute("korisnikDAO");
		Korisnik k = korisnici.findUser(korisnickoIme);
		if(k == null) {
			System.out.println("Nije nadjen korisnik na osnovu korisnickog imena-a");
			return null;
		}
		return k;
	}
	
	@GET
	@Path("/get_licni_podaci/{korisnickoIme}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Korisnik getLicniPodaci(@PathParam("korisnickoIme") String korisnickoIme) {
		KorisnikDAO korisnici = (KorisnikDAO)ctx.getAttribute("korisnikDAO");
		Korisnik k = korisnici.findUser(korisnickoIme);
		
		return k;
	}
	
	@PUT
	@Path("/put_licni_podaci/{korisnickoIme}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response izmeniLicnePodatke(@PathParam("korisnickoIme") String korisnickoIme, Korisnik user) {
		KorisnikDAO korisnici = (KorisnikDAO)ctx.getAttribute("korisnikDAO");
		Korisnik k = korisnici.findUser(korisnickoIme);
		
		k.setIme(user.getIme());
		k.setPrezime(user.getPrezime());
		k.setLozinka(user.getLozinka());
		
		ctx.setAttribute("korisnikDAO", korisnici);
		return Response.status(200).build();
		
	}
	
	@GET
	@Path("/get_all")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Korisnik> getAllUsers() {
		KorisnikDAO korisnici = (KorisnikDAO)ctx.getAttribute("korisnikDAO");
		
		ArrayList<Korisnik> users = new ArrayList<Korisnik>();
		
		for(Korisnik k :korisnici.getKorisnici().values()) {
			users.add(k);
		}
		
		return users;
	}
	
	@POST
	@Path("/search_users")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON) 
	public ArrayList<Korisnik> searchUsers(Korisnik k) {
		System.out.println("usao u searchUsers");
		KorisnikDAO korisnici = (KorisnikDAO)ctx.getAttribute("korisnikDAO");

		//System.out.println("KORIME: "+ k.getKorisnickoIme() + " IME: " + k.getIme()+ " PRZ: " + k.getPrezime() + " ULOGA: " + k.getUloga());
		ArrayList<Korisnik> users = new ArrayList<Korisnik>();
		for(Korisnik kor : korisnici.getKorisnici().values()) {
			if(kor.getIme().contains(k.getIme()) && kor.getPrezime().contains(k.getPrezime()) &&
					kor.getKorisnickoIme().contains(k.getKorisnickoIme()) && kor.getUloga().contains(k.getUloga())) {
				users.add(kor);
				
				System.out.println(kor.getKorisnickoIme());
			}
		}
		
		return users;
	}
	
	@GET
	@Path("/get_all_gosti/{korisnickoIme}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Korisnik> getAllGosti(@Context HttpServletRequest request, @PathParam("korisnickoIme") String korisnickoIme) {
		ArrayList<Korisnik> gosti = new ArrayList<Korisnik>();
		RezervacijaDAO rezervacije = (RezervacijaDAO)ctx.getAttribute("rezervacijaDAO");
		ApartmanDAO apartmani = (ApartmanDAO)ctx.getAttribute("apartmanDAO");
				
		KorisnikDAO korisnici = (KorisnikDAO)ctx.getAttribute("korisnikDAO");
		HashMap<String,Korisnik> svi = korisnici.getKorisnici();
		
		for(Rezervacija r: rezervacije.getRezervacije().values()) {
			String domacin = apartmani.findApartmanById(r.getApartmanId()).getDomacin(); //nadjem domacina
			
			if(domacin.equals(korisnickoIme)) {
				if(!gosti.contains(korisnici.findUser(korisnickoIme))) {
					gosti.add(korisnici.findUser(r.getGost()));
				}
			}
		}		
		return gosti;		
	}
	
	@POST
	@Path("/search_gosti/{korisnickoIme}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Korisnik> searchGosti(@Context HttpServletRequest request, @PathParam("korisnickoIme") String korisnickoIme, Korisnik user) {
		ArrayList<Korisnik> gosti = getAllGosti(request, korisnickoIme);
		ArrayList<Korisnik> rez = new ArrayList<Korisnik>();
		for(Korisnik k: gosti) {
			if(k.getIme().contains(user.getIme()) && k.getPrezime().contains(user.getPrezime())
					&& k.getKorisnickoIme().contains(user.getKorisnickoIme()) && k.getUloga().contains(user.getUloga())) {
				rez.add(k);
			}
		}
		return rez;
	}
	
	//dodala
	@POST
    @Path("/logout")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void logout(@Context HttpServletRequest request) {
        request.getSession().setAttribute("loggedUser", null);
        request.getSession().invalidate();
    }
}

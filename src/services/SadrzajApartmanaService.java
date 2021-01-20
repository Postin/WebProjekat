package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Apartman;
import beans.Korisnik;
import beans.SadrzajApartmana;
import dao.ApartmanDAO;
import dao.SadrzajApartmanaDAO;

@Path("sadrzaj")
public class SadrzajApartmanaService {

	@Context
	ServletContext ctx;
	
	@PostConstruct
	public void init() {
		String path = ctx.getRealPath("");
		
	
		if(ctx.getAttribute("sadrzajDAO") == null) {
			ctx.setAttribute("sadrzajDAO", new SadrzajApartmanaDAO(path));
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response dodajSadrzaj(@QueryParam("name") String name, @Context HttpServletRequest request) {
		Korisnik ulogovan = (Korisnik) request.getSession().getAttribute("loggedUser");
		if (!ulogovan.getUloga().equals("ADMINISTRATOR") || ulogovan.equals(null)) {
			return Response.status(403).entity("Forbidden").build();
		}
		SadrzajApartmanaDAO saDao = ((SadrzajApartmanaDAO) ctx.getAttribute("sadrzajDAO"));
		HashMap<Integer, SadrzajApartmana> sadrzaji = saDao.getSadrzaji();

		Integer id = 0;
		while (sadrzaji.containsKey(id)) {	
			id = ThreadLocalRandom.current().nextInt(0, 10000);
		}

		SadrzajApartmana sa = new SadrzajApartmana();
		sa.setId(id);
		sa.setNaziv(name);
		sadrzaji.put(id, sa);
		saDao.setSadrzaji(sadrzaji);
		
		ctx.setAttribute("sadrzajDAO", saDao);
		String contextPath = ctx.getRealPath("");
		saDao.saveSadrzaj(contextPath);
		System.out.println("Uspesno dodat sadrzaj!");
		return Response.status(200).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response prikazSadrzaja(@Context HttpServletRequest request) {
		
		ArrayList<SadrzajApartmana> sa = new ArrayList<SadrzajApartmana>();
		SadrzajApartmanaDAO saDao = (SadrzajApartmanaDAO) ctx.getAttribute("sadrzajDAO");
		
		if (saDao.getSadrzaji().values().size() > 0) {
			for (SadrzajApartmana a : saDao.getSadrzaji().values()) {
				if (!a.isObrisan()) {
					sa.add(a);
				}
			}
		}
		return Response.status(200).entity(sa).build();
	}
	
	
	@DELETE
	public Response obrisiSadrzaj(@QueryParam("id") Integer id, @Context HttpServletRequest request) {
		Korisnik ulogovan = (Korisnik) request.getSession().getAttribute("loggedUser");
		if (!ulogovan.getUloga().equals("ADMINISTRATOR")) {
			return Response.status(403).entity("Forbidden").build();
		}
		try {
			SadrzajApartmanaDAO sadrzajDAO = ((SadrzajApartmanaDAO) ctx.getAttribute("sadrzajDAO"));
			SadrzajApartmana a = sadrzajDAO.findById(id);
			
			if (a == null) {
				return Response.status(400).entity("Sadrzaj nije pronadjen").build();
			}
			
			HashMap<Integer, SadrzajApartmana> sadrzaji = sadrzajDAO.getSadrzaji();
			ApartmanDAO aptDao = ((ApartmanDAO) ctx.getAttribute("apartmanDAO"));

			for (Apartman apt : aptDao.getApartmani().values()) {
				for (SadrzajApartmana sa : apt.getSadrzaj()) { // can I get an amen
					if (sa.getId() == id) {
						apt.getSadrzaj().remove(sa);
					}
				}
			}
			
			ctx.setAttribute("apartmanDAO", aptDao);
			String contextPath = ctx.getRealPath("");
			aptDao.saveApartmans(contextPath);
			System.out.println("Uspesno obrisan sadrzaj!");

			a.setObrisan(true);
			sadrzaji.put(id, a);
			sadrzajDAO.setSadrzaji(sadrzaji);
			
			sadrzajDAO.saveSadrzaj(contextPath);
			aptDao.saveApartmans(contextPath);
			
			ctx.setAttribute("apartmanDAO", aptDao);
			ctx.setAttribute("sadrzajDAO", sadrzajDAO);
			return Response.status(200).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Internal server error").build();
		}

	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response izmeniSadrzaj(@QueryParam("id") Integer id, @QueryParam("name") String name,
	        @Context HttpServletRequest request, SadrzajApartmana sadrzaj) {
		Korisnik ulogovan = (Korisnik) request.getSession().getAttribute("loggedUser");
		if (!ulogovan.getUloga().equals("ADMINISTRATOR")) {
			return Response.status(403).entity("Forbidden").build();
		}

		try {
			SadrzajApartmana a = ((SadrzajApartmanaDAO) ctx.getAttribute("sadrzajDAO")).findById(id);
			if (a == null) {
				return Response.status(400).entity("Sadrzaj nije pronadjen").build();
			}
			SadrzajApartmanaDAO sadrzajDAO = ((SadrzajApartmanaDAO) ctx.getAttribute("sadrzajDAO"));
			HashMap<Integer, SadrzajApartmana> mapaSadrzaja = sadrzajDAO.getSadrzaji();

			ApartmanDAO aptDao = ((ApartmanDAO) ctx.getAttribute("apartmanDAO"));

			for (Apartman apt : aptDao.getApartmani().values()) { //TODO Poreklo greske je vrv LocalDate u Apartments
				for (SadrzajApartmana sa : apt.getSadrzaj()) { // can I get an amen
					if (sa.getId() == id) {
						sa.setNaziv(name);
					}
				}
			}
			a.setNaziv(name);
			mapaSadrzaja.put(id, a);
			sadrzajDAO.setSadrzaji(mapaSadrzaja);
			
			String contextPath = ctx.getRealPath("");
			sadrzajDAO.saveSadrzaj(contextPath);
			aptDao.saveApartmans(contextPath);
			
			ctx.setAttribute("apartmanDAO", aptDao);
			ctx.setAttribute("sadrzajDAO", sadrzajDAO);
			return Response.status(200).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Internal server error").build();
		}

	}


	
	
}

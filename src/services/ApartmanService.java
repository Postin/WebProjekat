package services;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import dao.ApartmanDAO;
import dao.KorisnikDAO;
import dao.RezervacijaDAO;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Apartman;
import beans.ApartmanDto;
import beans.Korisnik;
import beans.SadrzajApartmana;
import beans.SlikaDto;
import beans.TipApartmana;
import dao.ApartmanDAO;
import dao.KorisnikDAO;
import dao.RezervacijaDAO;
import dao.SadrzajApartmanaDAO;

@Path("apartmans")
public class ApartmanService {
	
	@Context
	ServletContext ctx;
	
	@PostConstruct
	public void init() {
		String path = ctx.getRealPath("");
		
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
	
	
/*	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addApartman(Apartman apartman) {
		
		ApartmanDAO apartmani = (ApartmanDAO)ctx.getAttribute("apartmanDAO");
		Apartman a = apartmani.findApartman(apartman);
		
		if(a != null) {
			System.out.println("Apartman je vec zapisan u sistemu!");
			return Response.status(400).build();			
		}
		apartmani.addApartman(apartman);
		
		ctx.setAttribute("apartmanDAO", apartmani);
		
		String contextPath = ctx.getRealPath("");
		apartmani.saveApartmans(contextPath);
		System.out.println("Uspesno dodat apartman!");
		return Response.status(200).build();
	} */
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addApartment(@Context HttpServletRequest request, ApartmanDto apt) {
		Korisnik ulogovan = (Korisnik) request.getSession().getAttribute("loggedUser");

		if (ulogovan == null || !ulogovan.getUloga().equals("DOMACIN")) {
			return Response.status(403).entity("Forbidden").build();
		}

		ApartmanDAO aptDao = ((ApartmanDAO) ctx.getAttribute("apartmanDAO"));
		//lista apartmana
		HashMap<Integer, Apartman> apts = aptDao.getApartmani();
		Apartman a = new Apartman();

		a.setDomacin(ulogovan.getUloga());
		Integer id = 0;

		while (apts.containsKey(id)) {
			id = ThreadLocalRandom.current().nextInt(0, 1000);
		}

		ArrayList<SadrzajApartmana> sadrzajiApt = new ArrayList<SadrzajApartmana>();
		SadrzajApartmanaDAO sadrzajDao = ((SadrzajApartmanaDAO) ctx.getAttribute("sadrzajDao"));

		for (Integer id2 : apt.getSadrzaji()) {
			sadrzajiApt.add(sadrzajDao.getSadrzaji().get(id2));
		}

		if (apt.getTip().equals("SOBA")) {
			a.setTip("SOBA");
		} else {
			a.setTip("CEO");
		}

		a.setId(id);
		a.setIme(apt.getIme());
		a.setBrojSoba(apt.getBrojSoba());
		a.setBrojGostiju(apt.getBrojGostiju());
		a.setLokacija(apt.getLokacija());
		a.setSlike(apt.getSlike());
		a.setAktivan(false);
		a.setCenaPoNoci(apt.getCena());
		LocalDate pocetakDatum = LocalDate.parse(apt.getPocetakDatum());
		LocalDate krajDatum = LocalDate.parse(apt.getKrajDatum());

		for (LocalDate date = pocetakDatum; date.isBefore(krajDatum); date = date.plusDays(1)) {
			a.getDatumiZaIzdavanje().add(date);
		}

		a.setSadrzaj(sadrzajiApt);
		a.setSlike(apt.getSlike());
		LocalTime prijava = LocalTime.parse(apt.getVremePrijave());
		a.setVremeZaPrijavu(prijava);
		LocalTime odjava = LocalTime.parse(apt.getVremeOdjave());
		a.setVremeZaOdjavu(odjava);
		apts.put(a.getId(), a);
		aptDao.setApartmani(apts);
		ctx.setAttribute("apartmanDAO", aptDao);
		String contextPath = ctx.getRealPath("");
		aptDao.saveApartmans(contextPath);
		System.out.println("Uspesno dodat apartman!");
		return Response.status(200).build();
	}
	
	@GET
	@Path("/availableDates/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response availableDates(@Context HttpServletRequest request, @PathParam(value = "id") Integer aptId) {
	
		System.out.println("Primljen je zahtev za potraznju dostupnih datuma za apartman sa id:  " + aptId);
	
		Apartman a = ((ApartmanDAO) ctx.getAttribute("apartmanDAO")).findApartmanById(aptId);
		if (a == null) {
			return Response.status(400).entity("Apartman nije pronadjen").build();
		} else {
			return Response.status(200).entity(a.getDostupnostPoDatumima()).build();
		}
	
	}

	@PUT
	@Path("/img")
	public Response setImages(SlikaDto slika) {
		Apartman a = ((ApartmanDAO) ctx.getAttribute("apartmanDAO")).findApartmanById(slika.getAptId());
		a.getSlike().addAll(slika.getSlike());
		
		ApartmanDAO aptDao = ((ApartmanDAO) ctx.getAttribute("apartmanDAO"));
		HashMap<Integer, Apartman> apts = aptDao.getApartmani();
		
		apts.put(a.getId(), a);
		aptDao.setApartmani(apts);
	//	((ApartmanDAO) ctx.getAttribute("apartmanDao")).getApartmani().put(a.getId(), a);
		ctx.setAttribute("apartmanDao", aptDao);
		String contextPath = ctx.getRealPath("");
		aptDao.saveApartmans(contextPath);
		
		return Response.status(200).build();
	}

}



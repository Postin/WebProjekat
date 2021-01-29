package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
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

import beans.Apartman;
import beans.Korisnik;
import beans.Rezervacija;
import dao.ApartmanDAO;
import dao.KorisnikDAO;
import dao.RezervacijaDAO;

@Path("rezervacija")
public class RezervacijaService {
	
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
	
	@GET
	@Path("/get_rezervacije/{korisnickoIme}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Rezervacija> getRezervacije(@PathParam("korisnickoIme") String korisnickoIme) {
		KorisnikDAO korisnici = (KorisnikDAO)ctx.getAttribute("korisnikDAO");
		RezervacijaDAO rezervacije = (RezervacijaDAO)ctx.getAttribute("rezervacijaDAO");
		ApartmanDAO apartmani = (ApartmanDAO)ctx.getAttribute("apartmanDAO");
		Korisnik loggedIn = korisnici.findUser(korisnickoIme);
		
		
		if(loggedIn.getUloga().equals("ADMINISTRATOR")) {			
			ArrayList<Rezervacija> rez = new ArrayList<Rezervacija>();
			for(Rezervacija r: rezervacije.getRezervacije().values()) {
				rez.add(r);
			}
			return rez;
			
		} else if(loggedIn.getUloga().equals("GOST")) {			
			ArrayList<Rezervacija> rez = new ArrayList<Rezervacija>();
			for(Rezervacija r: rezervacije.getRezervacije().values()) {
				if(r.getGost().equals(korisnickoIme)) {
					rez.add(r);
				}
			}
			return rez;
			
			
		} else if(loggedIn.getUloga().equals("DOMACIN")) {
			//TODO domacin vidi sve sve rezervacije gde je on vlasnik apartmana
			ArrayList<Rezervacija> rez = new ArrayList<Rezervacija>();
			for(Rezervacija r: rezervacije.getRezervacije().values()) {
				Apartman a = apartmani.findApartmanById(r.getApartmanId());
				if(a.getDomacin().equals(korisnickoIme)) {
					rez.add(r);
				}
			}
			return rez;
			
		}
		
		return null;
	}
	
	
	@PUT
	@Path("/odustani/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response odustani(@PathParam("id") Integer id) {
		RezervacijaDAO rezervacije = (RezervacijaDAO)ctx.getAttribute("rezervacijaDAO");
		Rezervacija r = rezervacije.findRezervacija(id);
		if(r.getStatus().equals("Kreirana") || r.getStatus().equals("Prihvacena"))
			r.setStatus("Odustanak");
		else {
			return null;
		}

		ctx.setAttribute("rezervacijaDAO", rezervacije);
		String path = ctx.getRealPath("");
		rezervacije.saveRezervacije(path);
		
		return Response.status(200).build();
	}
	
	@PUT
	@Path("/prihvati/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response prihvati(@PathParam("id") Integer id) {
		RezervacijaDAO rezervacije = (RezervacijaDAO)ctx.getAttribute("rezervacijaDAO");
		Rezervacija r = rezervacije.findRezervacija(id);
		if(r.getStatus().equals("Kreirana"))
			r.setStatus("Prihvacena");
		else {
			return null;
		}
		
		ctx.setAttribute("rezervacijaDAO", rezervacije);
		String path = ctx.getRealPath("");
		rezervacije.saveRezervacije(path);
		
		return Response.status(200).build();
	}
	
	@PUT
	@Path("/odbij/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response odbij(@PathParam("id") Integer id) {
		RezervacijaDAO rezervacije = (RezervacijaDAO)ctx.getAttribute("rezervacijaDAO");
		Rezervacija r = rezervacije.findRezervacija(id);
		
		if(r.getStatus().equals("Kreirana") || r.getStatus().equals("Prihvacena"))
			r.setStatus("Odbijena");
		else {
			return null;
		}
		
		ctx.setAttribute("rezervacijaDAO", rezervacije);
		String path = ctx.getRealPath("");
		rezervacije.saveRezervacije(path);
		
		return Response.status(200).build();
	}
	
	@POST
	@Path("/kreiraj/{poruka}/{datum}/{brNocenja}/{gost}/{ime}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response kreiraj(@PathParam("poruka") String poruka,@PathParam("datum") String datum,
			@PathParam("brNocenja") int brNocenja, @PathParam("gost") String gost, @PathParam("ime") String imeApartmana) {
		
		RezervacijaDAO rezervacije = (RezervacijaDAO)ctx.getAttribute("rezervacijaDAO");
		ApartmanDAO apartmani = (ApartmanDAO)ctx.getAttribute("apartmanDAO");
		Apartman a = apartmani.findApartmanByName(imeApartmana);
		int idRezervacije = 1;
		for(Rezervacija r: rezervacije.getRezervacije().values()) {
			idRezervacije+=1;
		}
		Date d1 = null;
		try {
			d1 = new SimpleDateFormat("YYYY-MM-dd").parse(datum);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		int ukupnaCena = a.getCenaPoNoci() * brNocenja;		
		Rezervacija r = new Rezervacija(idRezervacije, a.getId(), d1, brNocenja, ukupnaCena, poruka, gost, "Kreirana");
		rezervacije.addRezervacija(r);
		
		ArrayList<String> datumi = a.getDatumiZaIzdavanje();
		for(String s : datumi) {
			System.out.println("datum:"+s);
		}
		
		if(datumi.contains(datum)) {
			datumi.remove(datum);
			System.out.println("Datum je zauzet.");
			return Response.status(200).entity("Datum je zauzet.").build();
		}
		datumi.add(datum);
		ctx.setAttribute("apartmanDAO", apartmani);
		ctx.setAttribute("rezervacijaDAO", rezervacije);

		return Response.status(200).build();
	}

}

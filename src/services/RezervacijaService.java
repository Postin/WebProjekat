package services;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

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
	
	@POST
	@Path("/get_rezervacija/{korisnickoIme}")
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
				Apartman a = apartmani.findApartman(r.getApartmanId());
				if(a.getDomacin().equals(korisnickoIme)) {
					rez.add(r);
				}
			}
			return rez;
			
		}
		
		return null;
	}

}

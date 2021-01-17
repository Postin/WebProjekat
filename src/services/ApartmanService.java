package services;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import dao.ApartmanDAO;
import dao.KorisnikDAO;
import dao.RezervacijaDAO;

@Path("apartman")
public class ApartmanService {
	
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
	
}

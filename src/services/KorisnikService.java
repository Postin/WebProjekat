package services;

import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Korisnik;
import dao.KorisnikDAO;

@Path("users")
public class KorisnikService {
	
	@Context
	ServletContext ctx;
	
	@PostConstruct
	public void init() {
		if(ctx.getAttribute("korisnikDAO")==null) {
			ctx.setAttribute("korisnikDAO", new KorisnikDAO());
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
		
		user.setId(UUID.randomUUID());
		korisnici.addUser(user);
		
		ctx.setAttribute("korisnikDAO", korisnici);
		System.out.println("Uspesna registracija!");
		return Response.status(200).build();
		
	}
	
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(Korisnik user, @Context HttpServletRequest request) {
		
		KorisnikDAO korisnici = (KorisnikDAO)ctx.getAttribute("korisnikDAO");
		Korisnik k = korisnici.findUser(user);
		
		if(k == null) {
			System.out.println("Greska, korisnik ne postoji u sistemu!");
			return Response.status(401).build();	
		}
		
		
		ctx.setAttribute("korisnikDAO", korisnici);
		request.getSession().setAttribute("user", user);
		System.out.println("Uspesan login!");
		return Response.status(200).build();		
	}
	
}

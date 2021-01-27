package services;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import dao.ApartmanDAO;
import dao.KorisnikDAO;
import dao.RezervacijaDAO;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
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
import beans.ApartmanDto;
import beans.ApartmanZaDomacinaDto;
import beans.Korisnik;
import beans.Lokacija;
import beans.PretragaDto;
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
		
		if(ctx.getAttribute("sadrzajDAO") == null) {
			ctx.setAttribute("sadrzajDAO", new SadrzajApartmanaDAO(path));
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
	@Path("/add")
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
		SadrzajApartmanaDAO sadrDao = ((SadrzajApartmanaDAO) ctx.getAttribute("sadrzajDAO"));
		
		ArrayList<SadrzajApartmana> sadrzajiAptBaza = new ArrayList<SadrzajApartmana>();
		sadrzajiAptBaza.addAll(sadrDao.getSadrzaji().values());

		for (Integer id2 : apt.getSadrzaji()) {
			System.out.println(apt.getSadrzaji());
			
			sadrzajiApt.add(sadrzajiAptBaza.get(id2));
		}

		if (apt.getTip().equals("SOBA")) {
			a.setTip("SOBA");
		} else {
			a.setTip("CEO");
		}

		a.setDomacin(ulogovan.getKorisnickoIme());
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
			String datum = date.toString();
			a.getDatumiZaIzdavanje().add(datum);
			a.getDostupnostPoDatumima().add(datum);
		}

		a.setSadrzaj(sadrzajiApt);
		a.setSlike(apt.getSlike());
	//	LocalTime prijava = LocalTime.parse(apt.getVremePrijave());
		a.setVremeZaPrijavu(apt.getVremePrijave());
	//	LocalTime odjava = LocalTime.parse(apt.getVremeOdjave());
		a.setVremeZaOdjavu(apt.getVremeOdjave());
		apts.put(a.getId(), a);
		aptDao.setApartmani(apts);
		ctx.setAttribute("apartmanDAO", aptDao);
		String contextPath = ctx.getRealPath("");
		aptDao.saveApartmans(contextPath);
		System.out.println("Uspesno dodat apartman!");
		return Response.status(200).build();
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
	
	
	//listaApartmana po odgovarajucim uslovima
	@GET
	@Path("/listApartmans")
	@Produces(MediaType.APPLICATION_JSON)
	public Response vratiApartmane(@Context HttpServletRequest request) {
		try {
			Korisnik ulogovan = (Korisnik) request.getSession().getAttribute("loggedUser");
			if (ulogovan == null) {
				System.out.println("Neulogovani i neregistrovani korisnici");
				return Response.status(200).entity(((ApartmanDAO) ctx.getAttribute("apartmanDAO")).getActiveApartments())
						.build();
			} else {
				if (ulogovan.getUloga().equals("GOST"))
					return Response.status(200)
							.entity(((ApartmanDAO) ctx.getAttribute("apartmanDAO")).getActiveApartments()).build();
				else if (ulogovan.getUloga().equals("DOMACIN")) {
					
					return Response.status(200).entity(
							((ApartmanDAO) ctx.getAttribute("apartmanDAO")).getApartmentsByHost(ulogovan.getKorisnickoIme()))
							.build();
				} else if (ulogovan.getUloga().equals("ADMINISTRATOR")) {
					ArrayList<Apartman> sviApartmani = new ArrayList<Apartman>(
							((ApartmanDAO) ctx.getAttribute("apartmanDAO")).getApartmani().values());
					return Response.status(200).entity(sviApartmani).build();
				}
			}

		} catch (NullPointerException e) {
			e.printStackTrace();
			;
			return Response.status(400).entity("Error occured").build();
		}
		return Response.status(400).entity("Idk").build();

	}
	
	
	  //izmena atributa Apartmana
	  
	  @PUT // @Path("/aaa/{id}")
	  @Path("/izmeni/{id}")
	  @Produces(MediaType.APPLICATION_JSON)
	  @Consumes(MediaType.APPLICATION_JSON)
	  public Response izmeniApartman(ApartmanZaDomacinaDto dto, @PathParam("id") Integer id, @Context HttpServletRequest request) {
		  Korisnik ulogovan = (Korisnik)request.getSession().getAttribute("loggedUser");
		  if(ulogovan.getUloga().equals("GOST")) {
			  return Response.status(403).entity("Forbidden").build();
		  }
	  
	  System.out.println("Zahtev za izmenu podataka apartmana: ");
	  System.out.println(dto.getIme());
	  
	  
	  ApartmanDAO aptDao = ((ApartmanDAO) ctx.getAttribute("apartmanDAO"));
	  HashMap<Integer, Apartman> apts = aptDao.getApartmani();
	  Apartman a = apts.get(id);
	  
	  
	  if (a != null) {
		  a.setIme(dto.getIme());
		  a.setBrojSoba(dto.getBrojSoba());
		  System.out.println(a.getBrojSoba());
		  a.setBrojGostiju(dto.getBrojGostiju());
		  a.setLokacija(dto.getLokacija()); 
		  //a.setSlike(dto.getSlike());
		  a.setAktivan(dto.isAktivan());
		  
		  a.setCenaPoNoci(dto.getCenaPoNoci());
		  a.setTip(dto.getTip());
		  apts.put(id, a);
		  aptDao.setApartmani(apts);
		  String path = ctx.getRealPath("");
		  aptDao.saveApartmans(path);
		  ctx.setAttribute("apartmanDAO", aptDao);
		  System.out.println(a.getBrojSoba());
		  System.out.println(dto.isAktivan());
		  
		  System.out.println("Uspesno izmenjen apartman!");
		  return Response.status(200).build();
	  }
	  
	  else {
		  
		  System.err.println("error occured on update");
		  return Response.status(400).entity("Apartman nije pronadjen!").build(); 
	  }
	  
  }
	 
	
	//brisanje apartmana
	@DELETE
	@Path("/{id}")
	public Response obrisiApartman(@PathParam("id") Integer id, @Context HttpServletRequest request) {
		 System.out.println("Usao u metodu brisanja ");
		
		Korisnik ulogovan = (Korisnik) request.getSession().getAttribute("loggedUser");
		if (!ulogovan.getUloga().equals("DOMACIN") && !ulogovan.getUloga().equals("ADMINISTRATOR")) {
			return Response.status(403).entity("Forbidden").build();
		}
		try {
			ApartmanDAO aptDao = ((ApartmanDAO) ctx.getAttribute("apartmanDAO"));
			Apartman a = aptDao.findApartmanById(id);
			HashMap<Integer, Apartman> apts = aptDao.getApartmani();

			if (a != null) {
				if (!a.isObrisan()) {
					a.setObrisan(true);
					apts.put(id, a);
					aptDao.setApartmani(apts);
					String path = ctx.getRealPath("");
					aptDao.saveApartmans(path);
					ctx.setAttribute("apartmanDAO", aptDao);
					return Response.status(200).entity("Apartman je obrisan!").build();
				}
				return Response.status(400).entity("Apartman je VEC obrisan!").build();
			}

			else {
				System.err.println("error occured on delete");
				return Response.status(400).entity("Apartman nije pronadjen!").build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Internal server error").build();
		}

	}
	
//----------------------------------------	PRETRAGAAA   ---------------------------------------------
	
	//listaApartmana za pretragu
		@GET
		@Path("/listaZaPretragu")
		@Produces(MediaType.APPLICATION_JSON)
		public Response vratiApartmaneZaPretragu(@Context HttpServletRequest request) {
			try {
				Korisnik ulogovan = (Korisnik) request.getSession().getAttribute("loggedUser");
				if (ulogovan == null || ulogovan.getUloga().equals("GOST") || ulogovan.getUloga().equals("DOMACIN") ) {
					System.out.println("Vrati aktivne apartmane");
					return Response.status(200).entity(((ApartmanDAO) ctx.getAttribute("apartmanDAO")).getActiveApartments()).build();
				
				}else if (ulogovan.getUloga().equals("ADMINISTRATOR")) {
						ArrayList<Apartman> sviApartmani = new ArrayList<Apartman>(
								((ApartmanDAO) ctx.getAttribute("apartmanDAO")).getApartmaniList());
						return Response.status(200).entity(sviApartmani).build();
					}
				
			} catch (NullPointerException e) {
				e.printStackTrace();
				;
				return Response.status(400).entity("Error occured").build();
			}
			return Response.status(400).entity("Idk").build();

		}
		

	//proveri da li su zadati datumi dostupni
	public boolean proveriDostupnost(String startDate, String endDate, Apartman a1) {

		if (a1.getDostupnostPoDatumima().size() == 0)
			return false;

		long daysBetween = ChronoUnit.DAYS.between(LocalDate.parse(startDate), LocalDate.parse(endDate));
			// System.out.println(daysBetween);

		for (int i = 0; i < daysBetween; i++) {
			// System.out.println(LocalDate.parse(startDate).plusDays(i));
			LocalDate datum = LocalDate.parse(startDate).plusDays(i);
			String datum2=datum.toString();
			if (!a1.getDostupnostPoDatumima().contains(datum2)) {
				return false;
			}
		}

			   return true;
	}

	//dostupni Datumi za odredjeni apartman
	@GET
	@Path("/dostupnostDatuma/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response dostupniDatumi(@Context HttpServletRequest request, @PathParam(value = "id") Integer aptId) {

		System.out.println("Primljen je zahtev za potraznju dostupnih datuma za apartman sa id:  " + aptId);

		Apartman a = ((ApartmanDAO) ctx.getAttribute("apartmanDAO")).findApartmanById(aptId);
			if (a == null) {
				return Response.status(400).entity("Apartman nije pronadjen").build();
			} else {
				return Response.status(200).entity(a.getDostupnostPoDatumima()).build();
			}

}

	@POST
	@Path("/pretraga")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response search(@Context HttpServletRequest request, PretragaDto pretragaDto) {
		System.out.println(pretragaDto);

		Korisnik ulogovan = (Korisnik) request.getSession().getAttribute("loggedUser");
		if (ulogovan == null || ulogovan.getUloga().equals("GOST") || ulogovan.getUloga().equals("DOMACIN") ) {
			System.out.println("Vrati aktivne apartmane");
			ArrayList<Apartman> sviApartmani = new ArrayList<Apartman>(((ApartmanDAO) ctx.getAttribute("apartmanDAO")).getActiveApartments());
			
			
			
			//----------------------- PRETRAGA PO DATUMU -------------------------------------

			ArrayList<Apartman> pretragaDatum = new ArrayList<>();

			if (pretragaDto.getDatumDolaska() != null && pretragaDto.getDatumDolaska() != "" && pretragaDto.getDatumOdlaska() != null
					&& pretragaDto.getDatumOdlaska() != "")
				for (Apartman a : sviApartmani) {
					if (proveriDostupnost(pretragaDto.getDatumDolaska(), pretragaDto.getDatumOdlaska(), a)) {
						pretragaDatum.add(a);
						System.out.println("Dostupni su datumi u apartmanu: " + a.getIme());
					}
				}
			else
				pretragaDatum = new ArrayList<Apartman>(sviApartmani);
			
			
			
			//------------------------ PRETRAGA PO CENI ---------------------------------------

			ArrayList<Apartman> pretragaCena = new ArrayList<>();

			if (pretragaDto.getCenaOd() != 0 && pretragaDto.getCenaDo() != 0) {
				for (Apartman a : pretragaDatum) {
					if (a.getCenaPoNoci() != 0) {
						if (a.getCenaPoNoci() > pretragaDto.getCenaOd() && a.getCenaPoNoci() <= pretragaDto.getCenaDo()) {
							pretragaCena.add(a);
						}
					}
				}
			} else if (pretragaDto.getCenaOd() != 0) {
				for (Apartman a : pretragaDatum) {
					if (a.getCenaPoNoci() != 0) {
						if (a.getCenaPoNoci() >= pretragaDto.getCenaOd()) {
							pretragaCena.add(a);
						}
					}
				}
			} else if (pretragaDto.getCenaDo() != 0) {
				for (Apartman a : pretragaDatum) {
					if (a.getCenaPoNoci() != 0) {
						if (a.getCenaPoNoci() <= pretragaDto.getCenaDo()) {
							pretragaCena.add(a);
						}
					}
				}
			} else {
				pretragaCena = pretragaDatum;
			}
			
			
			
			//--------------------------- PRETRAGA PO BROJU SOBA ------------------------------------------------------

			ArrayList<Apartman> pretragaSoba = new ArrayList<>();

			if (pretragaDto.getBrojSobaOd() != 0 && pretragaDto.getBrojSobaDo() != 0) {
				for (Apartman a : pretragaCena) {
					if (a.getBrojSoba() != 0) {
						if (a.getBrojSoba() > pretragaDto.getBrojSobaOd() && a.getBrojSoba() <= pretragaDto.getBrojSobaDo()) {
							pretragaSoba.add(a);
						}
					}
				}
			} else if (pretragaDto.getBrojSobaOd() != 0) {
				for (Apartman a : pretragaCena) {
					if (a.getBrojSoba() != 0) {
						if (a.getBrojSoba() >= pretragaDto.getBrojSobaOd()) {
							pretragaSoba.add(a);
						}
					}
				}
			} else if (pretragaDto.getBrojSobaDo() != 0) {
				for (Apartman a : pretragaCena) {
					if (a.getBrojSoba() != 0) {
						if (a.getBrojSoba() <= pretragaDto.getBrojSobaDo()) {
							pretragaSoba.add(a);
						}
					}
				}
			} else {
				pretragaSoba = pretragaCena;
			}
			
			
			
			//-------------------- PRETRAGA PO BROJU OSOBA -------------------------------------------------------------

			ArrayList<Apartman> pretragaOsobe = new ArrayList<>();

			if (pretragaDto.getBrojOsoba() != 0) {
				for (Apartman a : pretragaSoba) {
					if (a.getBrojGostiju() != 0) {
						if (a.getBrojGostiju() == pretragaDto.getBrojOsoba())
							pretragaOsobe.add(a);
					}
				}
			} else {
				pretragaOsobe = pretragaSoba;
			}
			
			
			
			//-------------------- PRETRAGA PO LOKACIJI -------------------------------------------------------------

			ArrayList<Apartman> pretragaLokacije = new ArrayList<>();

			if (pretragaDto.getMesto() != null && pretragaDto.getMesto() != "") {
				for (Apartman a : pretragaOsobe) {
					if (a.getLokacija().getAdresa().getMesto() != null) {
						if (a.getLokacija().getAdresa().getMesto() == pretragaDto.getMesto())
							pretragaLokacije.add(a);
					}
				}
			} else {
				pretragaLokacije = pretragaOsobe;
			}

			return Response.status(200).entity(pretragaLokacije).build();
		
		
		}else  {
				ArrayList<Apartman> sviApartmani2 = new ArrayList<Apartman>(((ApartmanDAO) ctx.getAttribute("apartmanDAO")).getApartmaniList());
				
				//----------------------- PRETRAGA PO DATUMU -------------------------------------

				ArrayList<Apartman> pretragaDatum = new ArrayList<>();

				if (pretragaDto.getDatumDolaska() != null && pretragaDto.getDatumDolaska() != "" && pretragaDto.getDatumOdlaska() != null
						&& pretragaDto.getDatumOdlaska() != "")
					for (Apartman a : sviApartmani2) {
						if (proveriDostupnost(pretragaDto.getDatumDolaska(), pretragaDto.getDatumOdlaska(), a)) {
							pretragaDatum.add(a);
							System.out.println("Dostupni su datumi u apartmanu: " + a.getIme());
						}
					}
				else
					pretragaDatum = new ArrayList<Apartman>(sviApartmani2);
				
				
				
				//------------------------ PRETRAGA PO CENI ---------------------------------------

				ArrayList<Apartman> pretragaCena = new ArrayList<>();

				if (pretragaDto.getCenaOd() != 0 && pretragaDto.getCenaDo() != 0) {
					for (Apartman a : pretragaDatum) {
						if (a.getCenaPoNoci() != 0) {
							if (a.getCenaPoNoci() > pretragaDto.getCenaOd() && a.getCenaPoNoci() <= pretragaDto.getCenaDo()) {
								pretragaCena.add(a);
							}
						}
					}
				} else if (pretragaDto.getCenaOd() != 0) {
					for (Apartman a : pretragaDatum) {
						if (a.getCenaPoNoci() != 0) {
							if (a.getCenaPoNoci() >= pretragaDto.getCenaOd()) {
								pretragaCena.add(a);
							}
						}
					}
				} else if (pretragaDto.getCenaDo() != 0) {
					for (Apartman a : pretragaDatum) {
						if (a.getCenaPoNoci() != 0) {
							if (a.getCenaPoNoci() <= pretragaDto.getCenaDo()) {
								pretragaCena.add(a);
							}
						}
					}
				} else {
					pretragaCena = pretragaDatum;
				}
				
				
				
				//--------------------------- PRETRAGA PO BROJU SOBA ------------------------------------------------------

				ArrayList<Apartman> pretragaSoba = new ArrayList<>();

				if (pretragaDto.getBrojSobaOd() != 0 && pretragaDto.getBrojSobaDo() != 0) {
					for (Apartman a : pretragaCena) {
						if (a.getBrojSoba() != 0) {
							if (a.getBrojSoba() > pretragaDto.getBrojSobaOd() && a.getBrojSoba() <= pretragaDto.getBrojSobaDo()) {
								pretragaSoba.add(a);
							}
						}
					}
				} else if (pretragaDto.getBrojSobaOd() != 0) {
					for (Apartman a : pretragaCena) {
						if (a.getBrojSoba() != 0) {
							if (a.getBrojSoba() >= pretragaDto.getBrojSobaOd()) {
								pretragaSoba.add(a);
							}
						}
					}
				} else if (pretragaDto.getBrojSobaDo() != 0) {
					for (Apartman a : pretragaCena) {
						if (a.getBrojSoba() != 0) {
							if (a.getBrojSoba() <= pretragaDto.getBrojSobaDo()) {
								pretragaSoba.add(a);
							}
						}
					}
				} else {
					pretragaSoba = pretragaCena;
				}
				
				
				
				//-------------------- PRETRAGA PO BROJU OSOBA -------------------------------------------------------------

				ArrayList<Apartman> pretragaOsobe = new ArrayList<>();

				if (pretragaDto.getBrojOsoba() != 0) {
					for (Apartman a : pretragaSoba) {
						if (a.getBrojGostiju() != 0) {
							if (a.getBrojGostiju() == pretragaDto.getBrojOsoba())
								pretragaOsobe.add(a);
						}
					}
				} else {
					pretragaOsobe = pretragaSoba;
				}
				
				
				
				//-------------------- PRETRAGA PO LOKACIJI -------------------------------------------------------------

				ArrayList<Apartman> pretragaLokacije = new ArrayList<>();

				if (pretragaDto.getMesto() != null && pretragaDto.getMesto() != "") {
					for (Apartman a : pretragaOsobe) {
						if (a.getLokacija().getAdresa().getMesto() != null) {
							if (a.getLokacija().getAdresa().getMesto() == pretragaDto.getMesto())
								pretragaLokacije.add(a);
						}
					}
				} else {
					pretragaLokacije = pretragaOsobe;
				}

				return Response.status(200).entity(pretragaLokacije).build();
			}
		
	}

}

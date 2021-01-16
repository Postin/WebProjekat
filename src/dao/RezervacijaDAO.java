package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import beans.Apartman;
import beans.Korisnik;
import beans.Rezervacija;
import beans.StatusRezervacije;

public class RezervacijaDAO {
	private HashMap<Integer, Rezervacija> rezervacije = new HashMap<Integer,Rezervacija>();
	
	public RezervacijaDAO() {
		UUID id1 = UUID.randomUUID();
		UUID id2 = UUID.randomUUID();
		
		
		//rezervacije.put(id1, new Rezervacija(id1,a1,null,3,6000, "Dobar Dan!",gost1, StatusRezervacije.KREIRANA));
		//rezervacije.put(id2, new Rezervacija(id2,a2,null,15,30000,"Postovani!",gost2,StatusRezervacije.PRIHVACENA));
	}
	
	public Rezervacija findRezervacija(Rezervacija rez) {
		for(Rezervacija r: rezervacije.values()) {
			if(r.getId().equals(rez.getId())) {
				return r;
			}
		}
		return null;
	}
	
	public void addRezervacija(Rezervacija rez) {
		for(Rezervacija r: rezervacije.values()) {
			if(r.getId().equals(rez.getId())) {
				return;
			}
		}
		rezervacije.put(rez.getId(), rez);
	}
	
	public void deleteRezervacija(Rezervacija rez) {
		rezervacije.remove(rez.getId(), rez);
	}

	public HashMap<Integer, Rezervacija> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(HashMap<Integer, Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}

	public Rezervacija findRezervacija(Integer id) {
		Rezervacija r = rezervacije.get(id);
		return r;
	}
}

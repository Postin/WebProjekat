package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import beans.Korisnik;
import beans.Pol;
import beans.Uloga;

public class KorisnikDAO {
	private HashMap<UUID, Korisnik> korisnici = new HashMap<UUID,Korisnik>();;
	
	public KorisnikDAO() {
		// TODO Auto-generated constructor stub
		UUID id1 = UUID.randomUUID();
		UUID id2 = UUID.randomUUID();
		UUID id3 = UUID.randomUUID();
		UUID id4 = UUID.randomUUID();
		UUID id5 = UUID.randomUUID();
		
		korisnici.put(id1, new Korisnik(id1,"gost","gost","Filip","Vozarevic",Pol.MUSKO,Uloga.GOST, null, null, null ));
		korisnici.put(id2, new Korisnik(id2,"admin","admin","Natasa","Mitrovic",Pol.ZENSKO,Uloga.ADMINISTRATOR, null, null, null));
		korisnici.put(id3, new Korisnik(id3, "domacin", "domacin", "Filip", "Vozarevic", Pol.MUSKO, Uloga.DOMACIN, null, null, null));
		korisnici.put(id4, new Korisnik(id4, "domacin2", "domacin2", "Natasa", "Mitrovic", Pol.ZENSKO, Uloga.DOMACIN, null, null, null));
		korisnici.put(id5, new Korisnik(id5,"gost2","gost2","Pera","Peric",Pol.MUSKO,Uloga.GOST, null, null, null));
	}
	
	public Korisnik findUser(Korisnik user) {
		for(Korisnik k: korisnici.values()) {
			if(user.getKorisnickoIme().equals(k.getKorisnickoIme()) &&
					user.getLozinka().equals(k.getLozinka())) {
				return k;
			}
		}
		
		return null;
	}
	
	public void addUser(Korisnik user) {
		for(Korisnik k: korisnici.values()) {
			if(user.getKorisnickoIme().equals(k)) {
				return;
			}
		}
		korisnici.put(user.getId(), user);
	}
	
	public void deleteUser(Korisnik user) {
		korisnici.remove(user.getId(), user);
	}

	public HashMap<UUID, Korisnik> getKorisnici() {
		return korisnici;
	}
	
	public void setKorisnici(HashMap<UUID, Korisnik> korisnici) {
		this.korisnici = korisnici;
	}
	
	public ArrayList<Korisnik> getDomacini() {
		ArrayList<Korisnik> domacini = new ArrayList<Korisnik>();
		for(Korisnik k: korisnici.values()) {
			if(k.getUloga().equals(Uloga.DOMACIN)) {
				domacini.add(k);
			}
		}
		return domacini;
	}
	
	public ArrayList<Korisnik> getGosti() {
		ArrayList<Korisnik> gosti = new ArrayList<Korisnik>();
		for(Korisnik k: korisnici.values()) {
			if(k.getUloga().equals(Uloga.GOST)) {
				gosti.add(k);
			}
		}
		return gosti;
	}

	
	public ArrayList<Korisnik> getAdministratori() {
		ArrayList<Korisnik> admini = new ArrayList<Korisnik>();
		for(Korisnik k: korisnici.values()) {
			if(k.getUloga().equals(Uloga.ADMINISTRATOR)) {
				admini.add(k);
			}
		}
		return admini;
	}
	
	public Korisnik findUser(Integer id) {
		Korisnik k = korisnici.get(id);
		return k;
	}
}

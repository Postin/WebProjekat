package dao;

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
		korisnici.put(id1, new Korisnik(id1,"gost","gost","Filip","Vozarevic",Pol.MUSKO,Uloga.GOST));
		korisnici.put(id2, new Korisnik(id2,"admin","admin","Filip","Vozarevic",Pol.MUSKO,Uloga.ADMINISTRATOR));
		korisnici.put(id3, new Korisnik(id3,"domacin","domacin","Filip","Vozarevic",Pol.MUSKO,Uloga.DOMACIN));
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

	public Korisnik findUser(UUID id) {
		Korisnik k = korisnici.get(id);
		return k;
	}
}

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
		korisnici.put(UUID.randomUUID(), new Korisnik("123","123","Filip","Vozarevic",Pol.MUSKO,Uloga.GOST));
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
}

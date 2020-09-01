package dao;

import java.util.HashMap;
import java.util.UUID;

import beans.Korisnik;

public class KorisnikDAO {
	private HashMap<UUID, Korisnik> korisnici = new HashMap<UUID,Korisnik>();
	
	public Korisnik findUser() {
		return null;
	}
	
	public void addUser() {
		
	}

	public HashMap<UUID, Korisnik> getKorisnici() {
		return korisnici;
	}

	public void setKorisnici(HashMap<UUID, Korisnik> korisnici) {
		this.korisnici = korisnici;
	}
}

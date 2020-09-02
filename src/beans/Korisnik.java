package beans;

import java.util.ArrayList;
import java.util.UUID;

public class Korisnik {
	
	private UUID id;
	private String korisnickoIme;
	private String lozinka;
	private String ime;
	private String prezime;
	private Pol pol;
	private Uloga uloga;
	private ArrayList<Apartman> apartmaniZaIzdavanje; //ako je korisnik domacin
	private ArrayList<Apartman> iznajmljeniApartmani; // gost
	private ArrayList<Rezervacija> rezervacije; // gost
	
	public Korisnik() {
		// TODO Auto-generated constructor stub
		this.id = UUID.randomUUID();
		this.uloga = Uloga.GOST;
		this.pol = Pol.MUSKO;
		this.korisnickoIme = "";
		this.lozinka = "";
		this.ime = "";
		this.prezime = "";
		this.apartmaniZaIzdavanje = new ArrayList<>();
		this.iznajmljeniApartmani = new ArrayList<>();
		this.rezervacije = new ArrayList<>();
	}
	
	//administrator
	public Korisnik(UUID id,String korisnickoIme, String lozinka, String ime, String prz, Pol pol, Uloga uloga) {
		this.id = id;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prz;
		this.pol = pol;
		this.uloga = uloga;
	}
	
	//domacin
	public Korisnik(UUID id, String korisnickoIme, String lozinka, String ime, String prz, Pol pol, Uloga uloga, ArrayList<Apartman> apartmaniZaIzdavanje) {
		this.id = id;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prz;
		this.pol = pol;
		this.uloga = uloga;
		this.apartmaniZaIzdavanje = apartmaniZaIzdavanje;
	}
	
	//gost
	public Korisnik(UUID id, String korisnickoIme, String lozinka, String ime, String prz, Pol pol, Uloga uloga, ArrayList<Apartman> iznajmljeni, ArrayList<Rezervacija> rezervacije) {
		this.id = id;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prz;
		this.pol = pol;
		this.uloga = uloga;
		this.iznajmljeniApartmani = iznajmljeni;
		this.rezervacije = rezervacije;
	}
	
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public Pol getPol() {
		return pol;
	}
	public void setPol(Pol pol) {
		this.pol = pol;
	}
	public Uloga getUloga() {
		return uloga;
	}
	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public ArrayList<Apartman> getApartmaniZaIzdavanje() {
		return apartmaniZaIzdavanje;
	}
	public void setApartmaniZaIzdavanje(ArrayList<Apartman> apartmaniZaIzdavanje) {
		this.apartmaniZaIzdavanje = apartmaniZaIzdavanje;
	}
	public ArrayList<Apartman> getIznajmljeniApartmani() {
		return iznajmljeniApartmani;
	}
	public void setIznajmljeniApartmani(ArrayList<Apartman> iznajmljeniApartmani) {
		this.iznajmljeniApartmani = iznajmljeniApartmani;
	}
	public ArrayList<Rezervacija> getRezervacije() {
		return rezervacije;
	}
	public void setRezervacije(ArrayList<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}

}

package beans;

import java.util.ArrayList;
import java.util.UUID;

public class Korisnik {
	
	private String korisnickoIme; // korisnicko ime je jedinstveno
	private String lozinka;
	private String ime;
	private String prezime;
	private String pol;
	private String uloga;
	private ArrayList<Apartman> apartmaniZaIzdavanje; //ako je korisnik domacin
	private ArrayList<Apartman> iznajmljeniApartmani; // gost
	private ArrayList<Rezervacija> rezervacije; // gost
	
	public Korisnik() {
		// TODO Auto-generated constructor stub
		this.uloga = "GOST";
		this.pol = "MUSKO";
		this.korisnickoIme = "";
		this.lozinka = "";
		this.ime = "";
		this.prezime = "";
		this.apartmaniZaIzdavanje = new ArrayList<>();
		this.iznajmljeniApartmani = new ArrayList<>();
		this.rezervacije = new ArrayList<>();
	}
	
	public Korisnik(String korisnickoIme, String lozinka, String ime, String prz, String pol, String uloga, ArrayList<Apartman> apartmaniZaIzdavanje,
			ArrayList<Apartman> iznajmljeni, ArrayList<Rezervacija> rezervacije) {
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prz;
		this.pol = pol;
		this.uloga = uloga;
		this.apartmaniZaIzdavanje = apartmaniZaIzdavanje;
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
	public String getPol() {
		return pol;
	}
	public void setPol(String pol) {
		this.pol = pol;
	}
	public String getUloga() {
		return uloga;
	}
	public void setUloga(String uloga) {
		this.uloga = uloga;
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

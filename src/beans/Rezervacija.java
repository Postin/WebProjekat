package beans;

import java.time.LocalDate;

public class Rezervacija {
	private Apartman apartman;
	private LocalDate pocetniDatum;
	private int brNocenja;
	private int ukupnaCena;
	private String poruka;
	private Korisnik gost;
	private StatusRezervacije status;
	
	public Rezervacija() {
		
	}
	
	public Apartman getApartman() {
		return apartman;
	}
	public void setApartman(Apartman apartman) {
		this.apartman = apartman;
	}
	public LocalDate getPocetniDatum() {
		return pocetniDatum;
	}
	public void setPocetniDatum(LocalDate pocetniDatum) {
		this.pocetniDatum = pocetniDatum;
	}
	public int getBrNocenja() {
		return brNocenja;
	}
	public void setBrNocenja(int brNocenja) {
		this.brNocenja = brNocenja;
	}
	public int getUkupnaCena() {
		return ukupnaCena;
	}
	public void setUkupnaCena(int ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}
	public String getPoruka() {
		return poruka;
	}
	public void setPoruka(String poruka) {
		this.poruka = poruka;
	}
	public Korisnik getGost() {
		return gost;
	}
	public void setGost(Korisnik gost) {
		this.gost = gost;
	}
	public StatusRezervacije getStatus() {
		return status;
	}
	public void setStatus(StatusRezervacije status) {
		this.status = status;
	}
}

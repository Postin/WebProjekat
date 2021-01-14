package beans;

import java.time.LocalDate;
import java.util.UUID;

public class Rezervacija {
	private Integer id;
	private Apartman apartman;
	private LocalDate pocetniDatum;
	private int brNocenja;
	private int ukupnaCena;
	private String poruka;
	private Korisnik gost;
	private StatusRezervacije status;
	
	public Rezervacija(Integer id, Apartman apartman, LocalDate pocetniDatum, int brNocenja, int ukupnaCena, String poruka, Korisnik gost, StatusRezervacije status) {
		this.id = id;
		this.apartman = apartman;
		this.pocetniDatum = pocetniDatum;
		this.brNocenja = brNocenja;
		this.ukupnaCena = ukupnaCena;
		this.poruka = poruka;
		this.gost = gost;
		this.status = status;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}

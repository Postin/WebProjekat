package beans;

import java.awt.Image;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.UUID;

public class Apartman {
	private UUID id;
	private TipApartmana tip;
	private int brojSoba;
	private int brojGostiju;
	private Lokacija lokacija;
	private ArrayList<LocalDate> datumiZaIzdavanje;
	private ArrayList<LocalDate> dostupnostPoDatumima;
	private Korisnik domacin;
	private ArrayList<Komentar> komentari;
	private ArrayList<Image> slike;
	private int cenaPoNoci;
	private LocalTime vremeZaPrijavu;
	private LocalTime vremeZaOdjavu;
	private StatusApartmana status;
	
	public Apartman() {
		// TODO Auto-generated constructor stub
		this.id = UUID.randomUUID();
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public TipApartmana getTip() {
		return tip;
	}
	public void setTip(TipApartmana tip) {
		this.tip = tip;
	}
	public int getBrojSoba() {
		return brojSoba;
	}
	public void setBrojSoba(int brojSoba) {
		this.brojSoba = brojSoba;
	}
	public int getBrojGostiju() {
		return brojGostiju;
	}
	public void setBrojGostiju(int brojGostiju) {
		this.brojGostiju = brojGostiju;
	}
	public Lokacija getLokacija() {
		return lokacija;
	}
	public void setLokacija(Lokacija lokacija) {
		this.lokacija = lokacija;
	}
	public Korisnik getDomacin() {
		return domacin;
	}
	public void setDomacin(Korisnik domacin) {
		this.domacin = domacin;
	}
	public ArrayList<Komentar> getKomentari() {
		return komentari;
	}
	public void setKomentari(ArrayList<Komentar> komentari) {
		this.komentari = komentari;
	}
	public ArrayList<Image> getSlike() {
		return slike;
	}
	public void setSlike(ArrayList<Image> slike) {
		this.slike = slike;
	}
	public int getCenaPoNoci() {
		return cenaPoNoci;
	}
	public void setCenaPoNoci(int cenaPoNoci) {
		this.cenaPoNoci = cenaPoNoci;
	}
	public LocalTime getVremeZaPrijavu() {
		return vremeZaPrijavu;
	}
	public void setVremeZaPrijavu(LocalTime vremeZaPrijavu) {
		this.vremeZaPrijavu = vremeZaPrijavu;
	}
	public LocalTime getVremeZaOdjavu() {
		return vremeZaOdjavu;
	}
	public void setVremeZaOdjavu(LocalTime vremeZaOdjavu) {
		this.vremeZaOdjavu = vremeZaOdjavu;
	}
	public StatusApartmana getStatus() {
		return status;
	}
	public void setStatus(StatusApartmana status) {
		this.status = status;
	}
	public ArrayList<LocalDate> getDatumiZaIzdavanje() {
		return datumiZaIzdavanje;
	}
	public void setDatumiZaIzdavanje(ArrayList<LocalDate> datumiZaIzdavanje) {
		this.datumiZaIzdavanje = datumiZaIzdavanje;
	}
	public ArrayList<LocalDate> getDostupnostPoDatumima() {
		return dostupnostPoDatumima;
	}
	public void setDostupnostPoDatumima(ArrayList<LocalDate> dostupnostPoDatumima) {
		this.dostupnostPoDatumima = dostupnostPoDatumima;
	}
	
	
}

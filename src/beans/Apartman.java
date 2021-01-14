package beans;

import java.awt.Image;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.UUID;

public class Apartman {
	private Integer id;
	private TipApartmana tip;
	private int brojSoba;
	private int brojGostiju;
	private Lokacija lokacija;
	private ArrayList<LocalDate> datumiZaIzdavanje;
	private ArrayList<LocalDate> dostupnostPoDatumima;
	private String domacin; //korisnicko ime
	private ArrayList<Komentar> komentari;
	private ArrayList<Image> slike;
	private int cenaPoNoci;
	private LocalTime vremeZaPrijavu;
	private LocalTime vremeZaOdjavu;
	private StatusApartmana status;
	private boolean obrisan;
		
	public Apartman(Integer id, TipApartmana tip, int brojSoba, int brojGostiju, Lokacija lok, ArrayList<LocalDate> datumiZaIzdavanje,
			ArrayList<LocalDate> dostupnostPoDatumima, String domacin, ArrayList<Komentar> komentari,
			ArrayList<Image> slike, int cenaPoNoci, LocalTime vremeZaPrijavu, LocalTime vremeZaOdjavu, StatusApartmana status) {
		this.id = id;
		this.tip = tip;
		this.brojSoba = brojSoba;
		this.brojGostiju = brojGostiju;
		this.lokacija = lok;
		this.datumiZaIzdavanje = datumiZaIzdavanje;
		this.dostupnostPoDatumima = dostupnostPoDatumima;
		this.domacin = domacin;
		this.komentari = komentari;
		this.slike = slike;
		this.cenaPoNoci = cenaPoNoci;
		this.vremeZaPrijavu = vremeZaPrijavu;
		this.vremeZaOdjavu = vremeZaOdjavu;
		this.status = status;
		this.obrisan = false;		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public String getDomacin() {
		return domacin;
	}
	public void setDomacin(String domacin) {
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

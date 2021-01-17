package beans;

import java.awt.Image;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.UUID;

public class Apartman {
	private Integer id;
	private String tip; // CEO, SOBA
	private int brojSoba;
	private int brojGostiju;
	private Lokacija lokacija;
	private ArrayList<LocalDate> datumiZaIzdavanje;
	private ArrayList<LocalDate> dostupnostPoDatumima;
	private String domacin; //korisnicko ime
	private ArrayList<Komentar> komentari;
	private ArrayList<String> slike;
	private int cenaPoNoci;
	private String vremeZaPrijavu;
	private String vremeZaOdjavu;
	private String status;
	private ArrayList<Integer> sadrzaji;
	private ArrayList<Integer> rezervacije;
	private boolean obrisan;
	
	public Apartman() {
		super();
	}
		
	public Apartman(Integer id, String tip, int brojSoba, int brojGostiju, Lokacija lok, ArrayList<LocalDate> datumiZaIzdavanje,
			ArrayList<LocalDate> dostupnostPoDatumima, String domacin, ArrayList<Komentar> komentari,
			ArrayList<String> slike, int cenaPoNoci, String vremeZaPrijavu, String vremeZaOdjavu, String status, ArrayList<Integer> sadrzaji) {
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
		this.sadrzaji = sadrzaji;
		this.setObrisan(false);		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
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
	public ArrayList<String> getSlike() {
		return slike;
	}
	public void setSlike(ArrayList<String> slike) {
		this.slike = slike;
	}
	public int getCenaPoNoci() {
		return cenaPoNoci;
	}
	public void setCenaPoNoci(int cenaPoNoci) {
		this.cenaPoNoci = cenaPoNoci;
	}
	public String getVremeZaPrijavu() {
		return vremeZaPrijavu;
	}
	public void setVremeZaPrijavu(String vremeZaPrijavu) {
		this.vremeZaPrijavu = vremeZaPrijavu;
	}
	public String getVremeZaOdjavu() {
		return vremeZaOdjavu;
	}
	public void setVremeZaOdjavu(String vremeZaOdjavu) {
		this.vremeZaOdjavu = vremeZaOdjavu;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
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

	public ArrayList<Integer> getSadrzaji() {
		return sadrzaji;
	}

	public void setSadrzaji(ArrayList<Integer> sadrzaji) {
		this.sadrzaji = sadrzaji;
	}

	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

	public ArrayList<Integer> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(ArrayList<Integer> rezervacije) {
		this.rezervacije = rezervacije;
	}
	
	
}

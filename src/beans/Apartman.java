package beans;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Apartman {
	private Integer id;
	private String tip; // CEO, SOBA
	private String ime;
	private int brojSoba;
	private int brojGostiju;
	private Lokacija lokacija;
	private ArrayList<String> datumiZaIzdavanje = new ArrayList<String>();
	private ArrayList<String> dostupnostPoDatumima = new ArrayList<String>();
	private String domacin; //korisnicko ime
	private ArrayList<Komentar> komentari;
	//private String vremeZaPrijavu;
	//private String vremeZaOdjavu;
	//private String status;
	//private ArrayList<Integer> sadrzaji;
	//private ArrayList<Integer> rezervacije;
	
	private ArrayList<String> slike = new ArrayList<String>();
	private int cenaPoNoci;
	private String vremeZaPrijavu;
	private String vremeZaOdjavu;
	private boolean aktivan=true;
	private boolean obrisan;
	private ArrayList<SadrzajApartmana> sadrzaj = new ArrayList<SadrzajApartmana>();
	private ArrayList<Rezervacija> rezervacija = new ArrayList<Rezervacija>();
	
	public Apartman() {
		super();
	}

	public Apartman(Integer id,String ime, String tip, int brojSoba, int brojGostiju, Lokacija lok, ArrayList<String> datumiZaIzdavanje,
			ArrayList<String> dostupnostPoDatumima, String domacin, ArrayList<Komentar> komentari,
			ArrayList<String> slike, int cenaPoNoci, String vremeZaPrijavu, String vremeZaOdjavu, boolean aktivan, 
			ArrayList<SadrzajApartmana>sadrzaj, ArrayList<Rezervacija>rezervacija) {

		this.id = id;
		this.ime = ime;
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
		this.sadrzaj = sadrzaj;
		this.aktivan = aktivan;
		this.obrisan = false;
		this.sadrzaj = sadrzaj;
		this.rezervacija = rezervacija;
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

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
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

	public ArrayList<String> getDatumiZaIzdavanje() {
		return datumiZaIzdavanje;
	}

	public void setDatumiZaIzdavanje(ArrayList<String> datumiZaIzdavanje) {
		this.datumiZaIzdavanje = datumiZaIzdavanje;
	}

	public ArrayList<String> getDostupnostPoDatumima() {
		return dostupnostPoDatumima;
	}

	public void setDostupnostPoDatumima(ArrayList<String> dostupnostPoDatumima) {
		this.dostupnostPoDatumima = dostupnostPoDatumima;
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

	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}

	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

	public ArrayList<SadrzajApartmana> getSadrzaj() {
		return sadrzaj;
	}

	public void setSadrzaj(ArrayList<SadrzajApartmana> sadrzaj) {
		this.sadrzaj = sadrzaj;
	}

	public ArrayList<Rezervacija> getRezervacija() {
		return rezervacija;
	}

	public void setRezervacija(ArrayList<Rezervacija> rezervacija) {
		this.rezervacija = rezervacija;
	}

	@Override
	public String toString() {
		return "Apartman [id=" + id + ", ime=" + ime + ", tip=" + tip + ", brojSoba=" + brojSoba + ", brojGostiju=" + brojGostiju
				+ ", lokacija=" + lokacija + ", datumiZaIzdavanje=" + datumiZaIzdavanje + ", dostupnostPoDatumima="
				+ dostupnostPoDatumima + ", domacin=" + domacin + ", cenaPoNoci=" + cenaPoNoci + ", vremeZaPrijavu="
				+ vremeZaPrijavu + ", vremeZaOdjavu=" + vremeZaOdjavu + ", status=" + aktivan + ", sadrzaj=" + sadrzaj
				+ ", rezervacija=" + rezervacija + "]";
	}
	
	
}

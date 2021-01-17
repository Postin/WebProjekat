package beans;

import java.util.ArrayList;

public class ApartmanDto {
	
	private String ime;
	private int brojSoba;
	private int brojGostiju;
	private Lokacija lokacija;
	private String pocetakDatum;
	private String krajDatum;
	private String domacin; //username domacina
	private ArrayList<String> slike = new ArrayList<String>();
	private int cena;
	private String vremePrijave;
	private String vremeOdjave;
	private ArrayList<Integer> sadrzaji = new ArrayList<>();
	private String tip;
	
	public ApartmanDto(){
		super();
	}
	
	public ApartmanDto(String ime, int brojSoba, int brojGostiju, Lokacija lokacija, String pocetakDatum,
			String krajDatum, String domacin, ArrayList<String> slike, int cena, String vremePrijave,
			String vremeOdjave, ArrayList<Integer> sadrzaji, String tip) {
		super();
		this.ime = ime;
		this.brojSoba = brojSoba;
		this.brojGostiju = brojGostiju;
		this.lokacija = lokacija;
		this.pocetakDatum = pocetakDatum;
		this.krajDatum = krajDatum;
		this.domacin = domacin;
		this.slike = slike;
		this.cena = cena;
		this.vremePrijave = vremePrijave;
		this.vremeOdjave = vremeOdjave;
		this.sadrzaji = sadrzaji;
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

	public String getPocetakDatum() {
		return pocetakDatum;
	}

	public void setPocetakDatum(String pocetakDatum) {
		this.pocetakDatum = pocetakDatum;
	}

	public String getKrajDatum() {
		return krajDatum;
	}

	public void setKrajDatum(String krajDatum) {
		this.krajDatum = krajDatum;
	}

	public String getDomacin() {
		return domacin;
	}

	public void setDomacin(String domacin) {
		this.domacin = domacin;
	}

	public ArrayList<String> getSlike() {
		return slike;
	}

	public void setSlike(ArrayList<String> slike) {
		this.slike = slike;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public String getVremePrijave() {
		return vremePrijave;
	}

	public void setVremePrijave(String vremePrijave) {
		this.vremePrijave = vremePrijave;
	}

	public String getVremeOdjave() {
		return vremeOdjave;
	}

	public void setVremeOdjave(String vremeOdjave) {
		this.vremeOdjave = vremeOdjave;
	}

	public ArrayList<Integer> getSadrzaji() {
		return sadrzaji;
	}

	public void setSadrzaji(ArrayList<Integer> sadrzaji) {
		this.sadrzaji = sadrzaji;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	@Override
	public String toString() {
		return "ApartmanDto [ime=" + ime + ", brojSoba=" + brojSoba + ", brojGostiju=" + brojGostiju + ", lokacija="
				+ lokacija + ", pocetakDatum=" + pocetakDatum + ", krajDatum=" + krajDatum + ", domacin=" + domacin
				+ ", slike=" + slike + ", cena=" + cena + ", vremePrijave=" + vremePrijave + ", vremeOdjave="
				+ vremeOdjave + ", sadrzaji=" + sadrzaji + ", tip=" + tip + "]";
	}
	
	
	

	
}

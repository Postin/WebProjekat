package beans;

import java.util.ArrayList;

public class ApartmanZaDomacinaDto {
	
	
	private String tip;
	private String ime;
	private int brojSoba;
	private int brojGostiju;
	private Lokacija lokacija;
	private ArrayList<String> slike = new ArrayList<String>();
	private int cenaPoNoci;
	private boolean aktivan = true;
	
	
	public ApartmanZaDomacinaDto() {
		super();
	
	}


	public ApartmanZaDomacinaDto(String tip, String ime, int brojSoba, int brojGostiju, Lokacija lokacija, int cenaPoNoci, boolean aktivan) {
		super();
		this.tip = tip;
		this.ime = ime;
		this.brojSoba = brojSoba;
		this.brojGostiju = brojGostiju;
		this.lokacija = lokacija;
		
		this.cenaPoNoci = cenaPoNoci;
		this.aktivan = aktivan;
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


	public boolean isAktivan() {
		return aktivan;
	}


	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}


	@Override
	public String toString() {
		return "ApartmanZaDomacinaDto [tip=" + tip + ", ime=" + ime + ", brojSoba=" + brojSoba + ", brojGostiju="
				+ brojGostiju + ", lokacija=" + lokacija + ", slike=" + slike + ", cenaPoNoci=" + cenaPoNoci
				+ ", aktivan=" + aktivan + "]";
	}
	
	
	
	
	
	
	
	

}

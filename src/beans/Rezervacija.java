package beans;


import java.util.Date;

public class Rezervacija {
	private Integer id;
	private Integer apartmanId;
	private Date pocetniDatum;
	private int brNocenja;
	private int ukupnaCena;
	private String poruka;
	private String gost; //korisnicko Ime
	private String status; // KREIRANA,ODBIJENA,ODUSTANAK,PRIHVACENA,ZAVRSENA
	
	public Rezervacija() {
		super();
	}
	
	public Rezervacija(Integer id, Integer apartmanId, Date pocetniDatum, int brNocenja, int ukupnaCena, String poruka, String gost, String status) {
		this.id = id;
		this.apartmanId = apartmanId;
		this.pocetniDatum = pocetniDatum;
		this.brNocenja = brNocenja;
		this.ukupnaCena = ukupnaCena;
		this.poruka = poruka;
		this.gost = gost;
		this.status = status;
	}
	
	public Integer getApartmanId() {
		return apartmanId;
	}
	public void setApartman(Integer apartman) {
		this.apartmanId = apartman;
	}
	public Date getPocetniDatum() {
		return pocetniDatum;
	}
	public void setPocetniDatum(Date pocetniDatum) {
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
	public String getGost() {
		return gost;
	}
	public void setGost(String gost) {
		this.gost = gost;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}

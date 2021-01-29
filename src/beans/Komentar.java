package beans;

import java.util.UUID;

public class Komentar {
	private Integer id;
	private String gostKorisnickoIme;
	private Integer apartmanId;
	private String tekst;
	private int ocena;
	private boolean vidljivost;
	
	public Komentar() {

	}
	
	public Komentar(Integer id, String gost, Integer apartmanId, String tekst, int ocena) {
		super();
		this.id = id;
		this.gostKorisnickoIme = gostKorisnickoIme;
		this.apartmanId = apartmanId;
		this.tekst = tekst;
		this.ocena = ocena;
		this.vidljivost = true;
	}



	public boolean isVidljivost() {
		return vidljivost;
	}

	public void setVidljivost(boolean vidljivost) {
		this.vidljivost = vidljivost;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public String getGostKorisnickoIme() {
		return gostKorisnickoIme;
	}

	public void setGostKorisnickoIme(String gostKorisnickoIme) {
		this.gostKorisnickoIme = gostKorisnickoIme;
	}

	public Integer getApartmanId() {
		return apartmanId;
	}

	public void setApartmanId(Integer apartmanId) {
		this.apartmanId = apartmanId;
	}

	public String getTekst() {
		return tekst;
	}
	public void setTekst(String tekst) {
		this.tekst = tekst;
	}
	public int getOcena() {
		return ocena;
	}
	public void setOcena(int ocena) {
		this.ocena = ocena;
	}

	@Override
	public String toString() {
		return "Komentar [id=" + id + ", gost=" + gostKorisnickoIme + ", apartman=" + apartmanId + ", tekst=" + tekst + ", ocena="
				+ ocena + "]";
	}
	
	
}

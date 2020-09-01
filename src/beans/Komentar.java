package beans;

import java.util.UUID;

public class Komentar {
	private UUID id;
	private Korisnik gost;
	private Apartman apartman;
	private String tekst;
	private int ocena;
	
	public Komentar() {
		// TODO Auto-generated constructor stub
		this.id = UUID.randomUUID();
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public Korisnik getGost() {
		return gost;
	}
	public void setGost(Korisnik gost) {
		this.gost = gost;
	}
	public Apartman getApartman() {
		return apartman;
	}
	public void setApartman(Apartman apartman) {
		this.apartman = apartman;
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
	
}

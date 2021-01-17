package beans;

import java.util.UUID;

public class Komentar {
	private UUID id;
	private Korisnik gost;
	private Apartman apartman;
	private String tekst;
	private int ocena;
	private boolean vidljivost;
	
	public Komentar() {
		super();
		// TODO Auto-generated constructor stub
		this.id = UUID.randomUUID();
	}
	
	public Komentar(UUID id, Korisnik gost, Apartman apartman, String tekst, int ocena) {
		super();
		this.id = id;
		this.gost = gost;
		this.apartman = apartman;
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

	@Override
	public String toString() {
		return "Komentar [id=" + id + ", gost=" + gost + ", apartman=" + apartman + ", tekst=" + tekst + ", ocena="
				+ ocena + "]";
	}
	
	
}

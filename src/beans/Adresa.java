package beans;

import java.util.UUID;

public class Adresa {
	private UUID id;
	private String ulicaIbr;
	private String mesto;
	private int postanskiBr;
	
	public Adresa() {
		this.id = UUID.randomUUID();		
	}
	
	public Adresa(String ulicaIBr, String mesto, int postanskiBr) {
		this.ulicaIbr = ulicaIBr;
		this.mesto = mesto;
		this.postanskiBr = postanskiBr;
	}
	
	public String getUlicaIbr() {
		return ulicaIbr;
	}
	public void setUlicaIbr(String ulicaIbr) {
		this.ulicaIbr = ulicaIbr;
	}
	
	public String getMesto() {
		return mesto;
	}
	public void setMesto(String mesto) {
		this.mesto = mesto;
	}
	
	public int getPostanskiBr() {
		return postanskiBr;
	}
	public void setPostanskiBr(int postanskiBr) {
		this.postanskiBr = postanskiBr;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
}

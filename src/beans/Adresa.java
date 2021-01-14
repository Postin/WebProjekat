package beans;

import java.util.UUID;

public class Adresa {
	private Integer id;
	private String ulicaIbr;
	private String mesto;
	private int postanskiBr;
	
	public Adresa() {
		
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}

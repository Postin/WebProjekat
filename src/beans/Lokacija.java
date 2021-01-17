package beans;

import java.util.UUID;

public class Lokacija {
	private Integer id;
	private double geoSirina;
	private double geoDuzina;
	private Adresa adresa;
	
	public Lokacija() {
		// TODO Auto-generated constructor stub
	}
	
	public Lokacija(Integer id, double geoSirina, double geoDuzina, Adresa adresa) {
	//	this.id = UUID.randomUUID();
		this.geoSirina = geoSirina;
		this.geoDuzina = geoDuzina;
		this.adresa = adresa;
		this.id = id;
	}
	
	public double getGeoDuzina() {
		return geoDuzina;
	}
	public void setGeoDuzina(double geoDuzina) {
		this.geoDuzina = geoDuzina;
	}
	
	public double getGeoSirina() {
		return geoSirina;
	}
	public void setGeoSirina(double geoSirina) {
		this.geoSirina = geoSirina;
	}
	
	public Adresa getAdresa() {
		return adresa;
	}
	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Lokacija [id=" + id + ", geoSirina=" + geoSirina + ", geoDuzina=" + geoDuzina + ", adresa=" + adresa
				+ "]";
	}
	
	
	
}

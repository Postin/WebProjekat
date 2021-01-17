package beans;


public class Lokacija {
	private double geoSirina;
	private double geoDuzina;
	private Adresa adresa;
	
	public Lokacija() {
		// TODO Auto-generated constructor stub
	}
	
	public Lokacija(double geoSirina, double geoDuzina, Adresa adresa) {
		this.geoSirina = geoSirina;
		this.geoDuzina = geoDuzina;
		this.adresa = adresa;
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
	
}

package beans;

import java.util.UUID;

public class SadrzajApartmana {
	//private UUID id = UUID.randomUUID();
	private Integer id;
	private String naziv;
	private boolean obrisan;
	
	public SadrzajApartmana() {
		super();
		this.setObrisan(false);
		
	}
	
	public SadrzajApartmana(Integer id, String naziv) {
		this.id = id;
		this.naziv = naziv;
		this.setObrisan(false);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

	@Override
	public String toString() {
		return "SadrzajApartmana [id=" + id + ", naziv=" + naziv + "]";
	}
	
	
	
}

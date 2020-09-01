package beans;

import java.util.UUID;

public class SadrzajApartmana {
	private UUID id = UUID.randomUUID();
	private String naziv;
	
	public SadrzajApartmana() {
		
	}
	
	public SadrzajApartmana(String naziv) {
		this.id = UUID.randomUUID();
		this.naziv = naziv;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
}

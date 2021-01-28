package beans;

public class PretragaDto {

	private String datumDolaska;
	private String datumOdlaska;
	private String mesto;
	private int cenaOd;
	private int cenaDo;
	private int brojSobaOd;
	private int brojSobaDo;
	private int brojOsoba;
	
	
	public PretragaDto() {
		super();
		
	}


	public PretragaDto(String datumDolaska, String datumOdlaska, String mesto, int cenaOd, int cenaDo, int brojSobaOd,
			int brojSobaDo, int brojOsoba) {
		super();
		this.datumDolaska = datumDolaska;
		this.datumOdlaska = datumOdlaska;
		this.mesto = mesto;
		this.cenaOd = cenaOd;
		this.cenaDo = cenaDo;
		this.brojSobaOd = brojSobaOd;
		this.brojSobaDo = brojSobaDo;
		this.brojOsoba = brojOsoba;
	}


	public String getDatumDolaska() {
		return datumDolaska;
	}


	public void setDatumDolaska(String datumDolaska) {
		this.datumDolaska = datumDolaska;
	}


	public String getDatumOdlaska() {
		return datumOdlaska;
	}


	public void setDatumOdlaska(String datumOdlaska) {
		this.datumOdlaska = datumOdlaska;
	}


	public String getMesto() {
		return mesto;
	}


	public void setMesto(String mesto) {
		this.mesto = mesto;
	}


	public int getCenaOd() {
		return cenaOd;
	}


	public void setCenaOd(int cenaOd) {
		this.cenaOd = cenaOd;
	}


	public int getCenaDo() {
		return cenaDo;
	}


	public void setCenaDo(int cenaDo) {
		this.cenaDo = cenaDo;
	}


	public int getBrojSobaOd() {
		return brojSobaOd;
	}


	public void setBrojSobaOd(int brojSobaOd) {
		this.brojSobaOd = brojSobaOd;
	}


	public int getBrojSobaDo() {
		return brojSobaDo;
	}


	public void setBrojSobaDo(int brojSobaDo) {
		this.brojSobaDo = brojSobaDo;
	}


	public int getBrojOsoba() {
		return brojOsoba;
	}


	public void setBrojOsoba(int brojOsoba) {
		this.brojOsoba = brojOsoba;
	}


	@Override
	public String toString() {
		return "PretragaDto [datumDolaska=" + datumDolaska + ", datumOdlaska=" + datumOdlaska + ", mesto=" + mesto
				+ ", cenaOd=" + cenaOd + ", cenaDo=" + cenaDo + ", brojSobaOd=" + brojSobaOd + ", brojSobaDo="
				+ brojSobaDo + ", brojOsoba=" + brojOsoba + "]";
	}
	
	
	
	
}

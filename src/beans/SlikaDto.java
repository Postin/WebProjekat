package beans;

import java.util.ArrayList;

public class SlikaDto {
	
	private Integer aptId;
	private ArrayList<String> slike;
	
	public SlikaDto() {
		super();
	}

	public SlikaDto(Integer aptId, ArrayList<String> slike) {
		super();
		this.aptId = aptId;
		this.slike = slike;
	}

	public Integer getAptId() {
		return aptId;
	}

	public void setAptId(Integer aptId) {
		this.aptId = aptId;
	}

	public ArrayList<String> getSlike() {
		return slike;
	}

	public void setSlike(ArrayList<String> slike) {
		this.slike = slike;
	}

	@Override
	public String toString() {
		return "SlikaDto [aptId=" + aptId + ", slike=" + slike + "]";
	}
	
	

}

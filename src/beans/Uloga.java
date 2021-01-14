package beans;

public enum Uloga {
	ADMINISTRATOR,
	DOMACIN,
	GOST;
	
	// da bih mogao da poredim sa stringom u pretrazi
	public String toString(){
        switch(this){
        case ADMINISTRATOR :
            return "ADMINISTRATOR";
        case DOMACIN :
            return "DOMACIN";
        case GOST :
            return "GOST";
        }
        return null;
    }
}

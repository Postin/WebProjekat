package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import beans.Adresa;
import beans.Apartman;
import beans.Korisnik;
import beans.Lokacija;
import beans.StatusApartmana;
import beans.TipApartmana;
import dao.KorisnikDAO;

public class ApartmanDAO {
	private HashMap<Integer, Apartman> apartmani = new HashMap<Integer, Apartman>();

	public ApartmanDAO(String path) {
		loadApartments(path);
		/*
		 * apartmani.put(id1, new Apartman(id1,TipApartmana.CEO,3,4, new
		 * Lokacija(14.12,57.12, new Adresa("Brace Dronjak 15","Novi Sad",21000)),null,
		 * null, domacin1, null komentari, null slike, 2000, vremeZaPrijavu null,
		 * vremeZaOdjavu null,StatusApartmana.AKTIVAN));
		 * 
		 * apartmani.put(id2, new Apartman(id2,TipApartmana.SOBA,2,2, new
		 * Lokacija(15.14,52.12, new
		 * Adresa("Brace Ribnikara 15","Novi Sad",21000)),null, null, domacin2, null
		 * komentari, null slike, 2000, vremeZaPrijavu null, vremeZaOdjavu
		 * null,StatusApartmana.AKTIVAN));
		 */
	}
	
	public void loadApartments(String path) {
		BufferedReader in = null;
		try {
			File file = new File(path + "/data/apartments.json");
			in = new BufferedReader(new FileReader(file));
			String line;
			StringBuilder sb = new StringBuilder();
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			ObjectMapper mapper = new ObjectMapper();
			this.apartmani = mapper.readValue(sb.toString(), new TypeReference<Map<Integer, Apartman>>(){});
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {}
			}
		}
	}

	public Apartman findApartman(Apartman ap) {
		for (Apartman a : apartmani.values()) {
			if (a.getId().equals(ap.getId())) {
				return a;
			}
		}

		return null;
	}
	
	//nadji apartman po id-u
	public Apartman findApartmanById(Integer id) {
		if(this.apartmani.containsKey(id)) {
			return this.apartmani.get(id);
		}
		
		return null;
	}
	

	public void addApartman(Apartman ap) {
		for (Apartman a : apartmani.values()) {
			if (a.getId().equals(ap.getId())) {
				return;
			}
		}
		apartmani.put(ap.getId(), ap);
	}
	
	//izlistaj sve apartmane
	public ArrayList<Apartman> getApartmaniList() {
		ArrayList<Apartman> apartmaniList = new ArrayList<Apartman>();
		for (Apartman a : apartmani.values()) {
			apartmaniList.add(a);
		}
		return apartmaniList;
	}
	
	//izlistaj aktivne apartmane
	public ArrayList<Apartman> getActiveApartments() {
        ArrayList<Apartman> activeApts = new ArrayList<>();

        for (Apartman a : apartmani.values()) {
            if (a.isAktivan())
                activeApts.add(a);
        }

        return activeApts;
    }
	


	

	public HashMap<Integer, Apartman> getApartmani() {
		return apartmani;
	}
	public void setApartmani(HashMap<Integer, Apartman> apartmani) {
		this.apartmani = apartmani;
	}
	public void deleteApartman(Apartman a) {
		apartmani.remove(a.getId(), a);
	}
	
	
	//apartmani odgovarajuceg domacina
	public ArrayList<Apartman> getApartmentsByHost(String username) {
	        System.out.println("Preuzimamo apartmane za odredjenog domacina");

	        ArrayList<Apartman> apartmaniDomacina = new ArrayList<>();

	        for (Apartman a : apartmani.values()) {
	            System.out.println(a.toString());
	                if (a.getDomacin().equals(username)) {
	                	apartmaniDomacina.add(a);
	        }
	        }
	        System.out.println(this.apartmani);
	        return apartmaniDomacina;
	    }


	public void loadApartmans(String path) {
		BufferedReader in = null;
		try {
			File file = new File(path + "/data/apartmani.json");
			in = new BufferedReader(new FileReader(file));
			String line;
			StringBuilder sb = new StringBuilder();
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			ObjectMapper mapper = new ObjectMapper();
			this.apartmani = mapper.readValue(sb.toString(), new TypeReference<Map<Integer, Apartman>>(){});
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {}
			}
		}
	}
	
	public void saveApartmans(String path) {
		BufferedWriter out = null;
		try {
			File file = new File(path + "/data/apartmani.json");
			out = new BufferedWriter(new FileWriter(file));
			ObjectMapper mapper = new ObjectMapper();
			ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());  
			String content = writer.writeValueAsString(this.apartmani);
			out.write(content);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {}
			}
		}
	}
	
	
}
package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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

	public void addApartman(Apartman ap) {
		for (Apartman a : apartmani.values()) {
			if (a.getId().equals(ap.getId())) {
				return;
			}
		}
		apartmani.put(ap.getId(), ap);
	}

	public void deleteApartman(Apartman a) {
		apartmani.remove(a.getId(), a);
	}

	public HashMap<Integer, Apartman> getApartmani() {
		return apartmani;
	}

	public ArrayList<Apartman> getApartmaniList() {
		ArrayList<Apartman> apartmaniList = new ArrayList<Apartman>();
		for (Apartman a : apartmani.values()) {
			apartmaniList.add(a);
		}
		return apartmaniList;
	}

	public void setApartmani(HashMap<Integer, Apartman> apartmani) {
		this.apartmani = apartmani;
	}

	public Apartman findApartman(Integer id) {
		Apartman a = apartmani.get(id);
		return a;
	}
}

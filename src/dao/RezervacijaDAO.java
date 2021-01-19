package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import beans.Apartman;
import beans.Korisnik;
import beans.Rezervacija;
import beans.StatusRezervacije;

public class RezervacijaDAO {
	private HashMap<Integer, Rezervacija> rezervacije = new HashMap<Integer,Rezervacija>();
	
	public RezervacijaDAO(String path) {		
		loadRezervacije(path);
		//rezervacije.put(id1, new Rezervacija(id1,a1,null,3,6000, "Dobar Dan!",gost1, StatusRezervacije.KREIRANA));
		//rezervacije.put(id2, new Rezervacija(id2,a2,null,15,30000,"Postovani!",gost2,StatusRezervacije.PRIHVACENA));
	}
	
	public void loadRezervacije(String path) {
		BufferedReader in = null;
		try {
			File file = new File(path + "/data/reservations.json");
			in = new BufferedReader(new FileReader(file));
			String line;
			StringBuilder sb = new StringBuilder();
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			ObjectMapper mapper = new ObjectMapper();
			this.rezervacije = mapper.readValue(sb.toString(), new TypeReference<Map<Integer, Rezervacija>>(){});
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
	
	public void saveRezervacije(String path) {
		BufferedWriter out = null;
		try {
			File file = new File(path + "/data/reservations.json");
			out = new BufferedWriter(new FileWriter(file));
			ObjectMapper mapper = new ObjectMapper();
			ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());  
			String content = writer.writeValueAsString(this.rezervacije);
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
	
	public Rezervacija findRezervacija(Rezervacija rez) {
		for(Rezervacija r: rezervacije.values()) {
			if(r.getId().equals(rez.getId())) {
				return r;
			}
		}
		return null;
	}
	
	public void addRezervacija(Rezervacija rez) {
		for(Rezervacija r: rezervacije.values()) {
			if(r.getId().equals(rez.getId())) {
				return;
			}
		}
		rezervacije.put(rez.getId(), rez);
	}
	
	public void deleteRezervacija(Rezervacija rez) {
		rezervacije.remove(rez.getId(), rez);
	}

	public HashMap<Integer, Rezervacija> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(HashMap<Integer, Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}

	public Rezervacija findRezervacija(Integer id) {
		Rezervacija r = rezervacije.get(id);
		return r;
	}
}

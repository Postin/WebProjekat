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

import beans.Korisnik;
import beans.Uloga;
import beans.Korisnik;

public class KorisnikDAO {
	private HashMap<String, Korisnik> korisnici = new HashMap<String,Korisnik>();;
	
	public KorisnikDAO(String path) {
		
		loadUsers(path);
		//korisnici.put("gost", new Korisnik("gost","gost","Filip","Vozarevic",Pol.MUSKO,Uloga.GOST, null, null, null ));
		//korisnici.put("admin", new Korisnik("admin","admin","Natasa","Mitrovic",Pol.ZENSKO,Uloga.ADMINISTRATOR, null, null, null));
		//korisnici.put("domacin", new Korisnik("domacin", "domacin", "Filip", "Vozarevic", Pol.MUSKO, Uloga.DOMACIN, null, null, null));
		//korisnici.put("domacin2", new Korisnik("domacin2", "domacin2", "Natasa", "Mitrovic", Pol.ZENSKO, Uloga.DOMACIN, null, null, null));
		//korisnici.put("gost2", new Korisnik("gost2","gost2","Pera","Peric",Pol.MUSKO,Uloga.GOST, null, null, null));
	}
	
	public void loadUsers(String path) {
		BufferedReader in = null;
		try {
			File file = new File(path + "/data/users.json");
			in = new BufferedReader(new FileReader(file));
			String line;
			StringBuilder sb = new StringBuilder();
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			ObjectMapper mapper = new ObjectMapper();
			this.korisnici = mapper.readValue(sb.toString(), new TypeReference<Map<String, Korisnik>>(){});
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
	
	public void saveUsers(String path) {
		BufferedWriter out = null;
		try {
			File file = new File(path + "/data/users.json");
			out = new BufferedWriter(new FileWriter(file));
			ObjectMapper mapper = new ObjectMapper();
			ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());  
			String content = writer.writeValueAsString(this.korisnici);
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
	
	public Korisnik findUser(Korisnik user) {
		for(Korisnik k: korisnici.values()) {
			if(user.getKorisnickoIme().equals(k.getKorisnickoIme()) &&
					user.getLozinka().equals(k.getLozinka())) {
				return k;
			}
		}
		
		return null;
	}
	
	public void addUser(Korisnik user) {
		for(Korisnik k: korisnici.values()) {
			if(user.getKorisnickoIme().equals(k)) {
				return;
			}
		}
		korisnici.put(user.getKorisnickoIme(), user);
	}
	
	public void deleteUser(Korisnik user) {
		korisnici.remove(user.getKorisnickoIme(), user);
	}

	public HashMap<String, Korisnik> getKorisnici() {
		return korisnici;
	}
	
	public void setKorisnici(HashMap<String, Korisnik> korisnici) {
		this.korisnici = korisnici;
	}
	
	public ArrayList<Korisnik> getDomacini() {
		ArrayList<Korisnik> domacini = new ArrayList<Korisnik>();
		for(Korisnik k: korisnici.values()) {
			if(k.getUloga().equals(Uloga.DOMACIN)) {
				domacini.add(k);
			}
		}
		return domacini;
	}
	
	public ArrayList<Korisnik> getGosti() {
		ArrayList<Korisnik> gosti = new ArrayList<Korisnik>();
		for(Korisnik k: korisnici.values()) {
			if(k.getUloga().equals(Uloga.GOST)) {
				gosti.add(k);
			}
		}
		return gosti;
	}

	
	public ArrayList<Korisnik> getAdministratori() {
		ArrayList<Korisnik> admini = new ArrayList<Korisnik>();
		for(Korisnik k: korisnici.values()) {
			if(k.getUloga().equals(Uloga.ADMINISTRATOR)) {
				admini.add(k);
			}
		}
		return admini;
	}
	
	public Korisnik findUser(String korisnickoIme) {
		Korisnik k = korisnici.get(korisnickoIme);
		return k;
	}
}

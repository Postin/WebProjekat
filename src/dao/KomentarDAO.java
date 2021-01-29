package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import beans.Komentar;
import beans.SadrzajApartmana;

public class KomentarDAO {
	
	private HashMap<Integer, Komentar> komentari = new HashMap<>();
	
	public KomentarDAO(String contextPath) {
	       
        loadKomentar(contextPath);
   }
	
	 public HashMap<Integer, Komentar> getKomentari() {
	        return komentari;
	    }

	    public void setkomentari(HashMap<Integer, Komentar> komentar) {
	        this.komentari = komentari;
	    }

	    public Komentar findById(Integer id) {
	        if (this.komentari.containsKey(id)) {
	            return this.komentari.get(id);
	        }

	        return null;
	    }
	    
	    public void saveKomentar(String path) {
			BufferedWriter out = null;
			try {
				File file = new File(path + "/data/komentari.json");
				out = new BufferedWriter(new FileWriter(file));
				ObjectMapper mapper = new ObjectMapper();
				ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());  
				String content = writer.writeValueAsString(this.komentari);
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
	    
	    public void loadKomentar(String path) {
			BufferedReader in = null;
			try {
				File file = new File(path + "/data/komentari.json");
				in = new BufferedReader(new FileReader(file));
				String line;
				StringBuilder sb = new StringBuilder();
				while ((line = in.readLine()) != null) {
					sb.append(line);
				}
				ObjectMapper mapper = new ObjectMapper();
				this.komentari = mapper.readValue(sb.toString(), new TypeReference<Map<Integer,Komentar>>(){});
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

}

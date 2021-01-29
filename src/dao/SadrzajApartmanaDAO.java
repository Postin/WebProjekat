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

import beans.Korisnik;
import beans.SadrzajApartmana;

public class SadrzajApartmanaDAO {
	
	 private HashMap<Integer, SadrzajApartmana> sadrzaji = new HashMap<>();
	   
	    
	    public SadrzajApartmanaDAO(String contextPath) {
	       
	        
//	        sadrzaji.put( 1 ,new SadrzajApartmana(1,"WiFi"));
//	        sadrzaji.put(2,new SadrzajApartmana(2,"Kablovska"));
//	        sadrzaji.put( 3 ,new SadrzajApartmana(3,"Parking"));
//	        sadrzaji.put(4,new SadrzajApartmana(4,"Kuhinja"));
//	        sadrzaji.put( 5 ,new SadrzajApartmana(5,"Pegla"));
//	        sadrzaji.put(6,new SadrzajApartmana(6,"Ves Masina"));
//	        saveSadrzaj(contextPath);
	    	loadSadrzaj(contextPath);
	    	
	    	
	    }

	    public HashMap<Integer, SadrzajApartmana> getSadrzaji() {
	        return sadrzaji;
	    }

	    public void setSadrzaji(HashMap<Integer, SadrzajApartmana> sadrzaji) {
	        this.sadrzaji = sadrzaji;
	    }

	    public SadrzajApartmana findById(Integer id) {
	        if (this.sadrzaji.containsKey(id)) {
	            return this.sadrzaji.get(id);
	        }

	        return null;
	    }
	    
	    public void saveSadrzaj(String path) {
			BufferedWriter out = null;
			try {
				File file = new File(path + "/data/sadrzajApartmana.json");
				out = new BufferedWriter(new FileWriter(file));
				ObjectMapper mapper = new ObjectMapper();
				ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());  
				String content = writer.writeValueAsString(this.sadrzaji);
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
	    
	    public void loadSadrzaj(String path) {
			BufferedReader in = null;
			try {
				File file = new File(path + "/data/sadrzajApartmana.json");
				in = new BufferedReader(new FileReader(file));
				String line;
				StringBuilder sb = new StringBuilder();
				while ((line = in.readLine()) != null) {
					sb.append(line);
				}
				ObjectMapper mapper = new ObjectMapper();
				this.sadrzaji = mapper.readValue(sb.toString(), new TypeReference<Map<Integer, SadrzajApartmana>>(){});
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

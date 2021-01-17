package dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import beans.SadrzajApartmana;

public class SadrzajApartmanaDAO {
	
	 private HashMap<Integer, SadrzajApartmana> sadrzaji = new HashMap<>();
	   
	    
	    public SadrzajApartmanaDAO(String contextPath) {
	       
	        
	        sadrzaji.put( 1 ,new SadrzajApartmana(1,"WiFi"));
	        sadrzaji.put(2,new SadrzajApartmana(2,"Kablovska"));
	        sadrzaji.put( 3 ,new SadrzajApartmana(1,"Parking"));
	        sadrzaji.put(4,new SadrzajApartmana(2,"Kuhinja"));
	        sadrzaji.put( 5 ,new SadrzajApartmana(1,"Pegla"));
	        sadrzaji.put(6,new SadrzajApartmana(2,"Ves Masina"));
	        saveData(contextPath);
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
	    
	    public void saveData(String path) {
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
	    

}

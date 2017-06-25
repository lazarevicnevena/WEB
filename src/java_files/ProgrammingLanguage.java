package java_files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ProgrammingLanguage {

	private String name;
	private static Files files;
	
	private static ArrayList<String> programming_languages = new ArrayList<String>();
	 
	public ProgrammingLanguage() throws IOException {
		super();
		programming_languages = new ArrayList<String>();
		programming_languages = parseProgrammingLanguages();
	
	}
	public ProgrammingLanguage(String name) {
		super();
		this.name = name;
	}

	
	
	public static ArrayList<String> getProgramming_languages() {
		return programming_languages;
	}
	public static void setProgramming_languages(ArrayList<String> programming_languages) {
		ProgrammingLanguage.programming_languages = programming_languages;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static ArrayList<String> parseProgrammingLanguages() throws IOException{
		File file = new File(files.pathProgrLanguage);
		file.createNewFile();
		
		BufferedReader reader1 = new BufferedReader(new FileReader(files.pathProgrLanguage));
		BufferedReader reader2 = new BufferedReader(new FileReader(files.pathProgrLanguage));
	    String line1 = "";
	    String line2 = "";
	    
		if ((line1 = reader1.readLine()) == null){
			reader1.close();
			reader2.close();
		    return programming_languages;
		}
		else{
			
			while ((line2 = reader2.readLine()) != null)
			{
				String[] tokens = line2.split("\\|");
			    
			    programming_languages.add(tokens[0]);
			    tokens = null;
		    }
		    
		}
		reader2.close();
		reader1.close();
		return programming_languages;
	}
	
	public boolean addProgrammingLanguages(String name) throws IOException{
		
		String writeIn = "";

		
	    FileWriter fw = new FileWriter(files.pathProgrLanguage, true); 
		
		if (programming_languages.size() == 0){
			writeIn = name;
		}else{
			for (int i=0; i<programming_languages.size(); i++){
				if (programming_languages.get(i).equals(name)){
					fw.close();
					return false;
				}
				else{
					writeIn = "\n" + name;	
				}	
			}
		}
		
		
		programming_languages.add(name);
	    fw.append(writeIn);
	    fw.close();
		return true;
	}
	
}

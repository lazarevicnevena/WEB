package java_files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.time.DateUtils;

public class SnippetHandler {
	
	private static ArrayList<Snippet> snippets = new ArrayList<Snippet>();
	
	private static Files files;
	
	public SnippetHandler() throws IOException{
		super();
		snippets = new ArrayList<Snippet>();
		snippets = parseSnippets();
		for (int i=0; i<snippets.size(); i++){
			if (snippets.get(i).getContent().equals("null")){
				snippets.remove(i);
				break;
			}
		}
	}
	
	public ArrayList<Snippet> getSnippets() {
		return snippets;
	}
	public void setSnippets(ArrayList<Snippet> snippets) {
		this.snippets = snippets;
	}
	
	public static ArrayList<Snippet> parseSnippets() throws IOException{
		
		
		BufferedReader reader1 = new BufferedReader(new FileReader(files.pathSnippets));
		BufferedReader reader2 = new BufferedReader(new FileReader(files.pathSnippets));
	    String line1 = "";
	    String line2 = "";
	    
		if ((line1 = reader1.readLine()) == null){
			reader1.close();
			reader2.close();
		    return snippets;
		}
		else{
			
			while ((line2 = reader2.readLine()) != null)
			{
				String[] tokens = line2.split("\\|");
				Rate r = new Rate(Integer.parseInt(tokens[5]),
			    		Integer.parseInt(tokens[6]));
			    Snippet snippet = new Snippet(tokens[1], tokens[2], tokens[3], tokens[4], r);
			    if (tokens[7].equals("true")){
			    	snippet.setCanBeCommented(true);
			    }else{
			    	snippet.setCanBeCommented(false);
			    }
			    String uname = tokens[8];
			    AllUsers au = new AllUsers();
			    User u = au.findUser(uname);
			    String id = tokens[0];
			    snippet.setId(id);
			    snippet.setUser(u);
			    
			    String d = tokens[9];
			    
			    DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
			    Date result;
				try {
					result = df.parse(d);
					snippet.setDate(result);
				} catch (ParseException e) {
					snippet.setDate(new Date());
				}  
			    
			    
			    snippets.add(snippet);
			    tokens = null;
		    }
		    
		}
		reader2.close();
		reader1.close();
		return snippets;
	}
	
	public static String addSnippet(Snippet snippet) throws IOException{
		String writeIn = "";
	
	    FileWriter fw = new FileWriter(files.pathSnippets, true); 
		
	    if (snippets.size() == 0){
			writeIn =snippet.getId() + "|"+ snippet.getDescription() + "|" + snippet.getContent() + "|" + 
					snippet.getProgrammingLanguage() + "|" + snippet.getUrlAddress() + "|" +
					
					snippet.getRate().getPositiveRates().toString() + "|" + 
					snippet.getRate().getNegativeRates().toString() + "|" + 
					snippet.getCanBeCommented() + "|" + snippet.getUser().getUsername()+ "|" +
					snippet.getDate().toString() + "\n";
		}else{
			for (int i=0; i<snippets.size(); i++){
				if (snippets.get(i).getContent().equals(snippet.getContent())){
					fw.close();
					return "";
				}
				else{
					writeIn = snippet.getId() + "|"+ snippet.getDescription() + "|" + snippet.getContent() + "|" + 
							snippet.getProgrammingLanguage() + "|" + snippet.getUrlAddress() + "|" +
							
							snippet.getRate().getPositiveRates().toString() + "|" + 
							snippet.getRate().getNegativeRates().toString() + "|" +
							snippet.getCanBeCommented() + "|" + snippet.getUser().getUsername()+ "|"+
							snippet.getDate().toString() + "\n";
				}	
			}
		}
	    
	    snippets.add(snippet);
	    fw.append(writeIn);
	    fw.close();
	    return writeIn;
	    
	}

	public void deleteSnippet(String id) throws IOException{
		
		String writeIn1 = "";

		
		Snippet snippetDelete = findById(id);

		for (int i=0; i<snippets.size(); i++){
			if (snippets.get(i).getId() == snippetDelete.getId()){
				deleteSnippetComments(snippets.get(i));
				snippets.remove(i);
				
				break;
			}
		}
		
		
			
		PrintWriter writer1 = new PrintWriter(new File(files.pathSnippets));
		writer1.print("");
	    writer1.close();
		
		
	    FileWriter fw = new FileWriter(files.pathSnippets, true); 
		
		if (snippets.size() == 0){
			writeIn1 = "";
		}else{
			for (int i=0; i<snippets.size(); i++){
				Snippet s = snippets.get(i);
				writeIn1 += s.getId() + "|" + s.getDescription() + "|" + s.getContent() + "|" + 
							s.getProgrammingLanguage() + "|"  +
							s.getUrlAddress() + "|" + 
							s.getRate().getPositiveRates() + "|" +
							s.getRate().getNegativeRates() + "|" +
							s.getCanBeCommented()+"|" +
							s.getUser().getUsername()+ "|" + 
							s.getDate().toString() + "\n";	
				
				
			}
		}	
			
		fw.write(writeIn1);
		fw.close();
	}
	
	public void deleteSnippetComments(Snippet s) throws IOException{
		CommentHandler ch = new CommentHandler();
		UserRatingHandler uh = new UserRatingHandler();
		
		ArrayList<Comment> comments1 = new ArrayList<Comment>();
		ArrayList<UserRating> rates1 = new ArrayList<UserRating>();
		
		for (int i=0; i<ch.getComments().size() ; i++){
			
			if (!ch.getComments().get(i).getSnippet().getId().equals(s.getId())){
				comments1.add(ch.getComments().get(i));
			}
		}
		for (int i=0; i<uh.getUser_rates().size(); i++){
			for (int j=0; j<comments1.size(); j++){
				if (uh.getUser_rates().get(i).getComment().getId().equals(comments1.get(j).getId())){
					rates1.add(uh.getUser_rates().get(i));
				}
			}
		}
		uh.deleteCommentMarks(rates1);
		ch.deleteSnippetComments(comments1);
	}
	
	public Snippet findById(String id){
		
		for (int i=0; i<snippets.size(); i++){
			if (snippets.get(i).getId().equals(id)){
				return snippets.get(i);
			}
		}
		return null;
	}

	public Integer calculateNextId(){
		int id=0;
		for (int i=0; i<snippets.size(); i++){
			if (Integer.parseInt(snippets.get(i).getId())>id){
				id = Integer.parseInt(snippets.get(i).getId());
			}
		}
		return id;
	}
	
	public void blockCommenting(Snippet s) throws IOException{
		String writeIn1 = "";

			
		int pos = 0;
		
		for (int i=0; i<snippets.size(); i++){
			if (snippets.get(i).getId().equals(s.getId())){
				pos = i;
				snippets.remove(i);
				break;
			}
		}
		
		if (s.getCanBeCommented()){
			s.setCanBeCommented(false);
		}else{
			s.setCanBeCommented(true);
		}
		
		
		snippets.add(pos, s);
			
		PrintWriter writer1 = new PrintWriter(new File(files.pathSnippets));
		writer1.print("");
	    writer1.close();
		
		
	    FileWriter fw = new FileWriter(files.pathSnippets, true); 
		
		if (snippets.size() == 0){
			writeIn1 = s.getId() + "|" + s.getDescription() + "|" + s.getContent() + "|" + 
					s.getProgrammingLanguage() + "|"  +
					s.getUrlAddress() + "|" + 
					s.getRate().getPositiveRates() + "|" +
					s.getRate().getNegativeRates() + "|" +
					s.getCanBeCommented() + "|" + s.getUser().getUsername() + "|" +
					s.getDate().toString();
		}else{
			for (int i=0; i<snippets.size(); i++){
				Snippet s1 = snippets.get(i);
				writeIn1 += s1.getId() + "|" + s1.getDescription() + "|" + s1.getContent() + "|" + 
						s1.getProgrammingLanguage() + "|"  +
						s1.getUrlAddress() + "|" + 
						s1.getRate().getPositiveRates() + "|" +
						s1.getRate().getNegativeRates() + "|" +
						s1.getCanBeCommented() + "|" + s1.getUser().getUsername()+
						"|" + s1.getDate().toString() +"\n";	
				
			}
		}	
			
		fw.write(writeIn1);
		fw.close();
	}

	public ArrayList<Snippet> searchByDescription(String filter){
		ArrayList<Snippet> found = new ArrayList<Snippet>();
		for (int i=0; i<snippets.size(); i++){
			if (snippets.get(i).getDescription().toLowerCase().contains(filter.toLowerCase())){
				found.add(snippets.get(i));
			}
		}
		
		return found;
	}

	public void filterByDate(Date filter){
		ArrayList<Snippet> snip = new ArrayList<Snippet>();
		for (int i=0; i<snippets.size(); i++){
			Date d = snippets.get(i).getDate();
			if (DateUtils.isSameDay(d, filter)){
				snip.add(snippets.get(i)); 
			}
		}
		
		
		snippets.clear();
		snippets = snip;
	}

	public void filterByProgrLanguage(String filter){
		ArrayList<Snippet> snip = new ArrayList<Snippet>();
		for (int i=0; i<snippets.size(); i++){
			if (snippets.get(i).getProgrammingLanguage().toLowerCase().equals(filter.toLowerCase())){
				snip.add(snippets.get(i)); 
			}
		}
		
		
		snippets.clear();
		snippets = snip;
	}
}

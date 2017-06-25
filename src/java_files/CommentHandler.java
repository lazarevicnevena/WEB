package java_files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CommentHandler {
	
	private static ArrayList<Comment> comments = new ArrayList<Comment>();
	
	private static Files files;
	
	public CommentHandler() throws IOException{
		super();
		comments = new ArrayList<Comment>();
		comments = parseCommentFile();
		for (int i=0; i<comments.size(); i++){
			if (comments.get(i).getText().equals("null")){
				comments.remove(i);
				break;
			}
		}
	}
	
	
	public ArrayList<Comment> parseCommentFile() throws IOException{
		BufferedReader reader1 = new BufferedReader(new FileReader(files.pathComments));
		BufferedReader reader2 = new BufferedReader(new FileReader(files.pathComments));
	    String line1 = "";
	    String line2 = "";
	    
		if ((line1 = reader1.readLine()) == null){
			reader1.close();
			reader2.close();
		    return comments;
		}
		else{
			
			while ((line2 = reader2.readLine()) != null)
			{
				String[] tokens = line2.split("\\|");
				Rate r = new Rate(Integer.parseInt(tokens[4]),
			    		Integer.parseInt(tokens[5]));
				
				User u = new User();
				AllUsers au = new AllUsers();
				for (int i=0; i<au.getRegisteredUsers().size(); i++){
					if (au.getRegisteredUsers().get(i).getUsername().equals(tokens[3])){
						u = au.getRegisteredUsers().get(i);
					}
				}
				java.util.Date d = null;
				//Unparseable date: "Fri Jun 16 21:50:42 CEST 2017"
				DateFormat readFormat = new SimpleDateFormat( "EEE MMM dd hh:mm:ss zzz yyyy");

			    DateFormat writeFormat = new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss");
			   
			    try {
			       d = (java.util.Date) readFormat.parse(tokens[2]);
			    } catch ( ParseException e ) {
			        e.printStackTrace();
			    }
			    
			    SnippetHandler sh = new SnippetHandler();
			    
			    Snippet s = sh.findById(tokens[6]);

			    //String formattedDate = "";
			    //if( date != null ) {
			    //formattedDate = writeFormat.format( date );
			   // }
				
			    Comment c = new Comment(tokens[1], d, u, r, s);
			    c.setId(tokens[0]);
			    comments.add(c);
			    tokens = null;
		    }
		    
		}
		reader2.close();
		reader1.close();
		
		return comments;
		
	}

	public ArrayList<Comment> getComments() {
		return comments;
	}

	public static void setComments(ArrayList<Comment> comments) {
		CommentHandler.comments = comments;
	}
	
	public Integer calculateNextIdComment(){
		int id=0;
		for (int i=0; i<comments.size(); i++){
			if (Integer.parseInt(comments.get(i).getId())>id){
				id = Integer.parseInt(comments.get(i).getId());
			}
		}
		return id;
	}
	
	public Comment findById(String id) throws IOException{
		
		for (int i=0; i<comments.size(); i++){
			if (comments.get(i).getId().equals(id)){
				
				return comments.get(i);
			}
		}
		return null;
	}

	public boolean addComment(Comment c) throws IOException{
		String writeIn = "";
		
	    FileWriter fw = new FileWriter(files.pathComments, true); 
		
	    if (comments.size() == 0){
			writeIn = c.getId() + "|" + c.getText()+ "|" + c.getDate() + "|" + 
					c.getUser().getUsername() + "|" + c.getRate().getPositiveRates() + "|" +
					c.getRate().getNegativeRates() + "|" + c.getSnippet().getId() + "\n";
					
					
		}else{
			for (int i=0; i<comments.size(); i++){
				if (comments.get(i).getText().equals(c.getText()) &&
						comments.get(i).getDate().equals(c.getDate())){
					fw.close();
					return false;
				}
				else{
					writeIn = c.getId() + "|"+ c.getText()+ "|" + c.getDate() + "|" + 
							c.getUser().getUsername() + "|" + c.getRate().getPositiveRates() 
							+ "|" + c.getRate().getNegativeRates() + "|" + c.getSnippet().getId() + "\n";
							
							
				}	
			}
		}
	    comments.add(c);
	    fw.append(writeIn);
	    fw.close();
	    return true;
	}

    public void deleteComment(Comment c) throws IOException{
    	String writeIn1 = "";

		for (int i=0; i<comments.size(); i++){
			if (comments.get(i).getId().equals(c.getId())){
				
				comments.remove(i);
				break;
				
				
			}
		}
		
			
		PrintWriter writer1 = new PrintWriter(new File(files.pathComments));
		writer1.print("");
	    writer1.close();
		
		
	    FileWriter fw = new FileWriter(files.pathComments, true); 
		
		if (comments.size() == 0){
			writeIn1 = "";
		}else{
			for (int i=0; i<comments.size(); i++){
				Comment c1 = comments.get(i);
				System.out.println("nema korisnika? " + comments.get(i).getUser().getUsername());
				writeIn1 += c1.getId() + "|"+ c1.getText()+ "|" + c1.getDate() + "|" + 
						c1.getUser().getUsername() + "|" + c1.getRate().getPositiveRates() 
						+ "|" + c1.getRate().getNegativeRates() + "|" + c1.getSnippet().getId() +"\n";	
				
				
			}
		}	
			
		fw.write(writeIn1);
		fw.close();
		
		
    }

    public void changeRate(Comment c,String posOrNeg) throws IOException{
    	String writeIn1 = "";

		
		int pos = 0;
		int posRate = 0;
		int negRate = 0;
		
		for (int i=0; i<comments.size(); i++){
			if (comments.get(i).getId().equals(c.getId())){
				pos = i;
				posRate = comments.get(i).getRate().getPositiveRates();
				negRate = comments.get(i).getRate().getNegativeRates();
				comments.remove(i);
				break;
			}
		}
		
		if (posOrNeg.equals("positive")){
			posRate ++;
		}else if(posOrNeg.equals("negative")){
			negRate++;
		}
		
		Rate newRate = new Rate();
		newRate.setPositiveRates(posRate);
		newRate.setNegativeRates(negRate);
		
		c.setRate(newRate);
		
		comments.add(pos, c);
			
		PrintWriter writer1 = new PrintWriter(new File(files.pathComments));
		writer1.print("");
	    writer1.close();
		
		
	    FileWriter fw = new FileWriter(files.pathComments, true); 
		
		if (comments.size() == 0){
			writeIn1 = c.getId() + "|" + c.getText()+ "|" + c.getDate() + "|" + 
					c.getUser().getUsername() + "|" + c.getRate().getPositiveRates() + "|" +
					c.getRate().getNegativeRates() + "|" + c.getSnippet().getId();
		}else{
			for (int i=0; i<comments.size(); i++){
				Comment c1 = comments.get(i);
				writeIn1 += c1.getId() + "|" + c1.getText()+ "|" + c1.getDate() + "|" + 
						c1.getUser().getUsername() + "|" + c1.getRate().getPositiveRates() + "|" +
						c1.getRate().getNegativeRates() + "|" + c1.getSnippet().getId() +"\n";	
				
			}
		}	
			
		fw.write(writeIn1);
		fw.close();
    	
    }
    
    public void deleteSnippetComments(ArrayList<Comment> commentsLeft) throws IOException{
    	String writeIn1 = "";

		comments.clear();
		
		comments = commentsLeft;
		
			
		PrintWriter writer1 = new PrintWriter(new File(files.pathComments));
		writer1.print("");
	    writer1.close();
		
		
	    FileWriter fw = new FileWriter(files.pathComments, true); 
		
		if (comments.size() == 0){
			writeIn1 = "";
		}else{
			for (int i=0; i<comments.size(); i++){
				Comment c1 = comments.get(i);
				writeIn1 += c1.getId() + "|"+ c1.getText()+ "|" + c1.getDate() + "|" + 
						c1.getUser().getUsername() + "|" + c1.getRate().getPositiveRates() 
						+ "|" + c1.getRate().getNegativeRates() + "|" + c1.getSnippet().getId() +"\n";	
				
				
			}
		}	
			
		fw.write(writeIn1);
		fw.close();
    }

    public void sortComments(){
    	Collections.sort(comments, new CommentComparator());
    	Collections.reverse(comments);
    	
    }
}

class CommentComparator implements Comparator<Comment> {
	@Override
	public int compare(Comment c1, Comment c2) {
		int rate1 = c1.getRate().getPositiveRates();
		int rate2 = c2.getRate().getPositiveRates();
 
		if (rate1 > rate2) {
			return 1;
		} else if (rate1 < rate2) {
			return -1;
		} else {
			return 0;
		}
	}
}

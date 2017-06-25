package java_files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class UserRatingHandler {
	
	private static ArrayList<UserRating> user_rates = new ArrayList<UserRating>();
	private Files files;
	
	public UserRatingHandler() throws IOException {
		super();
		user_rates = new ArrayList<UserRating>();
		user_rates = parseUserRatingFile();
		for (int i=0; i<user_rates.size(); i++){
			if (user_rates.get(i).getUser().equals("null")){
				user_rates.remove(i);
				break;
			}
		}
		
	}

	public ArrayList<UserRating> getUser_rates() {
		return user_rates;
	}

	public void setUser_rates(ArrayList<UserRating> user_rates) {
		this.user_rates = user_rates;
	}

	public ArrayList<UserRating> parseUserRatingFile() throws IOException{
		BufferedReader reader1 = new BufferedReader(new FileReader(files.pathUserMarks));
		BufferedReader reader2 = new BufferedReader(new FileReader(files.pathUserMarks));
	    String line1 = "";
	    String line2 = "";
	    
		if ((line1 = reader1.readLine()) == null){
			reader1.close();
			reader2.close();
		    return user_rates;
		}
		else{
			
			while ((line2 = reader2.readLine()) != null)
			{
				AllUsers au = new AllUsers();
				CommentHandler ch = new CommentHandler();
				
				String[] tokens = line2.split("\\|");
				
				String uname=tokens[0];
				User u = au.findUser(uname);
				Comment c = ch.findById(tokens[1]);
				

				UserRating ur = new UserRating();
				ur.setUser(u);
				ur.setComment(c);
				ur.setMark(Integer.parseInt(tokens[2]));
				user_rates.add(ur);
			    tokens = null;
		    }
		    
		}
		reader2.close();
		reader1.close();
		return user_rates;
	}

	public String addMark(UserRating userRating) throws IOException{
		
		String writeIn = "";
		
	    FileWriter fw = new FileWriter(files.pathUserMarks, true); 
		
	    if (user_rates.size() == 0){
			writeIn =userRating.getUser().getUsername() + "|"+ userRating.getComment().getId()
					+ "|" + userRating.getMark() + "\n";
		}else{
			for (int i=0; i<user_rates.size(); i++){
				if (user_rates.get(i).getUser().getUsername().equals(userRating.getUser().getUsername()) &&
						user_rates.get(i).getComment().getId().equals(userRating.getComment().getId())){
					fw.close();
					return "";
				}
				else{
					writeIn = userRating.getUser().getUsername() + "|"+ userRating.getComment().getId()
							+ "|" + userRating.getMark() + "\n";
				}	
			}
		}
	    
	    user_rates.add(userRating);
	    fw.append(writeIn);
	    fw.close();
	    return writeIn;
		
	}

	
	public void deleteCommentMarks(ArrayList<UserRating> marksLeft) throws IOException{
		String writeIn1 = "";

		user_rates.clear();	
		user_rates = marksLeft;
		
			
		PrintWriter writer1 = new PrintWriter(new File(files.pathUserMarks));
		writer1.print("");
	    writer1.close();
		
		
	    FileWriter fw = new FileWriter(files.pathUserMarks, true); 
		
		if (user_rates.size() == 0){
			writeIn1 = "";
		}else{
			for (int i=0; i<user_rates.size(); i++){
				UserRating c1 = user_rates.get(i);
				writeIn1 += c1.getUser().getUsername() + "|"+ 
				c1.getComment().getId() +"|" + c1.getMark() +"\n";	
				
				
			}
		}	
			
		fw.write(writeIn1);
		fw.close();
		
	}
	
	
	public UserRating findByUserAndComment(User u, Comment c){
		for (int i=0; i<user_rates.size(); i++){
			if (user_rates.get(i).getUser().getUsername().equals(u.getUsername()) &&
					user_rates.get(i).getComment().getId().equals(c.getId())){
				return user_rates.get(i);
			}
		}
		return null;
	}

	public ArrayList<UserRating> getLeftMarks(ArrayList<Comment> comments){
		ArrayList<UserRating> rates = new ArrayList<UserRating>();
		for (int i=0; i<user_rates.size(); i++){
			for (int j=0; j<comments.size(); j++){
				
				if (user_rates.get(i).getComment() != null){
					if (user_rates.get(i).getComment().getId().equals(comments.get(j).getId())){
						rates.add(user_rates.get(i));
					
					}
				}
				
			}
		}
		return rates;
	}

}


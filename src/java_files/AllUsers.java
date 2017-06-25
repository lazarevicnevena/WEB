package java_files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import com.sun.javafx.scene.shape.PathUtils;



public class AllUsers {
	private static ArrayList<User> registeredUsers = new ArrayList<User>();
	//private static ArrayList<User> admins = new ArrayList<User>();
	
	private static Files files;
	
	
	public AllUsers() throws IOException {
		super();
		registeredUsers = new ArrayList<User>();
		//admins = new ArrayList<User>();
		registeredUsers = parseRegisteredUsersFile();
		//admins = parseAdminFile();
		/*
		for (int i=0; i<registeredUsers.size(); i++){
			if (registeredUsers.get(i).getUsername().equals("null")){
				registeredUsers.remove(i);
				break;
			}
		}
		for (int i=0; i<admins.size(); i++){
			if (admins.get(i).getUsername().equals("null")){
				admins.remove(i);
				break;
			}
		}
		*/
	}

	public ArrayList<User> getRegisteredUsers() throws IOException {
		return registeredUsers;
	}

	public void setRegisteredUsers(ArrayList<User> registeredUsers) {
		this.registeredUsers = registeredUsers;
	}
/*
	public ArrayList<User> getAdmins() throws IOException {
		return admins;
	}

	
	public static ArrayList<User> parseAdminFile() throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(files.pathAdmins));
	    String line;
	    while ((line = reader.readLine()) != null)
	    {
	      String[] tokens = line.split("\\|");
	      User admin = new User(tokens[0], tokens[1], tokens[2], tokens[3],
	    		  tokens[4], tokens[5], tokens[6], tokens[7]);
	      admin.setBlocked(false);
	      admin.setPicture(tokens[9].getBytes());
	      admins.add(admin);
	    }
	    reader.close();
		return admins;
	}
	*/
	public static ArrayList<User> parseRegisteredUsersFile() throws IOException{
		
		
		BufferedReader reader1 = new BufferedReader(new FileReader(files.pathUsers));
		BufferedReader reader2 = new BufferedReader(new FileReader(files.pathUsers));
	    String line1 = "";
	    String line2 = "";
	    
		if ((line1 = reader1.readLine()) == null){
			reader1.close();
			reader2.close();
		    return registeredUsers;
		}
		else{
			
			while ((line2 = reader2.readLine()) != null)
			{
				String[] tokens = line2.split("\\|");
			    User user = new User(tokens[0], tokens[1], tokens[2], tokens[3],
			    		tokens[4], tokens[5], tokens[6], tokens[7]);
			    if (tokens[8].equals("true")){
			    	user.setBlocked(true);
			    }else{
			    	user.setBlocked(false);
			    }
			    user.setPicture(tokens[9]);
			    registeredUsers.add(user);
			    tokens = null;
		    }
		    
		}
		reader2.close();
		reader1.close();
		return registeredUsers;
	}
	
	
	
	
	public static String registrationSucces(User user) throws IOException{
		
		String writeIn = "";

	    FileWriter fw = new FileWriter(files.pathUsers, true); 
	    user.setPicture(files.picUser);
 
		
		if (registeredUsers.size() == 0){
			writeIn = user.getUsername() + "|" + user.getPassword() + "|" + 
					user.getFirstName() + "|" + user.getLastName() + "|" +
					user.getTelephoneNum() + "|" + user.getEmail() + "|" + 
					user.getAddress() + "|" +
					user.getRole()+ "|" + user.getBlocked() + "|" + "null" +"\n";
		}else{
			for (int i=0; i<registeredUsers.size(); i++){
				if (registeredUsers.get(i).getUsername().equals(user.getUsername())){
					fw.close();
					return "";
				}
				else{
					writeIn = user.getUsername() + "|" + user.getPassword() + "|" + 
							user.getFirstName() + "|" + user.getLastName() + "|" +
							user.getTelephoneNum() + "|" + user.getEmail() + "|" + 
							user.getAddress() + "|" + 
							user.getRole() + "|" + user.getBlocked() + "|" + "null" +"\n";	
				}	
			}
		}
		
		
		
		
		registeredUsers.add(user);
	    fw.append(writeIn);
	    fw.close();
		return writeIn;
	}
	
	
	public static String loginSuccessful(String uname, String pwd, HttpServletRequest
			request) throws IOException{
		
		if (registeredUsers.size() > 0){
			for (int i=0; i<registeredUsers.size();i++){
				if (registeredUsers.get(i).getUsername().equals(uname)
						&& registeredUsers.get(i).getPassword().equals(pwd)){
					request.getSession().setAttribute("loggedUser", registeredUsers.get(i));
					if (registeredUsers.get(i).getPicture().equals("null")){
						request.getSession().setAttribute("pic", "yes");
					}else{
						request.getSession().setAttribute("pic", "no");
					}
					request.getSession().setAttribute("altPic", "\\files\\user.png");
					return registeredUsers.get(i).getRole();
				}
			}
		}
		
		return "";
		
	}

	public static boolean updateProfile(User user) throws IOException{
		String writeIn1 = "";

		boolean flag1 = false;
		
		
		if (registeredUsers.size() > 0){
			for (int i=0; i<registeredUsers.size(); i++){
				if (registeredUsers.get(i).getUsername().equals(user.getUsername())){
					
					registeredUsers.remove(i);
					flag1 = true;
					break;
				}
			}
		}
		
		if (flag1){
			registeredUsers.add(user);
			
			PrintWriter writer1 = new PrintWriter(new File(files.pathUsers));
			writer1.print("");
			writer1.close();
		}
		
	    FileWriter fw = new FileWriter(files.pathUsers, true); 
		
		if (registeredUsers.size() == 0){
			writeIn1 = user.getUsername() + "|" + user.getPassword() + "|" + 
					user.getFirstName() + "|" + user.getLastName() + "|" +
					user.getTelephoneNum() + "|" + user.getEmail() + "|" + 
					user.getAddress() + "|" +
					user.getRole()+ "|" + user.getBlocked() + "|" 
					+ user.getPicture();
		}else{
			for (int i=0; i<registeredUsers.size(); i++){
				User u = registeredUsers.get(i);
				writeIn1 += u.getUsername() + "|" + u.getPassword() + "|" + 
							u.getFirstName() + "|" + u.getLastName() + "|" +
							u.getTelephoneNum() + "|" + u.getEmail() + "|" + 
							u.getAddress() + "|" + 
							u.getRole() + "|" + u.getBlocked() + "|"
							+ u.getPicture() + "\n";	
				
			}
			
			
		}
		
		if (flag1){
			 fw.write(writeIn1);
			 fw.close();
		}
		
	   
		return true;
	}
	
	public static void blockUser(String username) throws IOException{
		String writeIn1 = "";

		int pos = 0;
		
		User userBlocked = new User();

		for (int i=0; i<registeredUsers.size(); i++){
			if (registeredUsers.get(i).getUsername().equals(username)){
				userBlocked = registeredUsers.get(i);
				pos = i;
				registeredUsers.remove(i);
				
				break;
			}
		}
		
		if (userBlocked.getBlocked()){
			userBlocked.setBlocked(false);
		}else{
			userBlocked.setBlocked(true);
		}
		
		
		registeredUsers.add(pos, userBlocked);
			
		PrintWriter writer1 = new PrintWriter(new File(files.pathUsers));
		writer1.print("");
	    writer1.close();
		
		
	    FileWriter fw = new FileWriter(files.pathUsers, true); 
		
		if (registeredUsers.size() == 0){
			writeIn1 = userBlocked.getUsername() + "|" + userBlocked.getPassword() + "|" + 
					userBlocked.getFirstName() + "|" + userBlocked.getLastName() + "|" +
					userBlocked.getTelephoneNum() + "|" + userBlocked.getEmail() + "|" + 
					userBlocked.getAddress() + "|" +
					userBlocked.getRole()+ "|" + userBlocked.getBlocked() + "|" + userBlocked.getPicture();
		}else{
			for (int i=0; i<registeredUsers.size(); i++){
				User u = registeredUsers.get(i);
				writeIn1 += u.getUsername() + "|" + u.getPassword() + "|" + 
							u.getFirstName() + "|" + u.getLastName() + "|" +
							u.getTelephoneNum() + "|" + u.getEmail() + "|" + 
							u.getAddress() + "|" + 
							u.getRole() + "|" + u.getBlocked() + "|" + u.getPicture() + "\n";	
				
			}
		}	
			
		fw.write(writeIn1);
		fw.close();
		
		
	}
	
	public ArrayList<User> searchByUsername(String uname){
    	ArrayList<User> users = new ArrayList<User>();
    	
    	for (int i=0; i<registeredUsers.size(); i++){
    		if (registeredUsers.get(i).getUsername().toLowerCase().contains(uname.toLowerCase())){
    			users.add(registeredUsers.get(i));	
    		}
    	}
    	return users;
    }

	public static User findUser(String uname) throws IOException{
		
		for(int i=0;i<registeredUsers.size();i++){
			if (uname.equals(registeredUsers.get(i).getUsername())){
				return registeredUsers.get(i);
			}
		}
		return null;
	}
}

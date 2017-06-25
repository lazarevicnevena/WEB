package java_files;


public class User implements java.io.Serializable {
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String role;
	private String telephoneNum;
	private String email;
	private String address;
	private String picture;
	private boolean blocked;

	
	public User(String username, String password, String firstName, String lastName, 
			String telephoneNum, String email, String address, String role) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.telephoneNum = telephoneNum;
		this.email = email;
		this.address = address;
		this.role = role;
	}
	
	public User() {
		super();
		this.blocked = false;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getTelephoneNum() {
		return telephoneNum;
	}
	public void setTelephoneNum(String telephoneNum) {
		this.telephoneNum = telephoneNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPicture() {
		
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", role=" + role + ", telephoneNum=" + telephoneNum + ", email=" + email + ", address="
				+ address + ", picture=" + picture + ", loggedIn=" + "]";
	}

	public boolean getBlocked() {
		return blocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.blocked = isBlocked;
	}
	
	
	
	
}

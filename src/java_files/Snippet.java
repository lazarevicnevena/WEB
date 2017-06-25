package java_files;

import java.util.Date;

public class Snippet implements java.io.Serializable {

	private String description;
	private String content;
	private String programmingLanguage;
	private String urlAddress;
	private Rate rate;
	private boolean canBeCommented;
	private String id;
	private User user;
	private Date date;
	
	Rate r = new Rate();
	
	public Snippet() {
		super();
		Rate r = new Rate();
		r.setNegativeRates(0);
		r.setPositiveRates(0);
		this.rate = r;
		canBeCommented = true;
		date = new Date();
	}
	
	
	
	public Snippet(String description, String content, String programmingLanguage, String urlAddress, 
			Rate r) {
		super();
		this.description = description;
		this.content = content;
		this.programmingLanguage = programmingLanguage;
		this.urlAddress = urlAddress;
		this.rate = r;
		
	}


	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getProgrammingLanguage() {
		return programmingLanguage;
	}
	public void setProgrammingLanguage(String programmingLanguage) {
		this.programmingLanguage = programmingLanguage;
	}
	public String getUrlAddress() {
		return urlAddress;
	}
	public void setUrlAddress(String urlAddress) {
		this.urlAddress = urlAddress;
	}
	
	public Rate getRate() {
		return rate;
	}

	public void setRate(Rate rate) {
		this.rate = rate;
	}
	
	public boolean getCanBeCommented() {
		return canBeCommented;
	}

	public void setCanBeCommented(boolean canBeCommented) {
		this.canBeCommented = canBeCommented;
	}

	public String getId() {
		return id;
	}


	public void setId(String nextId) {
		this.id = nextId;
	}


	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}

	
	

}

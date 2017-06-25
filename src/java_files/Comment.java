package java_files;

import java.io.IOException;
import java.util.Date;

public class Comment implements java.io.Serializable {

	private String text;
	private Date date;
	private User user;
	private Rate rate;
	private Snippet snippet;
	private String id;
	
	
	public Comment() {
		super();
	}


	public Comment(String text, Date date, User user, Rate rate, Snippet s) {
		super();
		this.text = text;
		this.date = date;
		this.user = user;
		this.rate = rate;
		this.snippet = s;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public User getUser() {
		return user;
	}

	
	public void setUser(User user) {
		this.user = user;
	}


	public Rate getRate() {
		return rate;
	}


	public void setRate(Rate rate) {
		this.rate = rate;
	}


	public Snippet getSnippet() {
		return snippet;
	}


	public void setSnippet(Snippet snippet) {
		this.snippet = snippet;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	
	
	
}

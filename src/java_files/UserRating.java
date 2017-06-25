package java_files;

public class UserRating {

	private User user;
	private Comment comment;
	private Integer mark;
	
	
	
	public UserRating() {
		super();
	}


	


	public UserRating(User user, Comment comment, Integer mark) {
		super();
		this.user = user;
		this.comment = comment;
		this.mark = mark;
	}





	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public Comment getComment() {
		return comment;
	}



	public void setComment(Comment comment) {
		this.comment = comment;
	}




	public Integer getMark() {
		return mark;
	}




	public void setMark(Integer mark) {
		this.mark = mark;
	}


	
	
	
}

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java_files.Comment;
import java_files.CommentHandler;
import java_files.Snippet;
import java_files.SnippetHandler;
import java_files.User;
import java_files.UserRating;
import java_files.UserRatingHandler;

/**
 * Servlet implementation class PositiveMarkServlet
 */
@WebServlet("/PositiveMarkServlet/*")
public class PositiveMarkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PositiveMarkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		String id1 = request.getPathInfo().substring(1);
		
		User u = (User) request.getSession().getAttribute("loggedUser");
		
		CommentHandler ch = new CommentHandler();

		Comment c = ch.findById(id1);
		UserRating ur = new UserRating();
		ur.setUser(u);
		ur.setComment(c);
		ur.setMark(1);
		
		UserRatingHandler urh = new UserRatingHandler();
		
		UserRating ur1 = urh.findByUserAndComment(u, c);
		String errorMsg = " ";
		if (ur1 == null){
			errorMsg = " ";
			urh.addMark(ur);
			ch.changeRate(c, "positive");
		}
		else{
			errorMsg = "You already rated this comment!";
		}
		
		
		request.getSession().setAttribute("errorMsg", errorMsg);
		
		request.getSession().setAttribute("comments", ch.getComments());
		request.getSession().setAttribute("userMarks", urh.getUser_rates());
		
		if (u.getRole().equals("ordinary")){
			response.sendRedirect("/Project/jsp_files/homePage.jsp");	
		}else{
			response.sendRedirect("/Project/jsp_files/adminHomePage.jsp");	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

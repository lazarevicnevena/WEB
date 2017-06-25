package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java_files.Comment;
import java_files.CommentHandler;
import java_files.User;

/**
 * Servlet implementation class SortComments
 */
@WebServlet("/SortComments")
public class SortComments extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SortComments() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		CommentHandler ch = new CommentHandler();
		
		ch.sortComments();
		
		User u = (User) request.getSession().getAttribute("loggedUser");
		request.getSession().setAttribute("comments", ch.getComments());
		if (u.getRole().equals("ordinary")){
			response.sendRedirect("/Project/jsp_files/homePage.jsp");	
		}else if (u.getRole().equals("admin")){
			response.sendRedirect("/Project/jsp_files/adminHomePage.jsp");	
		}else{
			response.sendRedirect("/Project/jsp_files/guestHomePage.jsp");
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

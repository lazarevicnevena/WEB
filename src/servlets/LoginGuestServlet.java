package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java_files.AllUsers;
import java_files.CommentHandler;
import java_files.ProgrammingLanguage;
import java_files.SnippetHandler;
import java_files.User;
import java_files.UserRatingHandler;

/**
 * Servlet implementation class LoginGuestServlet
 */
@WebServlet("/LoginGuestServlet")
public class LoginGuestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginGuestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ProgrammingLanguage pr = new ProgrammingLanguage();
		CommentHandler ch = new CommentHandler();
		SnippetHandler sh = new SnippetHandler();
		UserRatingHandler urh = new UserRatingHandler();
		AllUsers au = new AllUsers();
		
		User u = new User();
		u.setUsername("guest");
		u.setAddress("guest");
		u.setBlocked(false);
		u.setEmail("guest");
		u.setFirstName("guest");
		u.setFirstName("guest");
		u.setRole("guest");
		u.setTelephoneNum("guest");
		u.setPicture("guest");
		u.setPassword("guest");
		
		au.registrationSucces(u);
		
		request.getSession().setAttribute("errorMsg", " ");
		
		request.getSession().setAttribute("altPic", "\\files\\user.png");
		request.getSession().setAttribute("languages", pr.getProgramming_languages());
		request.getSession().setAttribute("snippets", sh.getSnippets());
		request.getSession().setAttribute("comments", ch.getComments());
		request.getSession().setAttribute("userMarks",urh.getUser_rates());
		request.getSession().setAttribute("loggedUser", u);
		request.getSession().setAttribute("role","guest");
		response.sendRedirect("/Project/jsp_files/guestHomePage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

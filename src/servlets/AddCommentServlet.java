package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java_files.AllUsers;
import java_files.Comment;
import java_files.CommentHandler;
import java_files.Rate;
import java_files.Snippet;
import java_files.SnippetHandler;
import java_files.User;

/**
 * Servlet implementation class AddCommentServlet
 */
@WebServlet("/AddCommentServlet/*")
public class AddCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String id =request.getPathInfo().substring(1);
		
		SnippetHandler sh = new SnippetHandler();
		
		Snippet sp = sh.findById(id);
		CommentHandler ch = new CommentHandler();
		
		AllUsers au = new AllUsers();
		
		String txt = request.getParameter("txtComment");
		User u = (User) request.getSession().getAttribute("loggedUser");
		
		User u1 = au.findUser(u.getUsername());
	
		Date d = new Date();
		Comment c = new Comment();
		c.setText(txt);
		c.setDate(d);
		c.setUser(u1);
		Rate r = new Rate();
		r.setNegativeRates(0);
		r.setPositiveRates(0);
		c.setRate(r);
		c.setSnippet(sp);
		Integer num = ch.calculateNextIdComment() +1;
		c.setId(num.toString());
		
		
		boolean added = ch.addComment(c);
		
		request.getSession().setAttribute("comments", ch.getComments());
		request.getSession().setAttribute("errorMsg", " ");
		if (u.getRole().equals("ordinary")){
			response.sendRedirect("/Project/jsp_files/homePage.jsp");	
		}else if(u.getRole().equals("admin")){
			response.sendRedirect("/Project/jsp_files/adminHomePage.jsp");	
		}else if(u.getRole().equals("guest")){
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

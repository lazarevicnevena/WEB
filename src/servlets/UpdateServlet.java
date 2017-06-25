package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java_files.AllUsers;
import java_files.CommentHandler;
import java_files.User;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String fname = request.getParameter("firstName");
		String lname = request.getParameter("lastName");
		String phone = request.getParameter("telephoneNum");
		String mail = request.getParameter("email");
		String addr = request.getParameter("address");
		
		User u = (User) request.getSession().getAttribute("loggedUser");
		
		String role = u.getRole();
		
		AllUsers allUsers = new AllUsers();
		
		User newUser = new User(u.getUsername(), u.getPassword(), fname, lname, phone, mail, addr, role);
		newUser.setBlocked(u.getBlocked());
		newUser.setPicture(u.getPicture());
		
		boolean updated = AllUsers.updateProfile(newUser);
		
		request.getSession().setAttribute("errorMsg", " ");
		
		if (!updated){
			String message = "Error";
			request.getSession().setAttribute("message", message);	
			
			
		}
		CommentHandler ch = new CommentHandler();
		request.getSession().setAttribute("comments", ch.getComments());
		if (role.equals("ordinary")){
			request.getSession().setAttribute("loggedUser", newUser);
			response.sendRedirect("/Project/jsp_files/homePage.jsp");
			
		}
		else{
			request.getSession().setAttribute("loggedUser", newUser);
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

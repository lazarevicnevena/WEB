package servlets;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java_files.AllUsers;
import java_files.User;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String uname = request.getParameter("username");
		String pwd = request.getParameter("password");
		String fname = request.getParameter("firstName");
		String lname = request.getParameter("lastName");
		String phone = request.getParameter("telephoneNum");
		String mail = request.getParameter("email");
		String addr = request.getParameter("address");
		String role = "ordinary";
		
		
		AllUsers allUsers = new AllUsers();
		
		User newUser = new User(uname, pwd, fname, lname, phone, mail, addr, role);
		newUser.setBlocked(false);
		newUser.setPicture("null");
		
		String answer = AllUsers.registrationSucces(newUser);
		
		request.getSession().setAttribute("errorMsg", " ");
		
		String message = "yes";
		
		
		if (answer == ""){
			request.getSession().setAttribute("messageReg", message);	
			response.sendRedirect("/Project/jsp_files/registration.jsp");
		}else{
			message = "no";
			request.getSession().setAttribute("messageReg", message);	
			response.sendRedirect("/Project/jsp_files/login.jsp");
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

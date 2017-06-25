package servlets;

import java.io.IOException;

import javax.print.DocFlavor.STRING;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java_files.AllUsers;
import java_files.CommentHandler;
import java_files.ProgrammingLanguage;
import java_files.SnippetHandler;
import java_files.UserRatingHandler;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("hej");
		String uname = request.getParameter("username");
		String pwd = request.getParameter("password");
				
		AllUsers allUsers = new AllUsers();
				
		String answer = allUsers.loginSuccessful(uname, pwd, request);
		ProgrammingLanguage pr = new ProgrammingLanguage();
		
		SnippetHandler sh = new SnippetHandler();
		
		CommentHandler ch = new CommentHandler();
		
				
		UserRatingHandler urh = new UserRatingHandler();
		request.getSession().setAttribute("errorMsg", " ");
		request.getSession().setAttribute("messageProgr", false);	
		request.getSession().setAttribute("divShown", "none");
		
		String message = "";
		if (answer.equals("")){
			message = "Incorrect username or password. Try again!";
			request.getSession().setAttribute("message", message);	
			response.sendRedirect("/Project/jsp_files/login.jsp");	
			
		}
		else if(answer.equals("ordinary")){
			request.getSession().setAttribute("languages", pr.getProgramming_languages());
			request.getSession().setAttribute("snippets", sh.getSnippets());
			request.getSession().setAttribute("comments", ch.getComments());
			request.getSession().setAttribute("userMarks",urh.getUser_rates());
			request.getSession().setAttribute("role","ordinary");
			response.sendRedirect("http://localhost:8080/Project/jsp_files/homePage.jsp");	
		}else if(answer.equals("admin")){
			request.getSession().setAttribute("languages", pr.getProgramming_languages());
			request.getSession().setAttribute("users", allUsers.getRegisteredUsers());
			request.getSession().setAttribute("snippets", sh.getSnippets());
			request.getSession().setAttribute("comments", ch.getComments());
			request.getSession().setAttribute("userMarks",urh.getUser_rates());
			request.getSession().setAttribute("role","admin");
			response.sendRedirect("http://localhost:8080/Project/jsp_files/adminHomePage.jsp");	
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

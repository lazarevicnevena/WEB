package servlets;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java_files.Snippet;
import java_files.SnippetHandler;
import java_files.User;

/**
 * Servlet implementation class DeleteSnippetServlet
 */
@WebServlet("/DeleteSnippetServlet/*")
public class DeleteSnippetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteSnippetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String id = request.getPathInfo().substring(1);
			
		SnippetHandler sh = new SnippetHandler();
		
		sh.deleteSnippet(id);
		
		User u = (User) request.getSession().getAttribute("loggedUser");
		
		request.getSession().setAttribute("snippets", sh.getSnippets());
		request.getSession().setAttribute("errorMsg", " ");
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

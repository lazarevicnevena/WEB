package servlets;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java_files.AllUsers;
import java_files.Snippet;
import java_files.SnippetHandler;
import java_files.User;

/**
 * Servlet implementation class SnippetCreationServlet
 */
@WebServlet("/SnippetCreationServlet")
public class SnippetCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SnippetCreationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		SnippetHandler sh = new SnippetHandler();
		
		User u = (User) request.getSession().getAttribute("loggedUser");
		
		String description = request.getParameter("description");
		String content = request.getParameter("snippetCode");
		String progrLanguage = request.getParameter("language");
		String url = request.getParameter("urlAddr");
		
		Snippet newSnippet = new Snippet();
		newSnippet.setContent(content);
		newSnippet.setDescription(description);
		newSnippet.setUrlAddress(url);
		newSnippet.setProgrammingLanguage(progrLanguage);
		newSnippet.setCanBeCommented(true);
		newSnippet.setUser(u);
		Integer num = sh.calculateNextId()+1;
		newSnippet.setId(num.toString());
		
		
		
		
		String answer = sh.addSnippet(newSnippet);
		request.getSession().setAttribute("errorMsg", " ");
		
		if (answer == ""){
			String message = "Error";
			request.getSession().setAttribute("message", message);	
		}
		request.getSession().setAttribute("snippets", sh.getSnippets());
		
		
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

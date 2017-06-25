package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java_files.SnippetHandler;
import java_files.User;

/**
 * Servlet implementation class FilterLangServlet
 */
@WebServlet("/FilterLangServlet")
public class FilterLangServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilterLangServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String progrLanguage = request.getParameter("language");
		

		SnippetHandler sh = new SnippetHandler();
		
		sh.filterByProgrLanguage(progrLanguage);
		
		User u = (User) request.getSession().getAttribute("loggedUser");
		request.getSession().setAttribute("snippets", sh.getSnippets());
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

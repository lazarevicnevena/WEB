package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java_files.ProgrammingLanguage;
import java_files.User;
import jdk.nashorn.internal.ir.RuntimeNode.Request;

/**
 * Servlet implementation class ProgrammingLanguage
 */
@WebServlet("/ProgrammingLanguageServlet")
public class ProgrammingLanguageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProgrammingLanguageServlet() {
        super();
        
       
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("language");
		
		ProgrammingLanguage pL = new ProgrammingLanguage();
		
		
		boolean added = pL.addProgrammingLanguages(name);
		
		request.getSession().setAttribute("errorMsg", " ");
		
		if (!added){
			//String message = "Error! Programming language already exists!";
			request.getSession().setAttribute("messageProgr", true);
			request.getSession().setAttribute("divShown", "programLAdd");
		}else{
			request.getSession().setAttribute("messageProgr", false);
			request.getSession().setAttribute("divShown", "none");
		}
		
		User u = (User) request.getSession().getAttribute("loggedUser");
		request.getSession().setAttribute("languages", pL.getProgramming_languages());
		
		if (u.getRole().equals("admin")){
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

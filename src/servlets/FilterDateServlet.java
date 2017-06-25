package servlets;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java_files.SnippetHandler;
import java_files.User;

/**
 * Servlet implementation class FilterDateServlet
 */
@WebServlet("/FilterDateServlet")
public class FilterDateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilterDateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String date1 = request.getParameter("date"); 
		
		
		String[] tokens = date1.split("/");
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Integer.parseInt(tokens[2]));
		calendar.set(Calendar.MONTH, Integer.parseInt(tokens[1]) - 1);
		calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(tokens[0]));
	
		Date filter = calendar.getTime();
		
		
		SnippetHandler sh = new SnippetHandler();
		
		sh.filterByDate(filter);
		
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

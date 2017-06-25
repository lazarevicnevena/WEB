package servlets;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java_files.Comment;
import java_files.CommentHandler;
import java_files.Snippet;
import java_files.SnippetHandler;

/**
 * Servlet implementation class SearchSnippets
 */
@WebServlet("/SearchSnippets")
public class SearchSnippets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchSnippets() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		String filter = request.getParameter("filter");
		
		
		SnippetHandler sh = new SnippetHandler();
		
		ArrayList<Snippet> snippetsFound = new ArrayList<Snippet>();
		
		
		snippetsFound = sh.searchByDescription(filter);
		
		request.getSession().setAttribute("searchedSnippets", snippetsFound);
		
		response.sendRedirect("http://localhost:8080/Project/jsp_files/searchedSnippets.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java_files.Snippet;
import java_files.SnippetHandler;

/**
 * Servlet implementation class DisableCommentingServlet
 */
@WebServlet("/DisableCommentingServlet/*")
public class DisableCommentingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisableCommentingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String id1 = request.getPathInfo().substring(1);
		
		SnippetHandler sh = new SnippetHandler();
		
		Snippet s = sh.findById(id1);
		
		sh.blockCommenting(s);
		
		request.getSession().setAttribute("snippets", sh.getSnippets());
		
		request.getSession().setAttribute("errorMsg", " ");
		response.sendRedirect("/Project/jsp_files/adminHomePage.jsp");	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java_files.AllUsers;
import java_files.CommentHandler;

/**
 * Servlet implementation class BlockUserServlet
 */
@WebServlet("/BlockUserServlet/*")
public class BlockUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlockUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String uname = request.getPathInfo().substring(1);
		
		AllUsers allusers = new AllUsers();
		
		
		
		allusers.blockUser(uname);
		
		CommentHandler ch = new CommentHandler();
		
		request.getSession().setAttribute("errorMsg", " ");
		request.getSession().setAttribute("users", allusers.getRegisteredUsers());
		request.getSession().setAttribute("comments", ch.getComments());
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

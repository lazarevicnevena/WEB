package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java_files.Comment;
import java_files.CommentHandler;
import java_files.Snippet;
import java_files.SnippetHandler;
import java_files.User;
import java_files.UserRatingHandler;

/**
 * Servlet implementation class DeleteCommentServlet
 */
@WebServlet("/DeleteCommentServlet/*")
public class DeleteCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String id1 = request.getPathInfo().substring(1);
		
		CommentHandler ch = new CommentHandler();
		
		Comment c = ch.findById(id1);
		
		ch.deleteComment(c);
		
		UserRatingHandler urh = new UserRatingHandler();
		
		System.out.println("komentara imam: " + ch.getComments().size());
		System.out.println("ocjena imam: " + urh.getUser_rates().size());
		
		urh.deleteCommentMarks(urh.getLeftMarks(ch.getComments()));
		
		User u = (User) request.getSession().getAttribute("loggedUser");
		
		String role = u.getRole();
		request.getSession().setAttribute("errorMsg", " ");
		
		if(role.equals("ordinary")){
			request.getSession().setAttribute("comments", ch.getComments());
			response.sendRedirect("/Project/jsp_files/homePage.jsp");	
		}else if(role.equals("admin")){
			request.getSession().setAttribute("comments", ch.getComments());
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

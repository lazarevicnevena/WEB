package servlets;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;


import java_files.AllUsers;
import java_files.CommentHandler;
import java_files.User;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = (User)request.getSession().getAttribute("loggedUser");
		
		ServletRequestContext ctx = new ServletRequestContext(request);
		if(ServletFileUpload.isMultipartContent(ctx)){
		
			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// Set factory constraints
			factory.setSizeThreshold(2000000);
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			// Set overall request size constraint
			upload.setSizeMax(3000000);
			// Parse the request
			try {
				List<FileItem> i = upload.parseRequest(request);
				for (FileItem item : i) {
					String fileName = item.getName();
					if (!item.isFormField()){
						long sizeInBytes = item.getSize();
						int idx = fileName.lastIndexOf("\\");
						if (idx != -1)
							fileName = fileName.substring(idx + 1);
						if (sizeInBytes != 0) {
							 new File((getServletContext()
									.getRealPath("")
									+ "/files/" + user.getUsername())).mkdirs();
							
							File uploadedFile = new File(getServletContext()
									.getRealPath("")
									+ "/files/"+user.getUsername()+"/"+fileName);
							
							user.setPicture("\\files\\"+user.getUsername()+"\\"+fileName);
							
							AllUsers au = new AllUsers();
							au.updateProfile(user);
							
							request.getSession().setAttribute("pic", "nova");
							item.write(uploadedFile);
							
						}
					}
				}
				request.getSession().setAttribute("loggedUser", user);
				CommentHandler ch = new CommentHandler();
				request.getSession().setAttribute("comments", ch.getComments());
				request.getSession().setAttribute("errorMsg", " ");
				if (user.getRole().equals("ordinary")){
					response.sendRedirect("/Project/jsp_files/homePage.jsp");	
				}else{
					AllUsers au = new AllUsers();
					request.getSession().setAttribute("users", au.getRegisteredUsers());
					response.sendRedirect("/Project/jsp_files/adminHomePage.jsp");	
				}
				
				
				return;
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Process the uploaded items
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

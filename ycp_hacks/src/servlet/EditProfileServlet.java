package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.UserController;
import model.User;
import persist.DatabaseProvider;
import persist.DerbyDatabase;
import persist.IDatabase;

public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	HttpSession session = null;
	IDatabase db = null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Edit Profile Servlet: doGet");
		session = req.getSession();
		
		/*Doesn't allow logged out client to access edit_profile page
		 * as there is no user to edit
		 * forwards back to the index
		 */
		if(session.getAttribute("currentUser") == null) {
			resp.sendRedirect(req.getContextPath() + "/index");
		}
		
		if(DatabaseProvider.getInstance() == null) {
			DatabaseProvider.setInstance(new DerbyDatabase());//------------------------------------------DATABASE MARKER
			db = DatabaseProvider.getInstance();
		}else {
			db = DatabaseProvider.getInstance();
		}
		
		req.getRequestDispatcher("/_view/editProfilePage.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			System.out.println("Edit Profile Servlet: doPost");
			session = req.getSession();
			User user = (User) session.getAttribute("currentUser");
			UserController controller = new UserController();
			controller.setModel(user);
			
			
			
			//if signoutButton's value is true, log out(set currentUser to null)
			if(req.getParameter("signOutButton") != null) {
				session.setAttribute("currentUser", null);				
				resp.sendRedirect(req.getContextPath() + "/index");
			}
			if(req.getParameter("deleteProfileButton") != null){
				//TODO: delete user model reference, delete in actual db
				controller.deleteUser(db);
				session.setAttribute("currentUser", null);
				resp.sendRedirect(req.getContextPath() + "/index");
			}
			if(req.getParameter("updateProfileButton") != null) {	
				//TODO: update user refernce with form info
				// also update actual user info in db with same form info
				
				
				
				
				
				resp.sendRedirect(req.getContextPath() + "/index");
			}
			
			
	}
}
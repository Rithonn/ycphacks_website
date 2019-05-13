package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCrypt;

import controller.UserController;
import model.User;
import persist.DatabaseProvider;
import persist.DerbyDatabase;
import persist.IDatabase;

public class AdminPanelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	HttpSession session = null;
	IDatabase db = null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Admin Panel Servlet: doGet");
		session = req.getSession();
		
		/*Doesn't allow logged out client/non admin to access adminPanel page
		 * as there is no user to edit
		 * forwards back to the index
		 */
		User currentUser = (User) session.getAttribute("currentUser");
		if(session.getAttribute("currentUser") == null) {
			resp.sendRedirect(req.getContextPath() + "/home");
		}else if(currentUser.getAccessID() != 2){
			resp.sendRedirect(req.getContextPath() + "/home");
		}
		
		if(DatabaseProvider.getInstance() == null) {
			DatabaseProvider.setInstance(new DerbyDatabase());//------------------------------------------DATABASE MARKER
			db = DatabaseProvider.getInstance();
		}else {
			db = DatabaseProvider.getInstance();
		}
		
		List<User> allUsers = db.getAllUsers();
		req.setAttribute("allUsers", allUsers);
		
		req.getRequestDispatcher("/_view/adminPage.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			System.out.println("Admin Panel Servlet: doPost");
			session = req.getSession();

			User user = (User) session.getAttribute("currentUser");
			
			if(req.getParameter("adminEmailPageButton") != null) {
				resp.sendRedirect(req.getContextPath() + "/adminEmail");
			}
			
			if(req.getParameter("userSearchOnAdmin") != null) {
				System.out.println(req.getParameter("userEmailForSearch"));
				
				
				//Setup a new user and add it to a list
				User newUser = new User();
				
				//Get account from email user specified from db
				newUser.setEmail(req.getParameter("userEmailForSearch"));
				newUser = db.userExists(newUser);
				
				List<User> userReturned = new ArrayList<User>();
				userReturned.add(newUser);
				
				req.setAttribute("allUsers", userReturned);
				
				
			}
			if(req.getParameter("userAccept") != null) {
				System.out.println(req.getParameter("userIdForReg"));				
			}
			if(req.getParameter("userDeny") != null) {
				System.out.println(req.getParameter("userIdForReg"));	
			}
			if(req.getParameter("userDelete") != null) {
				System.out.println(req.getParameter("userIdForReg"));				
			}
			if(req.getParameter("userIDAccessChange") != null) {
				
			}

			
			req.getRequestDispatcher("/_view/adminPage.jsp").forward(req, resp);
			
	}
}
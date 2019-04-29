package servlet;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
	Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);	
	
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
				
				if(req.getParameter("newFirst") != null) {
					controller.changeFirstName(req.getParameter("newFirst"));
				}
				
				if(req.getParameter("newLast") != null) {
					controller.changeLastName(req.getParameter("newLast"));
				}
				
				if(req.getParameter("newEmail") != null) {
					if(!validate(req.getParameter("newEmail"))) {
						req.setAttribute("error", "Please provide a new valid Email");
					}else {
						controller.changeEmail(req.getParameter("newEmail"));
					}
				}
				
				
				if(req.getParameter("newAge") != null ) {
					int newAge = Integer.parseInt(req.getParameter("newAge"));
					if(newAge > 130 || newAge < 1) {
						req.setAttribute("error", "Please provide a new valid age");
					}else {
						controller.changeAge(newAge);
					}
					
				}
				
				if(req.getParameter("newUniversity") != null) {
					controller.changeUniversity(req.getParameter("newUniversity"));
				}
				
				//String pw1 = req.getParameter("newPassword1");
				//String pw2 = req.getParameter("newPassword2");
				
				if(!req.getParameter("newPassword1").isEmpty()) {
					if(req.getParameter("newPassword1").equals(req.getParameter("newPassword2"))) {
						String hashedPW = BCrypt.hashpw(req.getParameter("newPassword1"),BCrypt.gensalt());
						controller.changePassword(hashedPW);
					}else {
						req.setAttribute("error", "Passwords did not match");
					}
					
					
				}
				
				controller.updateUser(db);
				
				resp.sendRedirect(req.getContextPath() + "/edit_profile");
				//resp.sendRedirect(req.getContextPath() + "/edit_profile");
			}
			
			
	}
	
	
	//method to validate email with the Pattern at top of file
	public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
	}
}
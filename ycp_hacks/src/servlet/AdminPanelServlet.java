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
import model.EmailSender;
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
		//This is to set the statistics 
		req.setAttribute("allUsers", allUsers);
		
		//This is to set the list of all users
		req.setAttribute("listOfUsers", allUsers);
		
		req.getRequestDispatcher("/_view/adminPage.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			System.out.println("Admin Panel Servlet: doPost");
			session = req.getSession();
			User user = (User) session.getAttribute("currentUser");
			User newUser = new User();
			
			if(req.getParameter("adminEmailPageButton") != null) {
				resp.sendRedirect(req.getContextPath() + "/adminEmail");
			}
			
			//This will search for one specific user to output back
			if(req.getParameter("userSearchOnAdmin") != null) {				
				//Setup a new user and add it to a list
				
				//Get account from email user specified from db
				newUser.setEmail(req.getParameter("userEmailForSearch"));
				newUser = db.userExists(newUser);
				
				List<User> userReturned = new ArrayList<User>();
				userReturned.add(newUser);
				
				req.setAttribute("listOfUsers", userReturned);	
			}
			if(req.getParameter("userAccept") != null) {
			
				newUser.setUserID(Integer.parseInt(req.getParameter("userIdForReg")));
				newUser = db.userExistsFromID(newUser);
				
				//Change reg to true
				newUser.setIsReg(true);
				//Call the update function
				db.updateUser(newUser);
				
				ArrayList<User> accountsReceiving = new ArrayList<User>();
				accountsReceiving.add(newUser);
				EmailSender emailSender = new EmailSender();
				emailSender.loadEmailsFromUserList(accountsReceiving);
				emailSender.sendRegisteredEmail();
				
				//Set the list back to what it was
				List<User> userReturned = db.getAllUsers();
				req.setAttribute("listOfUsers", userReturned);
				
			}
			//This denys the user from the db
			if(req.getParameter("userDeny") != null) {				
				newUser.setUserID(Integer.parseInt(req.getParameter("userIdForReg")));
				newUser = db.userExistsFromID(newUser);
				
				//Change reg to true
				newUser.setIsReg(false);
				//Call the update function
				db.updateUser(newUser);
				
				//Set the list back to what it was
				List<User> userReturned = db.getAllUsers();
				req.setAttribute("listOfUsers", userReturned);
			}
			//This delete the user from the db
			if(req.getParameter("userDelete") != null) {
				newUser.setUserID(Integer.parseInt(req.getParameter("userIdForReg")));
				db.deleteUser(db.userExistsFromID(newUser));
				
				//Set the list back to what it was
				List<User> userReturned = db.getAllUsers();
				req.setAttribute("listOfUsers", userReturned);
			}
			if(req.getParameter("userIDAccessChange") != null) {
				newUser.setUserID(Integer.parseInt(req.getParameter("userIdAccess")));
				//Check if it exists dumbass
				newUser = db.userExistsFromID(newUser);
				System.out.println(req.getParameter("userNumberForAccess"));
				newUser.setAccessID(Integer.parseInt(req.getParameter("userNumberForAccess")));
				
				db.updateUser(newUser);
				
				//Set the list back to what it was
				List<User> userReturned = db.getAllUsers();
				req.setAttribute("listOfUsers", userReturned);
			}

			//This is to store the statistics
			List<User> allUsers = db.getAllUsers();
			//Set the stats here
			req.setAttribute("allUsers", allUsers);
			
			//Resubmit it bro
			req.getRequestDispatcher("/_view/adminPage.jsp").forward(req, resp);
			
	}
}
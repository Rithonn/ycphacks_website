package servlet;

import java.io.IOException;
import java.util.ArrayList;

import model.EmailSender;
import model.User;
import controller.UserController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import persist.*;


//Implement the security here as well to use
import org.springframework.security.crypto.bcrypt.*;

public class LogInServlet extends HttpServlet{
private static final long serialVersionUID = 1L;
	
	HttpSession session = null;
	IDatabase db = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Log In Page Servlet: doGet");
		session = req.getSession();
				
		/*If session DB reference is null.
		 * create new DB (fake for now)
		 * set session DB reference to new db
		 */
		
		if(DatabaseProvider.getInstance() == null) {
			DatabaseProvider.setInstance(new DerbyDatabase());//------------------------------------------DATABASE MARKER
			db = DatabaseProvider.getInstance();
		}else {
			db = DatabaseProvider.getInstance();
		}
		
		req.getRequestDispatcher("/_view/logIn.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Log In Page Servlet: doPost");

		String errorMessage = null;
		
		if(req.getParameter("forgotPasswordButton") != null) {
			
			System.out.println("forgot button tripped");
			
			/*make sure an email was given,
			 * can't change a password without an account
			 */
			if(req.getParameter("email").isEmpty()) {
				errorMessage = "Please provide an email so we can find your account";
				req.setAttribute("login", errorMessage);	
				req.getRequestDispatcher("/_view/logIn.jsp").forward(req, resp);
			}else {
				User newPWUser = new User();
				UserController newPWController = new UserController();
				
				//Get account from email user specified from db
				newPWUser.setEmail(req.getParameter("email"));
				newPWUser = db.userExists(newPWUser);
				
				/*If db did not return anything, then the User object's
				 * only populated field should be email, 
				 * 
				 * so check if the email is null, if the database returned a user the password would be populated
				 */
				if(newPWUser.getPassword() == null) {
					System.out.println("returned user is null");
					errorMessage = "No account found under email: " + req.getParameter("email");
					req.setAttribute("login", errorMessage);	
					req.getRequestDispatcher("/_view/logIn.jsp").forward(req, resp);
				}
				
				newPWController.setModel(newPWUser);
				
				
				/*https://www.geeksforgeeks.org/generate-random-string-of-given-size-in-java/
				 * 
				 * build a "random" string of 10 characters for the user's new password,
				 * from the url above
				 */
				String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                        + "0123456789"
                        + "abcdefghijklmnopqrstuvxyz";
				
				StringBuilder sb = new StringBuilder(10);
				
				for (int i = 0; i < 10; i++) { 
					  
		            // generate a random number between 
		            // 0 to AlphaNumericString variable length 
		            int index = (int)(AlphaNumericString.length() * Math.random()); 
		  
		            // add Character one by one in end of sb 
		            sb.append(AlphaNumericString.charAt(index)); 
		        }
				
				//hash the new password and change in the user model
				String newpw = sb.toString();
				String pw_hash = BCrypt.hashpw(newpw, BCrypt.gensalt());
								
				newPWController.changePassword(pw_hash);
				
				//send email to user
				ArrayList<User> userChangedPW = new ArrayList<User>();
				userChangedPW.add(newPWUser);
				EmailSender emailSender = new EmailSender();
				emailSender.loadEmailsFromUserList(userChangedPW);
				emailSender.sendForgotPasswordEmail(newpw);
				
				//lastly update the database
				db.updateUser(newPWUser);
				
				/*alert user email has been sent,
				 * uses errorMessage variable but really a SUCCESS message
				 */
				errorMessage = "An email has been send to: " + req.getParameter("email");
				req.setAttribute("login", errorMessage);
				
				req.getRequestDispatcher("/_view/logIn.jsp").forward(req, resp);
			}
		}
		
		UserController controller = new UserController();
		User model = new User();
		controller.setModel(model);
		
		//get login credentials
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		model.setEmail(email);
		User returnedUser = db.userExists(model);

		//check if a user was returned from db
		if(returnedUser.getEmail() == null) {
			errorMessage = "No account found under: " + email;
			req.setAttribute("login", errorMessage);
		}else {
			//there is an account with that email
			//check if password is correct
			if(BCrypt.checkpw(password, returnedUser.getPassword())) {
				System.out.println("here");
				session.setAttribute("currentUser", model);
				req.setAttribute("user", model);
				resp.sendRedirect(req.getContextPath() + "/home");
			//password was wrong, inform user
			}else{
				errorMessage = "Incorrect password for account under: " + returnedUser.getEmail();
				req.setAttribute("login", errorMessage);	
			}
		}

		req.getRequestDispatcher("/_view/logIn.jsp").forward(req, resp);
	}
}

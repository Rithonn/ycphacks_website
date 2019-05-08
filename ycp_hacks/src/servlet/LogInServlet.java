package servlet;

import java.io.IOException;


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
		
		UserController controller = new UserController();
		User model = new User();
		controller.setModel(model);
	
		String errorMessage = null;
		
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

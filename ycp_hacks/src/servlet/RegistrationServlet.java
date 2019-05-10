package servlet;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.EmailSender;
import model.User;
import persist.DatabaseProvider;
import persist.DerbyDatabase;
import persist.FakeDatabase;
import persist.IDatabase;
import controller.UserController;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;
//Special import for the spring framework bcrypt
import org.springframework.security.crypto.bcrypt.*;


public class RegistrationServlet extends HttpServlet{
	
public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);	

private static final long serialVersionUID = 1L;
	
	HttpSession session = null;
	IDatabase db = null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Registration Servlet: doGet");
		session = req.getSession();
		
		/*If session DB reference is null.
		 * create new DB
		 * set session DB reference to new db
		 */
		
		if(DatabaseProvider.getInstance() == null) {
			DatabaseProvider.setInstance(new DerbyDatabase());
			db = DatabaseProvider.getInstance();
			//Don't need this for a real database
			//session.setAttribute("db", db);
		}else {
			db = DatabaseProvider.getInstance();
		}
		
		
		req.getRequestDispatcher("/_view/registration.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		System.out.println("Registration Servlet: doPost");
		
		//create new user/controller
		User model = new User();
		UserController controller = new UserController();
		controller.setModel(model);
		
		//start pulling information from the form
		String firstName = req.getParameter("firstname");
		String lastName = req.getParameter("lastname");
		
		//validate email address with regex pattern above
		//and that the two emails provided match
		String email = req.getParameter("email");
		if(!email.equals(req.getParameter("email2"))) {
			req.setAttribute("reg", "Emails fields did not match");
			req.getRequestDispatcher("/_view/registration.jsp").forward(req, resp);
			resp.sendRedirect(req.getContextPath() + "/registration");
		}else if(!validate(email)){
			req.setAttribute("reg", "Please provide a valid email address");
			req.getRequestDispatcher("/_view/registration.jsp").forward(req, resp);
			resp.sendRedirect(req.getContextPath() + "/registration");
		}
		
		String university = req.getParameter("uni");

		//TODO: validate age is an integer, and is between 1-130
		int age = 0;
		try{
			age = Integer.parseInt(req.getParameter("age"));
			if(age > 130 || age < 1) {
				req.setAttribute("reg", "Provide an age please");
				req.getRequestDispatcher("/_view/registration.jsp").forward(req, resp);
				resp.sendRedirect(req.getContextPath() + "/registration");
			}
		}catch(NumberFormatException e) {
			req.setAttribute("reg", "Provide an age please");
			req.getRequestDispatcher("/_view/registration.jsp").forward(req, resp);
			resp.sendRedirect(req.getContextPath() + "/registration");
		}
		
		
		//check passwords match in registration form
		String password = null;
		if(req.getParameter("password1").equals(req.getParameter("password2"))) {
			password = req.getParameter("password1");
			//build rest of user from form submission
			model.setFirstName(firstName);
			model.setLastName(lastName);
			model.setEmail(email);
			model.setAge(age);
			model.setUniversity(university);
			
			//Encrypt the password before it is installed

			//Take the password and hash it with a random salt
			String pw_hash = BCrypt.hashpw(password, BCrypt.gensalt());
			
			//Show the hashed password
			System.out.println(pw_hash);
			model.setPassword(pw_hash);
			
			model.setAccessID(0);
			
			
			//add user to db, will check if email has been used already
			//if successful load index.jsp
			boolean wasAdded = controller.addUser(db);
			if(wasAdded) {
				//log them in
				session.setAttribute("currentUser", model);
				
				/*
				 * Send registration email,
				 * still make an list of accounts, even though it's just one
				 * as emailSender has functionality to send to multiple recips
				 */
				ArrayList<User> accountsReceiving = new ArrayList<User>();
				accountsReceiving.add(model);
				EmailSender emailSender = new EmailSender();
				emailSender.loadEmailsFromUserList(accountsReceiving);
				emailSender.sendAccCreationEmail();

				//redirect to index.jsp
				resp.sendRedirect(req.getContextPath() + "/home");
			}else {
				//alert user reg failed
				req.setAttribute("reg", "Registration was unsuccessful");
				req.getRequestDispatcher("/_view/registration.jsp").forward(req, resp);
			}
		}else {
			//if password not matching, alert user
			req.setAttribute("reg", "Passwords did not match");
			req.getRequestDispatcher("/_view/registration.jsp").forward(req, resp);
		}
		
		
	}
	
	//method to validate email with the Pattern at top of file
	public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
	}
}

package servlet;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

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
		String email = req.getParameter("email");
		if(!validate(email)) {
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
				//send email
				String to = model.getEmail();
				String username = "walrussuit@gmail.com";
				//ask tim for password for sending email account
				String passwordEmail = "";
				Properties prop = System.getProperties();
				prop.put("mail.smtp.host", "smtp.gmail.com");
		        prop.put("mail.smtp.port", "587");
		        prop.put("mail.smtp.auth", "true");
		        prop.put("mail.smtp.starttls.enable", "true"); //TLS
				
		        Session session = Session.getInstance(prop,
	                new javax.mail.Authenticator() {
	                    protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication(username, passwordEmail);
	                    }
	                });
				
				try {
					MimeMessage message = new MimeMessage(session);
					message.setFrom(new InternetAddress(username));
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
					message.setSubject(model.getFirstName() + ", welcome to YCP Hacks!");
					message.setText("Your account under: " + to + ", is registered for YCP Hacks!");
					Transport.send(message);
				}catch(MessagingException e) {
					System.out.println(e);
				}
				
				//redirect to index.jsp
				resp.sendRedirect(req.getContextPath() + "/index");
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
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
	}
}

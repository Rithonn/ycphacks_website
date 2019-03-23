package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistrationServlet extends HttpServlet{
private static final long serialVersionUID = 1L;
	
	HttpSession session = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Registration Servlet: doGet");
		req.getSession();
		req.getRequestDispatcher("/_view/registration.jsp").forward(req, resp);
		/*Registration servlet will parse all the info from the registration form, and then use that to create a new user in the db
		 * provided that the user is not already in the database, and that all information passed in has been verified
		 * */
		
		//TODO: Parse info from form for user
		//TODO: verify info
		//TODO: store into db
	}
}

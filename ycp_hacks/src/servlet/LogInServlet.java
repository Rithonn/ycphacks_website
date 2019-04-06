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

public class LogInServlet extends HttpServlet{
private static final long serialVersionUID = 1L;
	
	HttpSession session = null;
	IDatabase db = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Profile Page Servlet: doGet");
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
		
		System.out.println("Profile Page Servlet: doPost");
		
		UserController controller = new UserController();
		User model = new User();
		controller.setModel(model);
	
		String errorMessage = null;
		
		//get login credentials
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		if(email == null || password == null) {
			errorMessage = "please specifiy valid credentials";
		}else{
			model.setEmail(email);
			model.setPassword(password);
		}
			
		boolean loginCheck = controller.checkCredentials(db);

		//If true login worked yo
		//If the user successfully logged in redirect to home page, 
		//Otherwise inform that log in was not successful
		String youwon = "You didnt win go cry";
		if(loginCheck == true) {
			//if not login denied fool
			session.setAttribute("currentUser", model);
			req.setAttribute("user", model);

			resp.sendRedirect("/_view/index.jsp");

			resp.sendRedirect(req.getContextPath() + "/index");

		}else if(loginCheck == false){
			youwon = "Login was not successful";
			req.setAttribute("login", youwon);
		}
		
		req.getRequestDispatcher("/_view/logIn.jsp").forward(req, resp);
		
	}
}

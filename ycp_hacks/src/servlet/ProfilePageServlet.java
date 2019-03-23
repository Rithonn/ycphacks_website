package servlet;

import java.io.IOException;

import model.User;
import controller.UserController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProfilePageServlet extends HttpServlet{
private static final long serialVersionUID = 1L;
	
	HttpSession session = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Profile Page Servlet: doGet");
		session = req.getSession();
		req.getRequestDispatcher("/_view/profilePage.jsp").forward(req, resp);
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
			
		boolean loginCheck = controller.checkCredentials();
		
		//If true login worked yo
		String youwon = "You didnt win go cry";
		if(loginCheck == true) {
			youwon = "Login was successful";
		}else if(loginCheck == false){
			youwon = "Login was not successful";
		}
		 
		session.setAttribute("currentUser", model);
		
		req.setAttribute("login", youwon);
		//if not login denied fool
		req.setAttribute("user", model);
		req.getRequestDispatcher("/_view/profilePage.jsp").forward(req, resp);
		
	}
}

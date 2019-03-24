package servlet;

import java.io.IOException;

import model.User;
import controller.UserController;
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
		session = req.getSession();
		req.getRequestDispatcher("/_view/registration.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		System.out.println("Registration Servlet: doPost");
		
		
		User model = new User();
		UserController controller = new UserController();
		controller.setModel(model);
		
		String firstName = req.getParameter("firstname");
		String lastName = req.getParameter("lastname");
		String email = req.getParameter("email");
		int age = Integer.parseInt(req.getParameter("age"));
		String university = req.getParameter("uni");
		
		//TODO HANDLE PASSWORDS NOT MATCHING
		String password = null;
		if(req.getParameter("password1").equals(req.getParameter("password2"))) {
			password = req.getParameter("password1");
		}
		
		
		model.setFirstName(firstName);
		model.setLastName(lastName);
		model.setEmail(email);
		model.setAge(age);
		model.setUniversity(university);
		model.setPassword(password);
		
		
		boolean wasAdded = controller.addUser();
		if(wasAdded) {
			session.setAttribute("currentUser", model);
			req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
		}
		
		req.getRequestDispatcher("/_view/registration.jsp").forward(req, resp);
	}
}

package servlet;

import java.io.IOException;

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

public class RegistrationServlet extends HttpServlet{
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
		
		//build rest of user from form submission
		model.setFirstName(firstName);
		model.setLastName(lastName);
		model.setEmail(email);
		model.setAge(age);
		model.setUniversity(university);
		model.setPassword(password);
		
		
		
		//Have a check to see if the user exists first. If not then go ahead with the addUser
		
		//add user to db
		//if successful load index.jsp
		boolean wasAdded = controller.addUser(db);
		if(wasAdded) {
			//log them in
			session.setAttribute("currentUser", model);
			//redirect to index.jsp
			req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
		}else {
			//alert user reg failed
			req.setAttribute("reg", "Registration was unsuccessful");
			req.getRequestDispatcher("/_view/registration.jsp").forward(req, resp);
		}
	}
}

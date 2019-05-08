package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import persist.DatabaseProvider;
import persist.DerbyDatabase;
import persist.IDatabase;

public class AdminEmailServlet extends HttpServlet{
private static final long serialVersionUID = 1L;
	
	HttpSession session = null;
	IDatabase db = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Admin Email Page Servlet: doGet");
		session = req.getSession();
		
		/*If session DB reference is null.
		 * create new DB (fake for now)
		 * set session DB reference to new db
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
		
		req.getRequestDispatcher("/_view/adminEmail.jsp").forward(req, resp);
	}

}

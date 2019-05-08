package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DirectionsServlet extends HttpServlet{
private static final long serialVersionUID = 1L;
	
	HttpSession session = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Directions Servlet: doGet");
		req.getRequestDispatcher("/_view/directions.jsp").forward(req, resp);
	}
}

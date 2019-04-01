package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ScheduleServlet extends HttpServlet{
private static final long serialVersionUID = 1L;

	HttpSession session = null;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Schedule Servlet: doGet");
		req.getSession();
		req.getRequestDispatcher("/_view/schedule.jsp").forward(req, resp);
	}
}

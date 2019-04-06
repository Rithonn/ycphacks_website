package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ScheduleController;
import model.Schedule;
import persist.DatabaseProvider;
import persist.DerbyDatabase;
import persist.FakeDatabase;
import persist.IDatabase;

public class ScheduleServlet extends HttpServlet{
private static final long serialVersionUID = 1L;

	HttpSession session = null;
	IDatabase db = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Schedule Servlet: doGet");
		session = req.getSession();
		
		if(DatabaseProvider.getInstance() == null) {
			DatabaseProvider.setInstance(new FakeDatabase());//------------------------------------------DATABASE MARKER
			db = DatabaseProvider.getInstance();
		}else {
			db = DatabaseProvider.getInstance();
		}
		
		// Create the controller and schedule model 
		ScheduleController schedCont = new ScheduleController();
		Schedule schedule = new Schedule();
		
		//Set the controller's model
		schedCont.setSchedule(schedule);
		
		//Loads schedule from database into the model schedule
		schedCont.loadSchedule(db);
		
		//Sets schedule attribute in HTTP to the schedule model
		req.setAttribute("schedule", schedule);
		
		req.getRequestDispatcher("/_view/schedule.jsp").forward(req, resp);
	}
	
//	@Override 
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException {
//		
//		System.out.println("Schedule Servlet: doPost");
//		
//		
//		
//		req.getRequestDispatcher("/_view/schedule.jsp").forward(req, resp);
//	}
}

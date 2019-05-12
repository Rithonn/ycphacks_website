package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ScheduleController;
import model.Event;
import model.Schedule;
import persist.DatabaseProvider;
import persist.DerbyDatabase;
import persist.IDatabase;

public class AboutServlet extends HttpServlet{
private static final long serialVersionUID = 1L;
	
	HttpSession session = null;
	IDatabase db = null;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("About Servlet: doGet");
		session = req.getSession();
		if(DatabaseProvider.getInstance() == null) {
			DatabaseProvider.setInstance(new DerbyDatabase());//------------------------------------------DATABASE MARKER
			db = DatabaseProvider.getInstance();
		}else {
			db = DatabaseProvider.getInstance();
		}
		// Create the controller and schedule model 
		ScheduleController schedCont = new ScheduleController();
		Schedule schedule = new Schedule();
						
		//Set the controller's model
		schedCont.setSchedule(schedule);

//		//Loads schedule from database into the model schedule
		schedCont.loadSchedule(db);
						
		//Convert to collection to make it iterable in the jsp
		List<Event> eventlist = schedule.getSchedule();
						
		//List to hold ongoing events for the ticker
		List<Event> ongoing = new ArrayList<Event>();
		//The next upcoming event for the ticker
		Event nextup = null;
						
		for(Event event : eventlist) {
			System.out.println(event.checkStatus());
			if(event.checkStatus() == 1) {
				ongoing.add(event);
			}
			if(event.checkStatus() == 2 && nextup == null) {
				nextup = event;
			}
		}
		//Set session attribute for the ongoing and upcoming events
		if(ongoing.size() != 0) {
			session.setAttribute("ongoing", ongoing);
		}
		session.setAttribute("upcoming", nextup);
		req.getRequestDispatcher("/_view/about.jsp").forward(req, resp);
	}
}

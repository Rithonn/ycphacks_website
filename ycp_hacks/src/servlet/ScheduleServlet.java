package servlet;

import java.util.Collections;
import java.util.List;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ScheduleController;
import controller.SortScheduleByDate;
import model.Event;
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
		
		
//		
//		//Loads schedule from database into the model schedule
		schedCont.loadSchedule(db);
		
//		Event event3 = new Event();
//		
//		LocalDateTime date3 = LocalDateTime.of(2019, Month.OCTOBER, 26, 22, 30);
//		event3.setDate(date3);
//		schedCont.addEventToDB(db, event3);
		//Collections.sort(schedule.getSchedule(), new SortScheduleByDate());
		
		//Convert to collection to make it iterable in the jsp
		List<Event> eventlist = schedule.getSchedule();
		
		//Get the first event to pass into the jsp
		Event firstevent = eventlist.get(1);
		System.out.println("First event dayofmonth = " +firstevent.getDate().getDayOfMonth());
		//Sets schedule attribute in HTTP to the schedule model
		req.setAttribute("schedule", eventlist);
		req.setAttribute("firstevent", firstevent);
		
		req.getRequestDispatcher("/_view/schedule.jsp").forward(req, resp);
	}
	
	@Override 
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Schedule Servlet: doPost");
		session = req.getSession();
		
		//Create new schedule and controller
		Schedule schedule = new Schedule();
		ScheduleController schedCont = new ScheduleController();
		schedCont.setSchedule(schedule);
		
		//Pull info from form
		//If the delete button was pressed, delete the event from the database, and reload the schedule
		if(req.getParameter("delEventButton") != null) {
//			System.out.println("Delete button " + req.getParameter("delEventButton"));
//			System.out.println("Add button " + req.getParameter("addEventButton"));
			Event delevent = new Event();
			String eventId = req.getParameter("delEventId");
//			System.out.println("Event id " + eventId);
			delevent.setEventId(Integer.parseInt(eventId));
			schedCont.deleteEvent(db, delevent);
			
		}
		//If the add event button was pressed, construct the new event, add it to the db and reload the schedule
		if(req.getParameter("addEventButton") != null) {
			Event addEvent = new Event();
			//Parse out the strings and set to the event
			addEvent.setName(req.getParameter("addEventName"));
			addEvent.setLocation(req.getParameter("addEventLocation"));
			addEvent.setDescription(req.getParameter("addEventDescription"));
			//Parse out the year, month and day
			int eventYear = Integer.parseInt(req.getParameter("addEventYear"));
			int eventMonth = Integer.parseInt(req.getParameter("addEventMonth"));
			int eventDay = Integer.parseInt(req.getParameter("addEventDay"));
//			System.out.println("Delete button " + req.getParameter("delEventButton"));
//			System.out.println("Add button " + req.getParameter("addEventButton"));
			//System.out.println("Event day " + eventDay);
			
			String amOrPm = req.getParameter("inlineRadioOptions");
			//Parse out the hours and minutes
			String time = req.getParameter("addEventTime");
			//Split the time string apart to separate hours and minutes
			String[] parts = time.split(":");
			int hours = Integer.parseInt(parts[0]);
			int minutes = Integer.parseInt(parts[1]);
//			System.out.println("Event hours " + hours);
//			System.out.println("Event minutes " + minutes);
			//If the time is pm, add 12 to convert to 24 hour time
			if("PM".equals(amOrPm)) {
				hours += 12;
				if(hours > 23) {
					hours = 0;
					eventDay++;
				}
			}
			//Construct the local date time
			LocalDateTime date = LocalDateTime.of(eventYear, eventMonth, eventDay, hours, minutes);
			addEvent.setDate(date);
			
			//Add the date to the db
			schedCont.addEventToDB(db, addEvent);
		}
		
		//Loads schedule from database into the model schedule
		schedCont.loadSchedule(db); 
		//Convert to collection to make it iterable in the jsp
		List<Event> eventlist = schedule.getSchedule();
				
				
		//Sets schedule attribute in HTTP to the schedule model
		req.setAttribute("schedule", eventlist);
				
		req.getRequestDispatcher("/_view/schedule.jsp").forward(req, resp);
	}
}

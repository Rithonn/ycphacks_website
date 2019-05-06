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
		Event firstevent = eventlist.get(0);
		//System.out.println("First event dayofmonth = " +firstevent.getDate().getDayOfMonth());
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
		schedCont.loadSchedule(db);
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
			Event addEvent = null;
			int eventYear = 0;
			int eventMonth = 0;
			int eventDay = 0;
			int hours = 0;
			int minutes = 0;
			String amOrPm = null;
			Boolean modify = null;
			//Parse out the strings and set to the event
			//If the modify event ID is not null, then set it in the event, same for all other attributes
			/*If there is a specified event ID, find that event in the schedule, so that the actual event is modified
			instead of creating a brand new event */
			System.out.println("Modify event ID = " +req.getParameter("modifyEventID"));
			if(req.getParameter("modifyEventID").isEmpty() == false) {
				for(Event event : schedule.getSchedule()) {
					System.out.println("I am running through the loop");
					if(event.getEventId() == Integer.parseInt(req.getParameter("modifyEventID"))){
						addEvent = event;
						System.out.println("Event was found based on ID");
					}
				if(addEvent == null) {
					System.out.println("Add event is null");
				}
				modify = true;
				}
			//If an event ID is not specified, create a new event to be added to the db
			}else if (req.getParameter("modifyEventID").isEmpty() == true){
				addEvent = new Event();
				System.out.println("There is no modifying going on here");
				modify = false;
			}
			//Only enter the name if the user enters one
			if(!req.getParameter("addEventName").isEmpty()) {
				addEvent.setName(req.getParameter("addEventName"));
			}
			//Only update the location if the user enters one
			if(!req.getParameter("addEventLocation").isEmpty()) {
				addEvent.setLocation(req.getParameter("addEventLocation"));
			}
			//Only update the description if the user enters one
			if(!req.getParameter("addEventDescription").isEmpty()) {
				addEvent.setDescription(req.getParameter("addEventDescription"));
			}
			//Parse out the year, month and day if they are not null
			//Only update the year if it is not null
			if(!req.getParameter("addEventYear").isEmpty()) {
				eventYear = Integer.parseInt(req.getParameter("addEventYear"));
			}
			//Only update the month if it is not null
			if(!req.getParameter("addEventMonth").isEmpty()) {
				eventMonth = Integer.parseInt(req.getParameter("addEventMonth"));
			}
			//Only update the day if it is not null
			if(!req.getParameter("addEventDay").isEmpty()) {
				eventDay = Integer.parseInt(req.getParameter("addEventDay"));
			}
			//If an am or pm option is selected, update the string
			if(req.getParameter("inlineRadioOptions") != null) {
				amOrPm = req.getParameter("inlineRadioOptions");
				
			}
			//If a time is entered update the date
			if(!req.getParameter("addEventTime").isEmpty()) {
				String time = req.getParameter("addEventTime");
				//Split the time string apart to separate hours and minutes
				String[] parts = time.split(":");
				hours = Integer.parseInt(parts[0]);
				minutes = Integer.parseInt(parts[1]);
				if("PM".equals(amOrPm)) {
					//If the time is pm, add 12 to convert to 24 hour time
					hours += 12;
					if(hours > 23) {
						hours = 0;
						eventDay++;
					}
				}
				//Construct the local date time
				LocalDateTime date = null; 
				
				date = LocalDateTime.of(eventYear, eventMonth, eventDay, hours, minutes);
				addEvent.setDate(date);
			}
			if(req.getParameter("visibility") != null) {
				String visibility = req.getParameter("visibility");
				//System.out.println("Vis = " + visibility);
				if("Visible".equals(visibility)) {
					addEvent.setIsVisible(true);
				}else if("Not Visible".equals(visibility)){
					addEvent.setIsVisible(false);
				}
			}
			
			//Add the date to the db if it is not being modified
			if(!modify) {
				schedCont.addEventToDB(db, addEvent);
			}else if(modify) {
				schedCont.modifyEventInDB(db, addEvent);
			}
		}
		
		//Loads schedule from database into the model schedule
		Schedule newsched = new Schedule();
		schedCont.setSchedule(newsched);
		schedCont.loadSchedule(db); 
		//Convert to collection to make it iterable in the jsp
		List<Event> eventlist = schedule.getSchedule();

		//Get the first event to pass into the jsp
		Event firstevent = eventlist.get(0);
		//System.out.println("First event dayofmonth = " +firstevent.getDate().getDayOfMonth());
		//Sets schedule attribute in HTTP to the schedule model
		req.setAttribute("schedule", eventlist);
		req.setAttribute("firstevent", firstevent);
				
		req.getRequestDispatcher("/_view/schedule.jsp").forward(req, resp);
	}
}

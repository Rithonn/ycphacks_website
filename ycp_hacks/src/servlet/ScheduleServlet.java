package servlet;

import java.util.ArrayList;
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

import controller.EventController;
import controller.ScheduleController;
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
		

		
		//Convert to collection to make it iterable in the jsp
		List<Event> eventlist = schedule.getSchedule();
		
		//List to hold ongoing events for the ticker
		List<Event> ongoing = new ArrayList<Event>();
		//The next upcoming event for the ticker
		Event nextup = null;
		//Get the first event to pass into the jsp
		Event firstevent = eventlist.get(0);
		
		for(Event event : eventlist) {
			System.out.println(event.checkStatus());
			if(event.checkStatus() == 1) {
				ongoing.add(event);
			}
			if(event.checkStatus() == 2 && nextup == null) {
				nextup = event;
			}
		}
		
		//System.out.println("First event dayofmonth = " +firstevent.getDate().getDayOfMonth());
		//Sets schedule attribute in HTTP to the schedule model
		req.setAttribute("schedule", eventlist);
		req.setAttribute("firstevent", firstevent);
		//Set session attribute for the ongoing and upcoming events
		if(ongoing.size() != 0) {
			session.setAttribute("ongoing", ongoing);
		}
		session.setAttribute("upcoming", nextup);
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
		String addEventErrorMsg = null;
		String delEventErrorMsg = null;
		
		//Pull info from form
		//If the delete button was pressed, delete the event from the database, and reload the schedule
		if(req.getParameter("delEventButton") != null) {
//			System.out.println("Delete button " + req.getParameter("delEventButton"));
//			System.out.println("Add button " + req.getParameter("addEventButton"));
			Event delevent = new Event();
			EventController eventcont = new EventController();
			eventcont.setModel(delevent);
			String eventId = req.getParameter("delEventId");
//			System.out.println("Event id " + eventId);
			try {
				eventcont.changeEventId(Integer.parseInt(eventId));
				schedCont.deleteEvent(db, delevent);
			}catch (NumberFormatException e) {
				delEventErrorMsg = "Please enter a valid event ID";
			}
			
			
		}
		//If the add event button was pressed, construct the new event, add it to the db and reload the schedule
		if(req.getParameter("addEventButton") != null) {
			Event addEvent = null;
			EventController eventcont = new EventController();
			eventcont.setModel(addEvent);
			int eventYear = 0;
			int eventMonth = 0;
			int eventDay = 0;
			int hours = 0;
			int minutes = 0;
			String amOrPm = null;
			Boolean modify = false;
			//Parse out the strings and set to the event
			//If the modify event ID is not null, then set it in the event, same for all other attributes
			/*If there is a specified event ID, find that event in the schedule, so that the actual event is modified
			instead of creating a brand new event */
			System.out.println("Modify event ID = " +req.getParameter("modifyEventID"));
			if(req.getParameter("modifyEventID").isEmpty() == false) {
				try {
					for(Event event : schedule.getSchedule()) {
						System.out.println("I am running through the loop");
						if(event.getEventId() == Integer.parseInt(req.getParameter("modifyEventID"))){
							addEvent = event;
							System.out.println("Event was found based on ID");
							modify = true;
							break;
						}
					
					
					}
					if(addEvent == null) {
						System.out.println("Add event is null");
						addEventErrorMsg = "Please enter a valid Event ID";
						
					}
				}catch(NumberFormatException e) {
					addEventErrorMsg = "Please enter a valid Event ID";
	
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
				try {
					eventYear = Integer.parseInt(req.getParameter("addEventYear"));
				}catch(NumberFormatException e) {
					if(addEventErrorMsg == null) {
						addEventErrorMsg = "Please enter an integer";
					}
				}
			}
			//Only update the month if it is not null
			if(!req.getParameter("addEventMonth").isEmpty()) {
				try {
					eventMonth = Integer.parseInt(req.getParameter("addEventMonth"));
				}catch(NumberFormatException e) {
					if(addEventErrorMsg == null) {
						addEventErrorMsg = "Please enter an integer 1-12";
					}
				}
			
			}
			//Only update the day if it is not null
			if(!req.getParameter("addEventDay").isEmpty()) {
				try {
					eventDay = Integer.parseInt(req.getParameter("addEventDay"));
				}catch(NumberFormatException e) {
					if(addEventErrorMsg == null) {
						addEventErrorMsg = "Please enter an integer 1-31";
					}
				}
			}
			//If an am or pm option is selected, update the string
			if(req.getParameter("inlineRadioOptions") != null) {
				amOrPm = req.getParameter("inlineRadioOptions");
				
			}
			
			if(!req.getParameter("addEventDuration").isEmpty()) {
				String time = req.getParameter("addEventDuration");
				//Split the time string apart to separate hours and minutes
				//If the user specified a valid format for the time
				if(time.indexOf(':') != -1) {
					String[] parts = time.split(":");
					try {
						hours = Integer.parseInt(parts[0]);
						minutes = Integer.parseInt(parts[1]);
					} catch(NumberFormatException e) {
						if(addEventErrorMsg == null) {
							addEventErrorMsg = "Please enter a valid time";
						}
					}
					
				//If the user did not include the colon
				}else if(time.indexOf(':') == -1) {
					if(addEventErrorMsg == null) {
						addEventErrorMsg = "Please include a colon in your time";
					}
				}
				long totalseconds = ((hours * 60) + minutes) * 60;
				if(addEvent != null) {
					addEvent.setEventDuration(totalseconds);
				}
				System.out.println("Event duration in seconds: " + totalseconds);
			}
			//If a time is entered update the date
			if(!req.getParameter("addEventTime").isEmpty()) {
				String time = req.getParameter("addEventTime");
				//Split the time string apart to separate hours and minutes
				//If the user specified a valid format for the time
				if(time.indexOf(':') != -1) {
					String[] parts = time.split(":");
					try {
						hours = Integer.parseInt(parts[0]);
						minutes = Integer.parseInt(parts[1]);
					} catch(NumberFormatException e) {
						if(addEventErrorMsg == null) {
							addEventErrorMsg = "Please enter a valid time";
						}
					}
					
				//If the user did not include the colon
				}else if(time.indexOf(':') == -1) {
					if(addEventErrorMsg == null) {
						addEventErrorMsg = "Please include a colon in your time";
					}
				}
				if("PM".equals(amOrPm) && hours < 12) {
					//If the time is pm, add 12 to convert to 24 hour time
					hours += 12;
					
				//If it is midnight, increment the day
				}else if("AM".equals(amOrPm) && hours == 12) {
						
						hours = 0;
						eventDay++;
						//If the user is modifying an event
						//Increment the already established event day
						if(modify) {
							eventDay = addEvent.getDate().getDayOfMonth();
							eventDay++;
						}
				}else if("AM".equals(amOrPm) && hours < 12) {
					
				}
				
				
			}else if(req.getParameter("addEventTime").isEmpty() && modify) {
				hours = addEvent.getDate().getHour();
				minutes = addEvent.getDate().getMinute();
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
			/*If the user has opted to modify an event, but has not altered the month, day, and year fields
			 * pull the fields from the current event
			 * Hours and Minutes are allowed to be 0*/
			System.out.println("Event year = " + eventYear + "Event month = " + eventMonth + "Event day = " + eventDay);
			System.out.println("Modify = " + modify);
			//If the event is being modified, but the user has not specified a part of the date, pull that part from the date to be modified
			if(modify && addEvent != null) {
				if(eventYear == 0){
					eventYear = addEvent.getDate().getYear();
				}
				if (eventMonth == 0) { 
					eventMonth = addEvent.getDate().getMonthValue();
				}
				if (eventDay == 0) {
					eventDay = addEvent.getDate().getDayOfMonth();
				}
			}
			//Construct the local date time
			LocalDateTime date = null;
			if(eventYear == 0 || eventMonth == 0 || eventDay == 0) {
				if(addEventErrorMsg == null) {
					addEventErrorMsg = "Please specifiy a valid date";
				}
			}
			if(eventYear != 0 && eventMonth != 0 && eventDay != 0) {
				date = LocalDateTime.of(eventYear, eventMonth, eventDay, hours, minutes);
				addEvent.setDate(date);
			}
			//Add the date to the db if it is not being modified
			if(!modify && addEventErrorMsg == null) {
				schedCont.addEventToDB(db, addEvent);
			}else if(modify && addEventErrorMsg == null) {
				schedCont.modifyEventInDB(db, addEvent);
			}
		}
		
		//Loads schedule from database into the model schedule
		Schedule newsched = new Schedule();
		schedCont.setSchedule(newsched);
		schedCont.loadSchedule(db); 
		//Convert to collection to make it iterable in the jsp
		List<Event> eventlist = newsched.getSchedule();
		//Get the first event to pass into the jsp
		Event firstevent = eventlist.get(0);
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
		//System.out.println("First event dayofmonth = " +firstevent.getDate().getDayOfMonth());
		//Sets schedule attribute in HTTP to the schedule model
		req.setAttribute("schedule", eventlist);
		req.setAttribute("firstevent", firstevent);
		req.setAttribute("addEventErrorMsg", addEventErrorMsg);
		req.setAttribute("delEventError", delEventErrorMsg);
		//Set session attribute for the ongoing and upcoming events
		if(ongoing.size() != 0) {
			session.setAttribute("ongoing", ongoing);
		}
		session.setAttribute("upcoming", nextup);
		req.getRequestDispatcher("/_view/schedule.jsp").forward(req, resp);
	}
}

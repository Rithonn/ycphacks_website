package persist;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;



import model.Event;
import model.Schedule;
import model.User;

public class FakeDatabase implements IDatabase {
	ArrayList<User> userList = null;
	ArrayList<Event> eventList = null;
	
	public FakeDatabase() {
		userList = new ArrayList<User>();
		eventList = new ArrayList<Event>();
		initData(userList, eventList);
		//Create a fake account here to be processed

	}

	//Broke fake database for this right now
	@Override
	public User userExists(User user) {
		// TODO Auto-generated method stub
		//Check to see if the user exits
		for(User exists: userList) {
			//return true if the email matches
			if(exists.getEmail().equals(user.getEmail()) && exists.getPassword().equals(user.getPassword())) {
				//user exists, build rest of user model
				user.setUserID(exists.getUserID());
				user.setAge(exists.getAge());
				user.setIsReg(exists.isReg());
				user.setUniversity(exists.getUniversity());
				user.setFirstName(exists.getFirstName());
				user.setLastName(exists.getLastName());
				return user;
			}
		}
		return null;
	}
	
	@Override
	public boolean addUser(User user) {
		
		boolean emailUsed = false;
		for(User exists : userList) {
			if(exists.getEmail().equals(user.getEmail())) {
				emailUsed = true;
			}
		}
		
		if(emailUsed == false) {
			userList.add(user);
			return true;
		}else {
			return false;
		}
		
	}
	
	
	
	
	public void initData(ArrayList<User> userList, ArrayList<Event> schedule) {
		User user = new User();
		user.setEmail("tjefferson@ycp.edu");
		user.setAge(20);
		user.setUserID(1);
		user.setIsReg(true);
		user.setPassword("ilikedogs");
		user.setUniversity("York College of PA");
		user.setFirstName("Timothy");
		user.setLastName("Jefferson");
		
		//Create a test event
		Event event1 = new Event();
		
		// Java calendars allow easier creation of dates
		LocalDateTime date = LocalDateTime.of(2019, Month.OCTOBER, 26, 17, 00);
		
		// Format YYYY MM DD HH MM
//		date.set(2019, 10, 26, 17, 00);
		
		event1.setLocation("Lobby");
		event1.setDescription("Check in before the event starts");
		event1.setName("Check-in");
		event1.setDate(date);
		event1.setIsPassedTime(false);
		event1.setIsUpComing(true);
		
		//Creates a second test event
		Event event2 = new Event();
		
		LocalDateTime date2 = LocalDateTime.of(2019, Month.OCTOBER, 26, 20, 30);
		
		
		
		event2.setLocation("Yorkview");
		event2.setDescription("Pizza for dinner");
		event2.setName("Free pizza");
		event2.setDate(date2);
		event2.setIsPassedTime(false);
		event2.setIsUpComing(true);
		
		Event event3 = new Event();
		
		LocalDateTime date3 = LocalDateTime.of(2019, Month.OCTOBER, 27, 8, 30);
		
		
		event3.setLocation("Yorkview");
		event3.setDescription("Breakfast is served");
		event3.setName("Breakfast");
		event3.setDate(date3);
		event3.setIsPassedTime(false);
		event3.setIsUpComing(true);
		
		//Add test stuff to proper lists
		eventList.add(event1);
		eventList.add(event2);
		eventList.add(event3);
		userList.add(user);
	}

	@Override
	public Schedule getScheduleFromDB(Schedule schedule) {
		//Add each event from the stored list to the schedule
		for(Event event: eventList) {
			schedule.addEvent(event);
		}
		
		return schedule;
	}
	
	@Override
	public boolean deleteUser(User user) {
		return false;
	}
	
	@Override
	public boolean updateUser(User user) {
		return false;
	}

	@Override
	public boolean deleteEvent(Event event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addEvent(Event event) {
		// TODO Auto-generated method stub
		return false;
	}
}

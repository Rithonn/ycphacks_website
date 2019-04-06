package persist;

import java.util.ArrayList;
import java.util.Calendar;


import model.Event;
import model.User;

public class FakeDatabase implements IDatabase {
	ArrayList<User> userList = null;
	ArrayList<Event> schedule = null;
	
	public FakeDatabase() {
		userList = new ArrayList<User>();
		schedule = new ArrayList<Event>();
		initData(userList, schedule);
		//Create a fake account here to be processed

	}

	@Override
	public boolean userExists(User user) {
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
				return true;
			}
		}
		return false;
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
		Calendar date = Calendar.getInstance();
		
		// Format YYYY MM DD HH MM
		date.set(2019, 10, 26, 17, 0);
		
		event1.setLocation("Lobby");
		event1.setDescription("Check in before the event starts");
		event1.setName("Check-in");
		event1.setDate(date);
		event1.setIsPassedTime(false);
		event1.setIsUpComing(true);
		
		//Creates a second test event
		Event event2 = new Event();
		
		Calendar date2 = Calendar.getInstance();
		
		date2.set(2019, 10, 26, 20, 0);
		
		event2.setLocation("Yorkview");
		event2.setDescription("Pizza for dinner");
		event2.setName("Free pizza");
		event2.setDate(date2);
		event2.setIsPassedTime(false);
		event2.setIsUpComing(true);
		
		//Add test stuff to proper lists
		schedule.add(event1);
		schedule.add(event2);		
		userList.add(user);
	}
	
}

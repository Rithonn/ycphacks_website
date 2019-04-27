package persist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import persist.ReadCSV;
import model.Event;
import model.User;



public class InitialData {

	public static List<User> getUserList() throws IOException {

		// TODO Auto-generated method stub
		List<User> userList = new ArrayList<User>();
		ReadCSV readUsers = new ReadCSV("users.csv");
		
		try {
			// auto-generated primary key for authors table
			Integer userId = 1;
			while (true) {
				List<String> tuple = readUsers.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				User user = new User();

				// read author ID from CSV file, but don't use it
				// it's there for reference purposes, just make sure that it is correct
				// when setting up the BookAuthors CSV file				
				Integer.parseInt(i.next());
				// build user
				user.setUserID(userId++);				
				user.setLastName(i.next());
				user.setFirstName(i.next());
				user.setEmail(i.next());
				user.setPassword(i.next());
				user.setAge(Integer.parseInt(i.next()));
				user.setUniversity(i.next());
				user.setIsReg(Boolean.parseBoolean(i.next()));
				userList.add(user);
			}
			System.out.println("userList loaded from CSV file");
			
			//dev-------------
			for(User user: userList) {
				System.out.println(user.getEmail());
			}
			
			
			return userList;
		} finally {
			readUsers.close();
		}
	}
	
	public static List<Event> getEventList() throws IOException {
		// TODO Auto-generated method stub
				List<Event> eventList = new ArrayList<Event>();
				ReadCSV readEvents = new ReadCSV("schedule.csv");
				
				try {
					while (true) {
						List<String> tuple = readEvents.next();
						if (tuple == null) {
							break;
						}
						Iterator<String> i = tuple.iterator();
						Event event = new Event();

						// read author ID from CSV file, but don't use it
						// it's there for reference purposes, just make sure that it is correct
						// when setting up the BookAuthors CSV file				
						
						// build user
						long temp = Long.valueOf(i.next());
					
						event.setDateFromLong(temp);
						System.out.println("InitialData ="+ temp);
						event.setName(i.next());
						event.setLocation(i.next());
						event.setDescription(i.next());
						eventList.add(event);
					}
					System.out.println("userList loaded from CSV file");
					
					//dev-------------
					for(Event event: eventList) {
						System.out.println(event.getName());
					}
					
					
					return eventList;
				} finally {
					readEvents.close();
				}
		
	}
}

package persist;

import java.util.ArrayList;

import model.User;

public class FakeDatabase implements IDatabase {
	ArrayList<User> userList = null;
	
	public FakeDatabase() {
		userList = new ArrayList<User>();
		initData(userList);
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
	
	
	
	public void initData(ArrayList<User> userList) {
		User user = new User();
		user.setEmail("tjefferson@ycp.edu");
		user.setAge(20);
		user.setIsReg(true);
		user.setPassword("ilikedogs");
		user.setUniversity("York College of PA");
		user.setFirstName("Timothy");
		user.setLastName("Jefferson");
		
		userList.add(user);
	}
	
}

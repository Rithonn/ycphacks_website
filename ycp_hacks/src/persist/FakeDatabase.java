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
	public boolean userExists(String email, String password) {
		// TODO Auto-generated method stub
		//Check to see if the user exits
		for(User exists: userList) {
			//return true if the email matches
			if(exists.getEmail().equals(email) && exists.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
	
	public void initData(ArrayList<User> userList) {
		User user = new User();
		user.setEmail("tjefferson@ycp.edu");
		user.setAge(20);
		user.setIsReg(true);
		user.setPassword("ilikedogs");
		user.setUniversity("York College of PA");
		
		userList.add(user);
	}
	
}

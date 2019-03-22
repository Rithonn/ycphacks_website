package persist;

import java.util.ArrayList;

import model.User;

public class FakeDatabase implements IDatabase {
	ArrayList<User> userList = null;
	
	public FakeDatabase() {
		userList = new ArrayList<User>();
		
		//Create a fake account here to be processed

	}

	@Override
	public Boolean userExists(String email) {
		// TODO Auto-generated method stub
		//Check to see if the user exits
		for(User exists: userList) {
			//return true if the email matches
			if(exists.getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}
	
	
	
}

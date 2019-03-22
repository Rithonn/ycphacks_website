package persist;

import java.util.List;

import model.User;

public interface IDatabase {
	
	public boolean userExists(String email, String password);
	
}

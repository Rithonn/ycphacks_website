package persist;

import java.util.List;

import model.User;

public interface IDatabase {
	
	public Boolean userExists(String email);
	
}

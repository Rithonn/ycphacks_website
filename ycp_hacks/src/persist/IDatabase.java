package persist;

import java.util.List;

import model.User;
import model.Schedule;

public interface IDatabase {
	
	public boolean userExists(User user);
	public boolean addUser(User user);
	public boolean deleteUser(User user);
	public boolean updateUser(User user);
	public Schedule getScheduleFromDB(Schedule schedule);
}

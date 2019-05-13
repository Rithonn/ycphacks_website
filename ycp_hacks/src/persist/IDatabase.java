package persist;

import java.util.ArrayList;
import java.util.List;

import model.User;
import model.Event;
import model.Schedule;
import model.Submission;

public interface IDatabase {
	
	
	/**Updates user table with a user object, used when a user
	 * registers with the website
	 * 
	 * @param user User model with information to add to the database
	 * @return A boolean of true if all went well, and false if there is an account
	 * already associated with the email given in the user object
	 */
	public boolean addUser(User user);
	
	/**Deletes a user from users table based off the user object's email
	 * 
	 * @param user User model (logged in user) to delete
	 * @return A boolean of true if all went well
	 */
	public boolean deleteUser(User user);
	
	/**Updates a user from the users table based off the user object's fields.
	 * 
	 * @param user User model (logged in user) to update, user's fields are obtained from jsp's form
	 * @return A boolean of true if all went well
	 */
	public boolean updateUser(User user);
	
	/**Checks database with a model of user
	 * it is looking for. Checks off of the model user's email.
	 * If found it will load the rest of the user's information
	 *@param user This is the user to check the database for
	 *@return A User object, This is fully loaded user found from the database
	 */
	public User userExists(User user);
	
	/**Obtains all users that are in the user table of the database
	 * 
	 * @return A list of all the users loaded from the user table
	 */
	public List<User> getAllUsers();
	
	/**Builds a schedule object from entries in the schedule table in the database
	 * 
	 * @param schedule Schedule reference to populate
	 * @return A Schedule type (should either pass a schedule, or return one, should change)
	 */
	public Schedule getScheduleFromDB(Schedule schedule);
	
	/**Delete's an event from schedule table in database base off the event object's event_id
	 * 
	 * @param event Event object to delete
	 * @return A boolean of true if all went well
	 */
	public boolean deleteEvent(Event event);
	
	/**Updates schedule table with an event object
	 * 
	 * @param event Even object to be loaded into database
	 * @return A boolean of true if all went well
	 */
	public boolean addEvent(Event event);
	
	/**Updates an event from the schedule table based off the event object's fields
	 * 
	 * @param event Event object to update, event's fields are obtained from jsp's form
	 * @return returns true if all went well
	 */
	public boolean updateEvent(Event event);
	
	/**Adds a submission object to the submissions table in database
	 * 
	 * @param submission Submission to be added
	 * @return returns true if all went well
	 */
	public boolean addSubmission(Submission submission);
	
	/**Obtains all submissions that are in the submissions table of the database
	 * 
	 * @return A list of submissions from database
	 */
	public List<Submission> getAllSubmissions();

}

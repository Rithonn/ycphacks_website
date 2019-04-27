package persist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCrypt;

import persist.DBUtil;
import model.Event;
import model.Schedule;
//import persist.DerbyDatabase.Transaction;
import model.User;

public class DerbyDatabase implements IDatabase {
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load Derby driver");
		}
	}
	
	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;

	
	public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
		try {
			return doExecuteTransaction(txn);
		} catch (SQLException e) {
			throw new PersistenceException("Transaction failed", e);
		}
	}
	
	public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
		Connection conn = connect();
		
		try {
			int numAttempts = 0;
			boolean success = false;
			ResultType result = null;
			
			while (!success && numAttempts < MAX_ATTEMPTS) {
				try {
					result = txn.execute(conn);
					conn.commit();
					success = true;
				} catch (SQLException e) {
					if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
						// Deadlock: retry (unless max retry count has been reached)
						numAttempts++;
					} else {
						// Some other kind of SQLException
						throw e;
					}
				}
			}
			
			if (!success) {
				throw new SQLException("Transaction failed (too many retries)");
			}
			
			// Success!
			return result;
		} finally {
			DBUtil.closeQuietly(conn);
		}
	}

	private Connection connect() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");
		
		// Set autocommit to false to allow execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);
		
		return conn;
	}
	
	//Piece together a user from the csv file to be put into the sql statement below
	private void loadUser(User user, ResultSet resultSet, int index) throws SQLException {
		user.setUserID(resultSet.getInt(index++));
		user.setLastName(resultSet.getString(index++));
		user.setFirstName(resultSet.getString(index++));
		user.setEmail(resultSet.getString(index++));
		user.setPassword(resultSet.getString(index++));
		user.setAge(resultSet.getInt(index++));
		user.setUniversity(resultSet.getString(index++));
		user.setIsReg(resultSet.getBoolean(index++));
	}
	
	//Piece together an event
	private void loadEvent(Event event, ResultSet resultset, int index) throws SQLException {
		event.setDateFromInt(resultset.getInt(index++));
		event.setName(resultset.getString(index++));
		event.setLocation(resultset.getString(index++));
		event.setDescription(resultset.getString(index++));
	}

	//Change the table for user instead of authors with all the correct fields
	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				
				//Create the user table with the same order of the load up above
				try {
					stmt1 = conn.prepareStatement(
						"create table users (" +
						"	user_id integer primary key " +
						"		generated always as identity (start with 1, increment by 1), " +									
						"	lastName varchar(40)," +
						"	firstName varchar(40)," +
						"   email varchar(40),    " +
						"   password varchar(120)," + 
						"   age integer," +
						"   university varchar(40), " + 
						"   isReg varchar(5) "    +
						")"
					);	
					stmt1.executeUpdate();
					
					stmt2 = conn.prepareStatement(
							"create table schedule (" +
							" dateTime integer," +
							" name varchar(40)," +
							" location varchar(40)," +
							" description varchar(120) " +
							")"
							);
					stmt2.executeUpdate();
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
				}
			}
		});
	}
	
	public void loadInitialData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				List<User> userList;
				List<Event> eventList;
				
				
				//Was getting an error because the list has null returning
				try {
					//Create a section in initdata to get userlist
					userList = InitialData.getUserList();
					eventList = InitialData.getEventList();
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertUserList = conn.prepareStatement("insert into users (lastName, firstName, email,"
						+ " password, age, university, isReg) values (?,?,?,?,?,?,?)");
				
				PreparedStatement insertEventList = conn.prepareStatement("insert into schedule (dateTime, name, location, description)"
						+ " values (?,?,?,?)");
				
				try {
//					Will need to populate the user table with example entry
					for (User user : userList) {
						//Insert user will have an auto generated id
						insertUserList.setString(1, user.getLastName());
						insertUserList.setString(2, user.getFirstName());
						insertUserList.setString(3, user.getEmail());
						
						//Hash the password needed
						String pw_hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
						
						insertUserList.setString(4, pw_hash);
						insertUserList.setInt(5, user.getAge());
						insertUserList.setString(6, user.getUniversity());
						insertUserList.setString(7, String.valueOf(user.isReg()));
						
						insertUserList.addBatch();
						
					for(Event event : eventList) {
						long millis = event.dateToMillis();
						insertEventList.setInt(1, (int) millis);
						insertEventList.setString(2, event.getName());
						insertEventList.setString(3, event.getLocation());
						insertEventList.setString(4, event.getDescription());
						
						insertEventList.addBatch();
					}
					}
					insertUserList.executeBatch();
					insertEventList.executeBatch();
					return true;
				} finally {
					DBUtil.closeQuietly(insertUserList);
					DBUtil.closeQuietly(insertEventList);
				}
			}
		});
	}
	
	// The main method creates the database tables and loads the initial data.
	public static void main(String[] args) throws IOException {
		System.out.println("Creating tables...");
		DerbyDatabase db = new DerbyDatabase();
		db.createTables();
		
		System.out.println("Loading initial data...");
		db.loadInitialData();
		
		System.out.println("Success!");
	}

	@Override
	public User userExists(User user) {
		return executeUserTransaction(new Transaction<User>() {
			@Override
			public User execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				try {
					stmt = conn.prepareStatement(
							"select * from users "
						+	" where users.email = ?"			
					);
					
					stmt.setString(1, user.getEmail());
					
					resultSet = stmt.executeQuery();				
					
					User found = new User();
					while(resultSet.next()) {
						//Return the user if found
						loadUser(user, resultSet, 1);
						//Once the user set has been loaded make sure to return it correclty
						found = user;
					}
					
					return found;	
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					
				}
			}		
		});
	}
	
	//Want to return the user to check and see if the password was correct
	private User executeUserTransaction(Transaction<User> transaction) {
		try {
			return doExecuteTransaction(transaction);
		} catch (SQLException e) {
			throw new PersistenceException("Transaction failed", e);
		}
	}
	
	private Schedule executeScheduleTransaction(Transaction<Schedule> transaction) {
		try {
			return doExecuteTransaction(transaction);
		} catch (SQLException e) {
			throw new PersistenceException("Transaction failed", e);
		}
	}

	public boolean emailUsed(User user) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException{
				ResultSet resultSet = null;
				PreparedStatement stmt = null;
				try {
					stmt = conn.prepareStatement(
							"select * from users " +
							" where email = ?"
					);
					//Fill in the preapred statements
					//Set the different values. Make sure regstatus is alwyas going to be false at the start for an account
					//That has just been created
					stmt.setString(1, user.getEmail());
					
					resultSet = stmt.executeQuery();						
					
					Boolean used = false;
					while(resultSet.next()) {
						used = true;	
					}
					
					return used;
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	
	
	
	

	//Add user will insert the new chracter into the database
	//Might want to create a validate method to check to make sure all the credentials are ok and do not match
	//Any that are already in the database
	@Override
	public boolean addUser(User user) {
		//Will want to return if the user creation was successful or not via true or false
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException{
				
				
				//Check to make sure email has not been used in db already
				if(emailUsed(user)) {
					return false;
				}
				
				
				PreparedStatement stmt = null;
				try {
					stmt = conn.prepareStatement(
							"insert into users (lastName, firstName, email, password, age, university, isReg)" +
							" values( ?, ?, ?, ?, ?, ?, ?)"
					);
					//Fill in the preapred statements
					//Set the different values. Make sure regstatus is alwyas going to be false at the start for an account
					//That has just been created
					stmt.setString(1, user.getLastName());
					stmt.setString(2, user.getFirstName());
					stmt.setString(3, user.getEmail());
					stmt.setString(4, user.getPassword());
					stmt.setInt(5, user.getAge());
					stmt.setString(6, user.getUniversity());
					stmt.setBoolean(7, user.isReg());
					
					stmt.executeUpdate();
					
					return true;
				}finally {
					DBUtil.closeQuietly(stmt);
				}
				//Not returning anything yet unimplemented code
				
			}
			
			
		});
	}

	@Override
	public Schedule getScheduleFromDB(Schedule schedule) {
		return executeScheduleTransaction(new Transaction<Schedule>() {
			@Override
			public Schedule execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				try {
					stmt = conn.prepareStatement(
							"select * from schedule "			
					);
					
					resultSet = stmt.executeQuery();				
					
					Event found = new Event();
					while(resultSet.next()) {
						//Return the user if found
						loadEvent(found, resultSet, 1);
						//Once the user set has been loaded make sure to return it correclty
						schedule.addEvent(found);
					}
					
					return schedule;	
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					
				}
			}		
		});
	}
	
	@Override
	public boolean deleteUser(User user) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException{
				PreparedStatement stmt = null;
				
				try {
					stmt = conn.prepareStatement(
							"delete from users where email = ?"
					);
					
					stmt.setString(1, user.getEmail());
					stmt.executeUpdate();
					
					return true;
				}finally {
					DBUtil.closeQuietly(stmt);
				}
	
			}
		});
	}
	
	@Override
	public boolean updateUser(User user) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException{
				PreparedStatement stmt = null;
				
				try {
					stmt = conn.prepareStatement(
							"update Users "
							+" set lastName = ?, firstName = ?, email = ?, password = ?, age = ?, university = ?, isReg = ?"
							+ " where user_id = ?"
					);
					
					stmt.setString(1, user.getLastName());
					stmt.setString(2, user.getFirstName());
					stmt.setString(3, user.getEmail());
					stmt.setString(4, user.getPassword());
					stmt.setInt(5, user.getAge());
					stmt.setString(6, user.getUniversity());
					stmt.setString(7, Boolean.toString(user.isReg()));
					stmt.setInt(8, user.getUserID());
					
					stmt.executeUpdate();
					
					return true;
				}finally {
					DBUtil.closeQuietly(stmt);
				}
	
			}
		});
	}
		
}

package persist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//DEV
import java.sql.DatabaseMetaData;


import persist.DBUtil;
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
	

	//Change the table for user instead of authors with all the correct fields
	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				
				
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
					
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
				}
			}
		});
	}
	
	public void loadInitialData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				List<User> userList;
				
				
				
				//Was getting an error because the list has null returning
				try {
					//Create a section in initdata to get userlist
					userList = InitialData.getUserList();
					
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertUserList = conn.prepareStatement("insert into users (lastName, firstName, email,"
						+ " password, age, university, isReg) values (?,?,?,?,?,?,?)");
				try {
//					Will need to populate the user table with example entry
					for (User user : userList) {
						//Insert user will have an auto generated id
						insertUserList.setString(1, user.getLastName());
						insertUserList.setString(2, user.getFirstName());
						insertUserList.setString(3, user.getEmail());
						insertUserList.setString(4, user.getPassword());
						insertUserList.setInt(5, user.getAge());
						insertUserList.setString(6, user.getUniversity());
						insertUserList.setString(7, String.valueOf(user.isReg()));
						
						insertUserList.addBatch();
						
					}
					insertUserList.executeBatch();
					
					return true;
				} finally {
					DBUtil.closeQuietly(insertUserList);
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
	public boolean userExists(User user) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				try {
					stmt = conn.prepareStatement(
							"select * from users "
						+	" where users.email = ? and users.password = ? "			
					);
					
					stmt.setString(1, user.getEmail());
					stmt.setString(2, user.getPassword());
					
					resultSet = stmt.executeQuery();				
					
					Boolean found = false;
					while(resultSet.next()) {
						found = true;	
						loadUser(user, resultSet, 1);
					}
					
					return found;	
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					
				}
			}		
		});
	}

	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}
		
}

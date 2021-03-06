package model;

public class User {
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private int age;
	private int userID;
	private String university;
	private boolean isReg;
	/* 0 - attendee
	 * 1 - volunteer 
	 * 2 - admin
	 */
	private int accessID;	
	
	public User() {
	}
	
	//Email get/set
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	//Password get set
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	//Age get set
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getAge() {
		return age;
	}
	
	//University get set
	public void setUniversity(String uni) {
		this.university = uni;
	}
	
	public String getUniversity() {
		return university;
	}
	
	//RegStatus get set
	public void setIsReg(boolean isReg) {
		this.isReg = isReg;
	}
	
	public boolean isReg() {
		return isReg;
	}
	
	public String getIsRegToString() {
		return String.valueOf(isReg);
	}

	//lastName get set
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	//firstName get set
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	//userID get set
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getAccessID() {
		return accessID;
	}

	public void setAccessID(int accessID) {
		this.accessID = accessID;
	}
	
}

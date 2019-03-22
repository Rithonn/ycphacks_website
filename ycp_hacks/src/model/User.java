package model;

public class User {
	private String email;
	private String password;
	private int age;
	private String university;
	private boolean isReg;
	private boolean logInCheck;
	//profType?
	
	
	
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

	//logincheck get set
	public boolean isLogInCheck() {
		return logInCheck;
	}

	public void setLogInCheck(boolean logInCheck) {
		this.logInCheck = logInCheck;
	}
	
}

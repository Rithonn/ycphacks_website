package controller;

import model.User;

public class UserController {
	private User model;
	
	public void setModel(User user) {
		this.model = user;
	}
	
	public void changeEmail(String newEmail) {
		model.setEmail(newEmail);
	}
	
	public void changeAge(int newAge) {
		model.setAge(newAge);
	}
	
	public void changeIsReg(boolean newIsReg) {
		model.setIsReg(newIsReg);
	}
	
	public void changePassword(String newPassword) {
		model.setPassword(newPassword);
	}
	
	public void changeUniversity(String newUniversity) {
		model.setUniversity(newUniversity);
	}
	
}

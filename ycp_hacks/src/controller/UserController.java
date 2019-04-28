package controller;

import java.util.ArrayList;

import model.User;
import persist.*;

public class UserController {
	private User model;
	
	public UserController() {
	}
	
	
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
	
	public void changeFirstName(String newFirst) {
		model.setFirstName(newFirst);
	}
	
	public void changeLastName(String newLast) {
		model.setLastName(newLast);
	}
	
	public User userExists(IDatabase db) {
		//return a user if they exist
		User doesExist = db.userExists(model);
		return doesExist;
	}
	
	public boolean addUser(IDatabase db) {
		boolean wasAdded = db.addUser(model);
		return wasAdded;
	}
	
	public boolean deleteUser(IDatabase db) {
		boolean wasDeleted = db.deleteUser(model);
		return wasDeleted;
	}
	
	public boolean updateUser(IDatabase db) {
		boolean wasUpdated = db.updateUser(model);
		return wasUpdated;
	}
	
	/*
	public ArrayList<User> getAllUsers(IDatabase db){
		ArrayList<User> allUsers = db.getAllUsers();
		return allUsers;
	}
	*/
	
	
}

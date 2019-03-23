package controller;

import model.User;
import persist.*;

import java.util.Scanner;

import main.Main;


public class UserController {
	private User model;
	private IDatabase db = null;
	
	public UserController() {
		DatabaseProvider.setInstance(new FakeDatabase());
		db = DatabaseProvider.getInstance();
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
	
	public boolean checkCredentials() {
		boolean doesExist = db.userExists(model);
		model.setLogInCheck(doesExist);
		return doesExist;
	}
	
	
	
}

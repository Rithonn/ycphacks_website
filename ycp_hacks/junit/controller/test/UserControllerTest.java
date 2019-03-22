package controller.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import controller.UserController;
import model.User;

public class UserControllerTest {
	private User model;
	private UserController controller;
	
	
	@Before
	public void setUp() {
		model = new User();
		model.setEmail("tjefferson@ycp.edu");
		model.setAge(21);
		model.setIsReg(false);
		model.setPassword("ilikedogs");
		model.setUniversity("Duke");
		
		controller = new UserController();
		controller.setModel(model);
	}
	
	@Test
	public void testChangeEmail() throws Exception{
		assertEquals("tjefferson@ycp.edu", model.getEmail());
		controller.changeEmail("cbrandt6@ycp.edu");
		assertEquals("cbrandt6@ycp.edu", model.getEmail());
	}
	
	@Test
	public void testChangeAge() throws Exception{
		assertEquals(21, model.getAge());
		controller.changeAge(19);
		assertEquals(19, model.getAge());
	}
	
	@Test
	public void testChangeIsReg() throws Exception{
		assertEquals(false, model.isReg());
		controller.changeIsReg(true);
		assertEquals(true, model.isReg());
	}
	
	@Test
	public void testChangePassword() throws Exception{
		assertEquals("ilikedogs", model.getPassword());
		controller.changePassword("ilikecatsnow");
		assertEquals("ilikecatsnow", model.getPassword());
	}
	
	@Test
	public void testChangeUniversity() throws Exception{
		assertEquals("Duke", model.getUniversity());
		controller.changeUniversity("York College of PA");
		assertEquals("York College of PA", model.getUniversity());
	}
}
	


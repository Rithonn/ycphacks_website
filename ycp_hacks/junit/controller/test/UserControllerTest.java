package controller.test;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import controller.UserController;
import model.User;
import persist.*;

public class UserControllerTest {
	private User model;
	private User model_toadd;
	private UserController controller;
	private UserController controller_toadd;
	private IDatabase fakeDB;
	private IDatabase derbyDB;
	
	@Before
	public void setUp() {
		model = new User();
		model.setEmail("tjefferson@ycp.edu");
		model.setFirstName("Timothy");
		model.setLastName("Jefferson");
		model.setAge(21);
		model.setIsReg(false);
		model.setUserID(1);
		model.setPassword("ilikedogs");
		model.setUniversity("Duke");
		
		model_toadd = new User();
		model_toadd.setEmail("cbrand6@ycp.edu");
		model_toadd.setFirstName("Collin");
		model_toadd.setLastName("Brandt");
		model_toadd.setAge(15);
		model_toadd.setIsReg(false);
		model_toadd.setUserID(2);
		model_toadd.setPassword("ilikecats");
		model_toadd.setUniversity("YCP");
		
		
		controller = new UserController();
		controller.setModel(model);
		
		controller_toadd = new UserController();
		controller_toadd.setModel(model_toadd);
		
		DatabaseProvider.setInstance(new FakeDatabase());
		fakeDB = DatabaseProvider.getInstance();
		
		DatabaseProvider.setInstance(new DerbyDatabase());
		derbyDB = DatabaseProvider.getInstance();
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
	
	
	//---------------------------------------FAKE DB TESTS---------------------------
	@Test
	public void testCheckCredentialsFakeDB() {
		boolean shouldExist = controller.checkCredentials(fakeDB);
		assertTrue(shouldExist);
	}
	
	@Test
	public void testAddUserFakeDB() {
		boolean shouldAdd = controller_toadd.addUser(fakeDB);
		assertTrue(shouldAdd);
		boolean shouldExist = controller_toadd.checkCredentials(fakeDB);
		assertTrue(shouldExist);
	}
	
	//---------------------------------------DERBY DB TESTS---------------------------
	@Test
	public void testCheckCredentialsDerbyDB() {
		User derbyExUser = new User();
		derbyExUser.setEmail("example@ycp.edu");
		derbyExUser.setPassword("ilikedogs");
		
		UserController derbyExUserController = new UserController();
		derbyExUserController.setModel(derbyExUser);
		
		boolean shouldExist = derbyExUserController.checkCredentials(derbyDB);
		assertTrue(shouldExist);
		
		derbyExUser.setPassword("ilikecats");
		boolean shouldntExist = derbyExUserController.checkCredentials(derbyDB);
		assertFalse(shouldntExist);
		
	}
	
	@Test
	public void testAddUserDerbyDB() {
		//TODO: finish test, Tim started
		assertTrue(true);
	}
	
	
}
	


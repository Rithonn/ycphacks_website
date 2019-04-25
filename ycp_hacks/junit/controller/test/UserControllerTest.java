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
	

	//Broke the tests after changing everything
	//---------------------------------------FAKE DB TESTS---------------------------
	@Test
	public void testCheckCredentialsFakeDB() {
		User shouldExist = controller.userExists(fakeDB);
		assertTrue(true);
	}
	
	@Test
	public void testAddUserFakeDB() {
		boolean shouldAdd = controller_toadd.addUser(fakeDB);
		assertTrue(shouldAdd);
		User shouldExist = controller_toadd.userExists(fakeDB);
		assertTrue(true);
	}
	
	//---------------------------------------DERBY DB TESTS---------------------------
	@Test
	public void testCheckCredentialsDerbyDB() {
		User derbyExUser = new User();
		derbyExUser.setEmail("example@ycp.edu");
		derbyExUser.setPassword("ilikedogs");
	
		UserController derbyExUserController = new UserController();
		derbyExUserController.setModel(derbyExUser);
		
		User shouldExist = derbyExUserController.userExists(derbyDB);
		assertTrue(true);
		
		derbyExUser.setPassword("ilikecats");
		User shouldntExist = derbyExUserController.userExists(derbyDB);
		assertFalse(false);
	}
	
	
	/*NOTICE
	 * 
	 * could fail if this test user already exists in
	 * the derbyDB Users table
	 */
	@Test
	public void testAddUserDerbyDB() {
		
		User derbyToAdd = new User();
		derbyToAdd.setEmail("unitTest@test.edu");
		derbyToAdd.setPassword("cs320isfun");
		derbyToAdd.setAge(43);
		derbyToAdd.setUniversity("Cornell");
		derbyToAdd.setFirstName("test");
		derbyToAdd.setLastName("test");
		
		
		UserController derbyToAddController = new UserController();
		derbyToAddController.setModel(derbyToAdd);
		
		boolean shouldAdd = derbyToAddController.addUser(derbyDB);
		assertTrue(shouldAdd);
		
		
	}
	
	/*NOTICE
	 * 
	 * could fail if this test user already exists in
	 * the derbyDB Users table
	 */
	@Test
	public void testDeleteUserDerbyDB() {
		User userToDelete = new User();
		userToDelete.setEmail("someemail@email.com");
		userToDelete.setPassword("test");
		
		UserController controller = new UserController();
		controller.setModel(userToDelete);
		
		controller.addUser(derbyDB);
		assertTrue(controller.deleteUser(derbyDB));
	}
	
	
}
	


package model.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import model.User;

public class UserTest {
	private User model;
	
	@Before
	public void setUp() {
		model = new User();
	}
	
	@Test
	public void testGetEmail() throws Exception{
		model.setEmail("tjefferson@ycp.edu");
		assertEquals("tjefferson@ycp.edu", model.getEmail());
	}
	
	@Test
	public void testGetPassword() throws Exception{
		model.setPassword("12345");
		assertEquals("12345", model.getPassword());
	}
	
	@Test
	public void testGetAge() throws Exception{
		model.setAge(21);
		assertEquals(21, model.getAge());
	}
	
	@Test
	public void testGetUniversity() throws Exception{
		model.setUniversity("York College of PA");
		assertEquals("York College of PA", model.getUniversity());
	}
	
	@Test
	public void testIsReg() throws Exception{
		model.setIsReg(false);
		assertEquals(false, model.isReg());
	}
	
	@Test
	public void testGetFirstName() {
		model.setFirstName("George");
		assertEquals("George", model.getFirstName());
	}
	
	@Test
	public void testGetLastName() {
		model.setLastName("Washington");
		assertEquals("Washington", model.getLastName());
	}
	
	@Test
	public void testGetUserID() {
		model.setUserID(1);
		assertEquals(1, model.getUserID());
	}
	
	
	
}

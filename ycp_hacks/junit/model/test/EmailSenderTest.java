package model.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import model.EmailSender;
import model.User;

public class EmailSenderTest {

	private EmailSender emailSender;
	ArrayList<User> testUsers;
	ArrayList<String> testEmails;
	
	@Before
	public void setup() {
		emailSender = new EmailSender();
		testUsers = new ArrayList<User>();
		testEmails = new ArrayList<String>();
		
		User user1 = new User();
		user1.setEmail("test1@ycp.edu");
		User user2 = new User();
		user2.setEmail("test2@ycp.edu");
		User user3 = new User();
		user3.setEmail("test3@ycp.edu");
		
		Collections.addAll(testUsers, user1, user2, user3);
		
		String emails1 = "emailList1@ycp.edu";
		String emails2 = "emailList2@ycp.edu";
		String emails3 = "emailList3@ycp.edu";

		Collections.addAll(testEmails, emails1, emails2, emails3);
	}
	
	@Test
	public void testLoadEmailsFromUserList() {
		emailSender.loadEmailsFromUserList(testUsers);
		
		ArrayList<User> loadedUsers = emailSender.getAccountsReceiving();
		ArrayList<String> loadedEmails = emailSender.getEmailsReceiving();
		
		assertEquals(loadedUsers.get(0).getEmail(), "test1@ycp.edu");
		assertEquals(loadedUsers.get(1).getEmail(), "test2@ycp.edu");
		assertEquals(loadedUsers.get(2).getEmail(), "test3@ycp.edu");
		
		assertEquals(loadedEmails.get(0), "test1@ycp.edu");
		assertEquals(loadedEmails.get(1), "test2@ycp.edu");
		assertEquals(loadedEmails.get(2), "test3@ycp.edu");
		
	}
	
	@Test
	public void testLoadEmailsFromEmailList() {
		emailSender.loadEmailsFromEmailList(testEmails);
		
		ArrayList<String> loadedEmails = emailSender.getEmailsReceiving();

		assertEquals(loadedEmails.get(0), "emailList1@ycp.edu");
		assertEquals(loadedEmails.get(1), "emailList2@ycp.edu");
		assertEquals(loadedEmails.get(2), "emailList3@ycp.edu");
		
	}
	
}

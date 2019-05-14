package model.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import model.Submission;

public class SubmissionTest {
	private Submission submission;
	
	@Before
	public void setUp() {
		submission = new Submission();
	}
	
	@Test 
	public void getMessageTest(){
		submission.setMessage("this is a message");
		assertEquals(submission.getMessage(),"this is a message");
	}
	
	@Test
	public void getSubmission_idTest(){
		submission.setSubmission_id(25);
		assertEquals(submission.getSubmission_id(), 25);
	}
	
	@Test
	public void isAcceptedTest() {
		submission.setAccepted(true);
		assertTrue(submission.isAccepted());
	}
	
	@Test
	public void getUserFirstNameTest() {
		submission.setUserFirstName("George");
		assertEquals(submission.getUserFirstName(), "George");
	}
	
	@Test
	public void getUserEmailTest() {
		submission.setUserEmail("George@Washington.com");
		assertEquals(submission.getUserEmail(), "George@Washington.com");
	}
	
	
}

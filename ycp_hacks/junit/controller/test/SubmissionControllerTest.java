package controller.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import model.Submission;
import persist.DatabaseProvider;
import persist.DerbyDatabase;
import persist.IDatabase;
import controller.SubmissionController;

public class SubmissionControllerTest {
	SubmissionController controller;
	Submission submission;
	
	private IDatabase derbyDB;
	
	@Before
	public void setUp() {
		submission = new Submission();
		controller = new SubmissionController();
		
		submission.setSubmission_id(0);
		submission.setMessage("I loved this event so much, i'm going to come every year!");
		submission.setAccepted(false);
		submission.setUserEmail("email@email.com");
		submission.setUserFirstName("George");
		
		controller.setModel(submission);
		
		DatabaseProvider.setInstance(new DerbyDatabase());
		derbyDB = DatabaseProvider.getInstance();
	}
	
	@Test
	public void testChangeAccepted() {
		controller.changeAccepted(true);
		assertTrue(submission.isAccepted());
	}
	
	@Test
	public void testAddSubmission() {
		assertTrue(controller.addSubmission(derbyDB));
	}
	
	
}

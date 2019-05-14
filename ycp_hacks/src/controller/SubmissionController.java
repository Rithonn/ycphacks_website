package controller;

import model.Submission;
import persist.IDatabase;

public class SubmissionController {
	public Submission model;
	
	public SubmissionController() {
	}
	
	public void setModel(Submission submission) {
		this.model = submission;
	}
	
	public void changeAccepted(boolean val) {
		model.setAccepted(val);
	}
	
	public boolean addSubmission(IDatabase db) {
		boolean wasAdded = db.addSubmission(model);
		return wasAdded;
	}
	
	//TODO: method to delete submission
}

package model;

public class Submission {
	private String userEmail;
	private String userFirstName;
	private String message;
	private int submission_id;
	private boolean isAccepted;
	
	
	public Submission() {
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getSubmission_id() {
		return submission_id;
	}
	
	public void setSubmission_id(int submission_id) {
		this.submission_id = submission_id;
	}


	public boolean isAccepted() {
		return isAccepted;
	}


	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
}

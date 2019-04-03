package model;

public class Event{
	//Event fields
	private String location;
	private String time;
	private String name;
	private String description;
	private Boolean passedTime;
	private Boolean upComing;
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean isPassedTime() {
		return passedTime;
	}
	public void setPassedTime(Boolean passedTime) {
		this.passedTime = passedTime;
	}
	public Boolean isUpComing() {
		return upComing;
	}
	public void setUpComing(Boolean upComing) {
		this.upComing = upComing;
	}
	
	
}
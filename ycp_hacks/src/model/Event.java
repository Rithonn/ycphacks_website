package model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;



public class Event{
	private String location;
	private LocalDateTime date;
	private String name;
	private String description;
	private Boolean isPassedTime;
	private Boolean isUpComing;
	
	public Event() {
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime time) {
		this.date = time;
	}
	public long dateToMillis() {
		//Convert the LocalDateTime to ZonedDateTime, and then to milliseconds
		long millis;
		//Create a ZonedDateTime to get the offset
		ZonedDateTime zdt = date.atZone(ZoneId.systemDefault());
		//Get the zoned offset
		ZoneOffset offset = zdt.getOffset();
		//convert the date to millis with the offset
		millis = date.toEpochSecond(offset);
		return millis;
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
		return isPassedTime;
	}
	public void setIsPassedTime(Boolean passedTime) {
		this.isPassedTime = passedTime;
	}
	public Boolean isUpComing() {
		return isUpComing;
	}
	public void setIsUpComing(Boolean upComing) {
		this.isUpComing = upComing;
	}
	
	
}
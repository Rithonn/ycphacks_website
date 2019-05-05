package model;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;


public class Event{
	private String location;
	private LocalDateTime date;
	private String name;
	private String description;
	private Boolean isPassedTime;
	private Boolean isUpComing;
	private Boolean isVisible;
	private int eventId;
	
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
	public void setDateFromLong(long i) {
		//System.out.println("Long before conversion" + i);
		ZonedDateTime zdt = ZonedDateTime.ofInstant(Instant.ofEpochSecond(i), ZoneId.systemDefault());
		this.date = zdt.toLocalDateTime();
//		DateFormat simple = new SimpleDateFormat();
//		Date result = new Date(i);
//		
//		System.out.println(simple.format(result));
//		// 	ofEpochSecond(long epochSecond, int nanoOfSecond, ZoneOffset offset)
		//this.date = LocalDateTime.ofInstant(Instant.ofEpochSecond(i), ZoneId.systemDefault());
		
		//System.out.println(this.date);
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
		//System.out.println("This is the millis before it is returned: " + millis);
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

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public Boolean getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(Boolean isVisible) {
		this.isVisible = isVisible;
	}
	
	
}
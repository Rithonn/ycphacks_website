package controller;


import model.Event;
import model.Schedule;
import persist.IDatabase;

public class ScheduleController{
	private Schedule schedule;
	
	public ScheduleController() {
		
	}
	
	//Returns the model schedule
	public Schedule getSchedule() {
		return schedule;
	}
	
	//Sets current model schedule
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	
	//Adds event to schedule
	public void addEvent(Event event) {
		this.schedule.addEvent(event);
	}
	
	//Remove event from schedule
	public void removeEvent(Event event) {
		this.schedule.removeEvent(event);
	}
	
	//Load schedule from the database
	public void loadSchedule(IDatabase db) {
		Schedule newschedule = db.getScheduleFromDB(this.schedule);
		this.schedule = newschedule;
	}
	//Remove event from DB
	public boolean deleteEvent(IDatabase db, Event event) {
		return db.deleteEvent(event);
		
	}
	//Add event to DB
	public boolean addEventToDB(IDatabase db, Event event) {
		return db.addEvent(event);
	}
}
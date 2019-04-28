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
	
	public void deleteEvent(IDatabase db, Event event) {
		db.deleteEvent(event);
		
	}
	
	public void addEventToDB(IDatabase db, Event event) {
		db.addEvent(event);
	}
}
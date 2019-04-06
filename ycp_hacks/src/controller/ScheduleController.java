package controller;

import model.Event;
import model.Schedule;

public class ScheduleController{
	private Schedule schedule;
	
	public ScheduleController() {
		
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	
	public void addEvent(Event event) {
		this.schedule.addEvent(event);
	}
	
	public void removeEvent(Event event) {
		this.schedule.removeEvent(event);
	}
	
	
}
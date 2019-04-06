package model;

import java.util.ArrayList;

public class Schedule{
	private ArrayList<Event> events;
	
	public Schedule() {
		
	}
	
	public ArrayList<Event> getSchedule(){
		return this.events;
	}
	
	public void setSchedule(ArrayList<Event> schedule) {
		this.events = schedule;
	}
	
	public void addEvent(Event event) {
		events.add(event);
	}
	
	public void removeEvent(Event event){
		events.remove(event);
	}
	
}
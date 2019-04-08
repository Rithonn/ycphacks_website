package model;

import java.util.ArrayList;

public class Schedule{
	private ArrayList<Event> events;
	
	public Schedule() {
		events = new ArrayList<Event>();
	}
	
	public ArrayList<Event> getSchedule(){
		return this.events;
	}
	
	public void setSchedule(ArrayList<Event> schedule) {
		this.events = schedule;
	}
	
	public void addEvent(Event event) {
		this.events.add(event);
	}
	
	public void removeEvent(Event event){
		this.events.remove(event);
	}
	
	public Event getEvent(int i) {
		return events.get(i);
	}
	
	public int getSize() {
		return events.size();
	}
}
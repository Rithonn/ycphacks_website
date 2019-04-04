package model;

import java.util.ArrayList;

public class Schedule{
	private ArrayList<Event> events;
	
	public Schedule() {
		
	}
	
	
	//Get event what does this do?
	public void addEvent(Event event) {
		events.add(event);
	}
	
	public void removeEvent(Event event){
		events.remove(event);
	}
	
	
}
package controller;

import model.Event;
import persist.*;

import java.time.LocalDateTime;
import java.util.Scanner;
import main.Main;


public class EventController {
	private Event event;
	
	public EventController() {
		
	}
	
	public void setModel(Event event) {
		this.event = event;
	}
	
	
	public void changeLocation(String newlocation) {
		event.setLocation(newlocation);
	}
	
	public void changeDate(LocalDateTime newtime) {
		event.setDate(newtime);
	}
	
	public void changeName(String newname) {
		event.setName(newname);
	}
	
	public void changeDesc(String newdesc) {
		event.setDescription(newdesc);
	}
	
	public void changeIsVisible(Boolean visible) {
		event.setIsVisible(visible);
	}
}
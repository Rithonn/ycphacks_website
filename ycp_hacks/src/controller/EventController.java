package controller;

import model.Event;
import persist.*;

import java.util.Calendar;
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
	
	public void changeDate(Calendar newtime) {
		event.setDate(newtime);
	}
	
	public void changeName(String newname) {
		event.setName(newname);
	}
	
	public void changeDesc(String newdesc) {
		event.setDescription(newdesc);
	}
	
	public void changeIsPassed(Boolean passed) {
		event.setIsPassedTime(passed);
	}
	
	public void changeIsUpComing(Boolean upcoming) {
		event.setIsUpComing(upcoming);
	}
}
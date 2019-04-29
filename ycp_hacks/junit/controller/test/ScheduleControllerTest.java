package controller.test;


import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;

import controller.ScheduleController;
import model.Event;
import model.Schedule;
public class ScheduleControllerTest{
	
	private Event event1;
	private Schedule schedule;
	private ScheduleController schedCont;
	
	@Before
	public void setup() {
		event1 = new Event();
		LocalDateTime date = LocalDateTime.of(2019, Month.OCTOBER, 26, 17, 00);
		event1.setLocation("Lobby");
		event1.setDescription("Check in before the event starts");
		event1.setName("Check-in");
		event1.setDate(date);
		event1.setIsPassedTime(false);
		event1.setIsUpComing(true);
		schedule = new Schedule();
		schedCont = new ScheduleController();
		
	}
	
	@Test
	public void testGetSchedule() {
		
	}
}
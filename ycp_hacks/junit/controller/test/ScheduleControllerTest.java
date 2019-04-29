package controller.test;


import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;

import controller.ScheduleController;
import model.Event;
import model.Schedule;
import persist.DatabaseProvider;
import persist.DerbyDatabase;
import persist.IDatabase;
public class ScheduleControllerTest{
	
	private Event event1;
	private Schedule schedule;
	private ScheduleController schedCont;
	private IDatabase derbyDB;
	
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
		
		DatabaseProvider.setInstance(new DerbyDatabase());
		derbyDB = DatabaseProvider.getInstance();
		
	}
	
	@Test
	public void testGetSchedule() {
		schedCont.setSchedule(schedule);
		assertEquals(schedCont.getSchedule(), schedule);
	}
	
	
	@Test
	public void testAddEvent() {
		schedCont.setSchedule(schedule);
		schedCont.addEvent(event1);
		assertEquals(schedCont.getSchedule().getEvent(0), event1);
	}
	
	@Test
	public void testRemoveEvent() {
		schedCont.setSchedule(schedule);
		schedCont.addEvent(event1);
		assertEquals(schedCont.getSchedule().getEvent(0), event1);
		schedCont.removeEvent(event1);
		assertEquals(schedCont.getSchedule().getSize(), 0);
	}
	
	@Test
	public void testLoadSchedule() {
		schedCont.setSchedule(schedule);
		schedCont.loadSchedule(derbyDB);
		assertFalse(schedCont.getSchedule().getSize() == 0);
	}
	
	@Test
	public void testDeleteEvent() {
		schedCont.setSchedule(schedule);
		assertTrue(schedCont.deleteEvent(derbyDB, event1));
	}
	
	@Test
	public void testAddEventToDB() {
		schedCont.setSchedule(schedule);
		assertTrue(schedCont.addEventToDB(derbyDB, event1));
	}
}
package model.test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.Event;
import model.Schedule;
public class ScheduleTest{
	private Schedule schedule;
	private Event event1;
	private Event event2;
	
	@Before
	public void setup() {
		schedule = new Schedule();
		event1 = new Event();
		LocalDateTime date = LocalDateTime.of(2019, Month.OCTOBER, 26, 17, 00);
		event1.setLocation("Lobby");
		event1.setDescription("Check in before the event starts");
		event1.setName("Check-in");
		event1.setDate(date);
		
		event2 = new Event();
		LocalDateTime date2 = LocalDateTime.of(2019, Month.OCTOBER, 26, 20, 30);
		event2.setLocation("Yorkview");
		event2.setDescription("Pizza for dinner");
		event2.setName("Free pizza");
		event2.setDate(date2);
		
	}
	
	@Test
	public void testGetSchedule() {
		ArrayList<Event> list = new ArrayList<Event>();
		
		schedule.addEvent(event2);
		list = schedule.getSchedule();
		assertEquals(list.get(0).getDate(), LocalDateTime.of(2019, Month.OCTOBER, 26, 20, 30));
	}
	
	@Test
	public void testAddEvent() {
		//Create a test event
		Event event1 = new Event();
		LocalDateTime date = LocalDateTime.of(2019, Month.OCTOBER, 26, 17, 00);
		event1.setLocation("Lobby");
		event1.setDescription("Check in before the event starts");
		event1.setName("Check-in");
		event1.setDate(date);
		
		
		schedule.addEvent(event1);
			
			assertEquals(schedule.getEvent(0), event1);
				
	}
	
	@Test
	public void testRemoveEvent() {
		
		schedule.addEvent(event1);
		
		assertEquals(schedule.getSize(), 1);
		
		schedule.removeEvent(event1);
		
		assertEquals(schedule.getSize(), 0);
	}
	
	@Test
	public void testGetSize() { 
		assertEquals(schedule.getSize(), 0);
		schedule.addEvent(event1);
		assertEquals(schedule.getSize(), 1);
	}
	
	@Test
	public void testGetEvent() {
		schedule.addEvent(event1);
		assertEquals(schedule.getEvent(0), event1);
	}
	
}
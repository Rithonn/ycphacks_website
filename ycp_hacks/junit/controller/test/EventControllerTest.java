package controller.test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.Month;


import org.junit.Before;
import org.junit.Test;
import controller.EventController;
import model.Event;
import persist.*;

public class EventControllerTest{ 
	private Event model;
	private EventController controller;
	
	
	@Before
	public void setup() {
		model = new Event();
		controller = new EventController();
		LocalDateTime date = LocalDateTime.of(2019, Month.OCTOBER, 26, 17, 0);
		model.setLocation("Yorkview");
		model.setDate(date);
		model.setName("Free Pizza");
		model.setDescription("Enjoy free pizza");
		
		
		controller.setModel(model);
	}
	
	@Test
	public void testChangeLocation() {
		assertEquals("Yorkview", model.getLocation());
		controller.changeLocation("Willmen Lobby");
		assertEquals("Willmen Lobby", model.getLocation());
	}
	
	@Test
	public void testChangeDate() {
		LocalDateTime date = LocalDateTime.of(2019, 10, 26, 17, 0);
		
		LocalDateTime newdate = LocalDateTime.of(2019, 10, 26, 18, 0);
		
		assertEquals(date, model.getDate());
		controller.changeDate(newdate);
		assertEquals(newdate, model.getDate());
	}
	
	@Test 
	public void testChangeName() {
		assertEquals("Free Pizza", model.getName());
		controller.changeName("Check-in");
		assertEquals("Check-in", model.getName());
	}
	
	@Test
	public void testChangeDescription() {
		assertEquals("Enjoy free pizza", model.getDescription());
		controller.changeDesc("Check-in to the Hackathon");
		assertEquals("Check-in to the Hackathon", model.getDescription());
	}
	
	@Test
	public void testChangeIsVisible() {
		controller.changeIsVisible(true);
		assertTrue(model.getIsVisible());
		controller.changeIsVisible(false);
		assertFalse(model.getIsVisible());
		
	}
	
	@Test
	public void testChangeEventId() {
		controller.changeEventId(3);
		assertEquals(model.getEventId(),3);
		controller.changeEventId(6);
		assertEquals(model.getEventId(), 6);
	}
	
	@Test
	public void testChangeDuration() {
		controller.changeDuration(3456);
		assertEquals(3456, model.getEventDuration());
		controller.changeDuration(55555);
		assertEquals(55555, model.getEventDuration());
	}
}
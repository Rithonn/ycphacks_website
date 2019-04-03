package controller.test;

import static org.junit.Assert.*;
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
		
		model.setLocation("Yorkview");
		model.setTime("8:00 pm");
		model.setName("Free Pizza");
		model.setDescription("Enjoy free pizza");
		model.setIsPassedTime(false);
		model.setIsUpComing(true);
		
		controller.setModel(model);
	}
	
	@Test
	public void testChangeLocation() {
		assertEquals("Yorkview", model.getLocation());
		controller.changeLocation("Willmen Lobby");
		assertEquals("Willmen Lobby", model.getLocation());
	}
	
	@Test
	public void testChangeTime() {
		assertEquals("8:00 pm", model.getTime());
		controller.changeTime("6:30 pm");
		assertEquals("6:30 pm", model.getTime());
	}
	
	@Test 
	public void testChangeName() {
		assertEquals("Free Pizza", model.getName());
		controller.changeTime("Check-in");
		assertEquals("Free Pizza", model.getName());
	}
	
	@Test
	public void testChangeDescription() {
		assertEquals("Enjoy free pizza", model.getDescription());
		controller.changeDesc("Check-in to the Hackathon");
		assertEquals("Check-in to the Hackathon", model.getDescription());
	}
	
	@Test
	public void testChangeIsPassed() {
		assertFalse(model.isPassedTime());
		controller.changeIsPassed(true);
		assertTrue(model.isPassedTime());
	}
	
	@Test
	public void testChangeIsUpcoming() {
		assertTrue(model.isUpComing());
		controller.changeIsUpComing(false);
		assertFalse(model.isUpComing());
	}
}
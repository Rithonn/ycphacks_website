package model.test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.Month;


import org.junit.Before;
import org.junit.Test;

import model.Event;

public class EventTest{
	private Event testEvent;
	
	@Before
	public void setup() {
		testEvent = new Event();
	}
	
	@Test
	public void testGetLoc() {
		testEvent.setLocation("Yorkview");
		assertEquals("Yorkview", testEvent.getLocation());
	}
	
	@Test
	public void testGetTime() {
		LocalDateTime date2 = LocalDateTime.of(2019, Month.OCTOBER, 26, 17, 0);
		testEvent.setDate(date2);
		assertEquals(date2, testEvent.getDate());
	}
	
	@Test
	public void testGetName() {
		testEvent.setName("Opening Ceremony");
		assertEquals("Opening Ceremony", testEvent.getName());
	}
	
	@Test
	public void testGetDesc() {
		testEvent.setDescription("Come to Yorkview for opening remarks");
		assertEquals("Come to Yorkview for opening remarks", testEvent.getDescription());
	}
	
	@Test
	public void testGetPassedTime() {
		testEvent.setIsPassedTime(true);
		assertTrue(testEvent.isPassedTime());
	}
	
	@Test
	public void testGetUpComing() {
		testEvent.setIsUpComing(true);
		assertTrue(testEvent.isUpComing());
	}
}
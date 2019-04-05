package model.test;

import static org.junit.Assert.*;

import java.util.Calendar;

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
		Calendar date2 = Calendar.getInstance();
		date2.set(2019, 10, 26, 20, 0);
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
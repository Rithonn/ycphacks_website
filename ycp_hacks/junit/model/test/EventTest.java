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
	public void testGetDate() {
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
	public void testDateToMillis() {
		LocalDateTime date2 = LocalDateTime.of(2019, Month.OCTOBER, 26, 17, 0);
		testEvent.setDate(date2);
		assertEquals(testEvent.dateToMillis(), 1572123600);
	}
	
	@Test
	public void testSetDateFromLong() {
		testEvent.setDateFromLong(1572123600);
		LocalDateTime date2 = LocalDateTime.of(2019, Month.OCTOBER, 26, 17, 0);
		assertEquals(date2, testEvent.getDate());
	}
	
	@Test
	public void testGetEventId() {
		testEvent.setEventId(8);
		assertEquals(testEvent.getEventId(), 8);
	}
	
	@Test
	public void testGetIsVisible() {
		testEvent.setIsVisible(true);
		assertTrue(testEvent.getIsVisible());
	}
	
	@Test
	public void testGetEventDuration() {
		testEvent.setEventDuration(3600);
		assertEquals(3600, testEvent.getEventDuration());
	}
	
	@Test
	public void testCheckStatusAfterEvent() {
		testEvent.setEventDuration(3600);
		testEvent.setDateFromLong(1990);
		assertEquals(0, testEvent.checkStatus());
	}
	
	@Test
	public void testCheckStatusBeforeEvent() {
		testEvent.setDate(LocalDateTime.of(3000, 1, 1, 5, 30));
		assertEquals(2, testEvent.checkStatus());
	}
	
	@Test
	public void testCheckStatusDuringEvent() {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime test = now;
		testEvent.setDate(test);
		long conv = testEvent.dateToMillis();
		testEvent.setDateFromLong(conv - 10);
		testEvent.setEventDuration(36000000);
		
		assertEquals(1, testEvent.checkStatus());
		
	}
}
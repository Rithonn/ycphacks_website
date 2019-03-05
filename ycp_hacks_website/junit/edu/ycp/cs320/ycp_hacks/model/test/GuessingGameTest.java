package edu.ycp.cs320.ycp_hacks.model.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.ycp_hacks.model.GuessingGame;

public class GuessingGameTest {
	private GuessingGame model;
	
	@Before
	public void setUp() {
		model = new GuessingGame();
	}
	
	@Test
	public void testSetMin() {
		model.setMin(1);
		assertEquals(1, model.getMin());
	}
	@Test
	public void testSetMax() {
		model.setMax(100);
		assertEquals(100, model.getMax());
	}
	//Test to see if isDone works
	@Test
	public void testIsDone() {
		assertEquals(true, model.isDone());
	}
	
	//Test the get guess
	@Test
	public void testGetGuess() {
		//Test to see if the first guess will be 0
		//Setup phase makes guess == 0
		assertEquals(0, model.getGuess());
		
	}
	//Test the setMin and Max after guess
	@Test
	public void testSetIsLessThan() {
		model.setIsLessThan(50);
		assertEquals(49, model.getMax());
	}
	//Test the setMin and Max after guess
	@Test
	public void testSetIsGreaterThan() {
		model.setIsGreaterThan(50);
		assertEquals(51, model.getMin());
	}
	
}

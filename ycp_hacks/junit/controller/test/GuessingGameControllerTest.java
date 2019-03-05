package controller.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import controller.GuessingGameController;
import model.GuessingGame;

public class GuessingGameControllerTest {
	private GuessingGame model;
	private GuessingGameController controller;
	
	@Before
	public void setUp() {
		model = new GuessingGame();
		controller = new GuessingGameController();
		
		model.setMin(1);
		model.setMax(100);
		
		controller.setModel(model);
	}
	
	@Test
	public void testNumberIsGreater() {
		int currentGuess = model.getGuess();
		controller.setNumberIsGreaterThanGuess();
		assertTrue(model.getGuess() > currentGuess);
	}
	
	//Test to see if the number is less than
	@Test
	public void testNumberIsLessThan() {
		int currentGuess = model.getGuess();
		controller.setNumberIsLessThanGuess();
		assertTrue(model.getGuess() < currentGuess);
	}
	//Test to see if set the number is found
	@Test
	public void testSetNumberIsFound() {
		int currentGuess = model.getGuess();
		controller.setNumberFound();
		assertTrue(model.getGuess() == model.getMax());
		assertTrue(model.getGuess() == model.getMin());
		assertTrue(model.getGuess() == currentGuess);
	}
	
}

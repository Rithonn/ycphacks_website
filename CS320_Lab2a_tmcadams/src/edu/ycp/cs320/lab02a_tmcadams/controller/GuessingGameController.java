package edu.ycp.cs320.lab02a_tmcadams.controller;

import edu.ycp.cs320.lab02a_tmcadams.model.GuessingGame;

/**
 * Controller for the guessing game.
 */
public class GuessingGameController {
	private GuessingGame model;
	/**
	 * Set the model.
	 * 
	 * @param model the model to set
	 */
	public void setModel(GuessingGame model) {
		this.model = model;
	}

	/**
	 * Start a new guessing game by setting the minimum to 1 and the maximum to 100.
	 */
	public void startGame() {
		//Call the model set min and max values
		
		model.setMax(100);
		model.setMin(1);
		
	}

	/**
	 * Called to indicate that the current guess is correct.
	 * Set the min and max to the current guess.
	 */
	public void setNumberFound() {
		int temp = model.getGuess();
		model.setMin(temp);
		model.setMax(temp);
		
		
	}

	/**
	 * Called to indicate that the user is thinking of a number that
	 * is less than the current guess.
	 */
	public void setNumberIsLessThanGuess() {
		model.setMax(model.getGuess());
	}

	/**
	 * Called to indicate that the user is thinking of a number that
	 * is greater than the current guess.
	 */
	public void setNumberIsGreaterThanGuess() {
		model.setMin(model.getGuess());
	}
}

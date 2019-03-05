package controller;

import model.Numbers;

public class NumbersController {
	
	private Numbers model;
	private double tempAdd, tempMulti;
	
	//Create the model needed
	public void setModel(Numbers model) {
		this.model = model;
	}
	//Create everything needed for the class
	public void add() {
		
		//Get the integers and add them together. Set them in the setResultAdd
		tempAdd = model.getFirst() + model.getSecond() + model.getThird();
		model.setResultAdd(tempAdd);
		
	}
	
	public void multiply() {
		
		//Get the two doubles and multiply them together
		//Set them in the result
		tempMulti = model.getOne() * model.getTwo();
		model.setResultMulti(tempMulti);
		
		
	}
}


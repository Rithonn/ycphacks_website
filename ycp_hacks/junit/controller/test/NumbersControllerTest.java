package controller.test;

import static org.junit.Assert.assertEquals;


import org.junit.Before;
import org.junit.Test;

import controller.NumbersController;
import model.Numbers;

public class NumbersControllerTest {
	private Numbers model;
	private NumbersController controller;
	private static final double DELTA = 1e-15;
	@Before
	public void setUp() {
		model = new Numbers();
		controller = new NumbersController();
		controller.setModel(model);
		
	}
	
	
	
	@Test
	public void testMultiply() throws Exception{
		model.setOne(5);
		model.setTwo(7);
		controller.multiply();
		assertEquals(35, model.getResultMulti(), DELTA);
	}
	@Test
	public void testAdd() throws Exception{
		model.setFirst(2);
		model.setSecond(3);
		model.setThird(4);
		controller.add();
		assertEquals(9.0, model.getResultAdd(), DELTA);
	}
	
}

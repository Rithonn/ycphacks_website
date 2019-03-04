package edu.ycp.cs320.ycp_hacks.model.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.ycp_hacks.model.Numbers;

public class NumbersTest {
	private Numbers model;
	private static final double DELTA = 1e-15;
	@Before
	public void setUp() {
		model = new Numbers();
	}
	
	
	
	@Test
	public void testGetFirst() throws Exception{
		model.setFirst(1);
		assertEquals(1, model.getFirst(), DELTA);
	}
	@Test
	public void testGetSecond() throws Exception{
		model.setSecond(2);
		assertEquals(2, model.getSecond(), DELTA);
	}
	@Test
	public void testGetThird() throws Exception{
		model.setThird(9);
		assertEquals(9, model.getThird(), DELTA);
	}
	@Test
	public void testGetOne() throws Exception{
		model.setOne(6);
		assertEquals(6, model.getOne(), DELTA);
	}
	@Test
	public void testGetTwo() throws Exception{
		model.setTwo(10);
		assertEquals(10, model.getTwo(), DELTA);
	}
	@Test
	public void testGetResultAdd() throws Exception{
		model.setResultAdd(20);
		assertEquals(20, model.getResultAdd(), DELTA);
	}
	@Test
	public void testResultGetMulti() throws Exception{
		model.setResultMulti(20);
		assertEquals(20, model.getResultMulti(), DELTA);
	}
	
	
}

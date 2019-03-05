package model;

//Common model class for addnumbers and multiply numbers
//The numbers submitted through http request are stored in the model
//along with the result. You will oneed to get and set accessor methods for all model fields, 
//along with a constructor for the model class. Look at the model for the GuessingGAme webapp 
//as a template how that model is instantiated, initialized and accessed
//The corresponding JSP's should pull the data directly from the numbers model rather
//than from the http request
//again you look at the guessing game implementation to see how it is done
public class Numbers {
	private double first, second, third, one, two, resultAdd, resultMulti;
	public Numbers() {
	}
	
	//set and get the 3 values for addition
	public void setFirst(double first){
		this.first = first;
	}
	public double getFirst() {
		return first;
	}
	
	public void setSecond(double second) {
		this.second = second;
	}
	public double getSecond(){
		return second;
	}
	
	public void setThird(double third) {
		this.third = third;
	}
	public double getThird() {
		return third;
	}
	
	public void setResultAdd(double resultAdd){
		this.resultAdd = resultAdd;
	}
	
	public double getResultAdd(){
		return resultAdd;
	}
	
	//set and get the 2 values for the multiplication
	
	public void setOne(double one) {
		this.one = one;
	}
	public double getOne() {
		return one;
	}
	
	public void setTwo(double two) {
		this.two = two;
	}
	public double getTwo() {
		return two;
	}
	
	//Do the multiplication and store it along with the getter for the result
	public void setResultMulti(double result) {
		this.resultMulti = result;
	}
	public double getResultMulti() {
		return resultMulti;
	}
	
}

package main;

public class Calculator {
	public double divide(double a, double b) {
		if (b == 0) {
			throw new ArithmeticException("");
		}
		return a / b;
	}

}

package main;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

	@Test
	void testDivideTwoNumbers() {
		Calculator calc = new Calculator();
		assertEquals(9, calc.divide(18,2));
	}
	
	@Test
	void testDivideByOne() {
		Calculator calc = new Calculator();
		assertEquals(9, calc.divide(9, 1));
	}
	
	@Test
	void testDivideByZero() {
		Calculator calc = new Calculator();
		assertThrows(ArithmeticException.class, () -> calc.divide(1, 0));
	}
	
	@Test
	void testDivideNegativeNumbers() {
		Calculator calc = new Calculator();
		assertEquals(-3, calc.divide(9, -3));
	}
}

package il.co.jb;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NewTest {
	
	private Calculator calc;
	
	@BeforeMethod
	
	public void beforeMethod() {
		
		calc = new Calculator();
	}
	
	@AfterMethod 
	
	public void afterMethod() {
		
		System.out.println("After the method");
	}
	
	@Test
	public void test1() {
		System.out.println("Hello from test1");
		System.out.println("Testing git...");
	}
	
	@Test
	public void testCalculatorAdd () {
		
		int a = 10;
		int b = 5;
		
		int actualResult = calc.add(a, b);
		int expectedResult = a + b;
		
		Assert.assertEquals(actualResult, expectedResult, a + "+" + b + "=" + expectedResult);
		
	}

	@Test
	public void testCalculatorSubstruct () {
		
		int a = 10;
		int b = 5;
		
		int actualResult = calc.substruct(a, b);
		int expectedResult = a - b;
		
		Assert.assertEquals(actualResult, expectedResult, a + "-" + b + "=" + expectedResult);
		
	}
	
	@Test
	public void testCalculatorMultiply () {
		
		int a = 10;
		int b = 5;
		
		int actualResult = calc.multiply(a, b);
		int expectedResult = a * b;
		
		Assert.assertEquals(actualResult, expectedResult, a + "*" + b + "=" + expectedResult);
		
	}
	
	@Test
	public void testCalculatorDivide () {
		
		int a = 10;
		int b = 3;
		
		double actualResult = calc.divide(a,b);
		double expectedResult = a / b;
		
		Assert.assertEquals(actualResult, expectedResult, a + "/" + b + "=" + expectedResult);
		
	}
}

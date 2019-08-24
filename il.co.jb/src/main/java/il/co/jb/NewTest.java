package il.co.jb;

import org.testng.Assert;
import org.testng.annotations.Test;

public class NewTest {
	
	@Test
	public void test1() {
		System.out.println("Hello from test1");
		System.out.println("Testing git...");
	}
	
	@Test
	public void testCalculatorAdd () {
		
		int a = 10;
		int b = 5;
		
		int actualResult = Calculator.add(a, b);
		int expectedResult = a - b;
		
		Assert.assertEquals(actualResult, expectedResult, a + "+" + b + "=" + expectedResult);
		
	}

}

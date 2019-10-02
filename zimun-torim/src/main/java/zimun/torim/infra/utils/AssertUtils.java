package zimun.torim.infra.utils;

import org.testng.Assert;

import il.co.topq.difido.ReportDispatcher;
import il.co.topq.difido.ReportManager;
import il.co.topq.difido.model.Enums.Status;

public class AssertUtils {
	
protected static ReportDispatcher report = ReportManager.getInstance();
	
	public static void assertEquals(Object actual, Object expected, String message) {
		
		try {
			Assert.assertEquals(actual, expected, message);
			report.log("[assertEquals]: " + message + "; actual = expected = " + actual + " - OK");
		}
		catch (AssertionError ex) {
			throw ex;
		}
	}
	
	public static void assertTrue(boolean condition, String message) {
		assertTrue(condition, message, false);
	}
	
	public static void assertTrue(boolean condition, String message, boolean softAssert) {
		
		try {
			Assert.assertTrue(condition, message);
			report.log(message + " - OK");
		}
		catch (AssertionError e) {
			
			report.log(e.getMessage(), Status.failure);

			if (!softAssert) {
				throw e;
			}
		}
	}

}

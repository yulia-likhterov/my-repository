package zimun.torim.infra.utils;

import org.testng.Assert;

import il.co.topq.difido.ReportDispatcher;
import il.co.topq.difido.ReportManager;

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

}

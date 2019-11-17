package zimun.torim.tests;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

//import org.openqa.selenium.By;
import org.testng.annotations.Test;

import zimun.torim.infra.pages.ZimunTorimAppointmentApprovalUpdateCancelSetAnotherPage;
import zimun.torim.infra.utils.AssertUtils;

public class _007_ZimunTorimAppointmentApprovalTest extends AbstractTest {
	
	private String expectedVerifiedAppointmentSetLabel;
	private String actualVerifiedAppointmentSetLabel;
	
	// This test verifies that the appointment from the previous test on the set appointment page
	// was set successfully by verifying the appointment approval label on the page
	@Test (priority=7)
	public void _007_ZimunTorimAppointmentApproval() throws Exception {
		
		try {
		
			initParams();
			// Step 1 - Verify the appointment was set successfully
			report.startLevel("Step 1 - Verify the appointment was set successfully");
			ZimunTorimAppointmentApprovalUpdateCancelSetAnotherPage zimunTorimAppointmentApprovalUpdateCancelSetAnotherPage = new ZimunTorimAppointmentApprovalUpdateCancelSetAnotherPage(driver);
			actualVerifiedAppointmentSetLabel=zimunTorimAppointmentApprovalUpdateCancelSetAnotherPage.getVerifyAppointmentSetLabel();
			AssertUtils.assertTrue(actualVerifiedAppointmentSetLabel.contains(expectedVerifiedAppointmentSetLabel), "Expecting to see '" + expectedVerifiedAppointmentSetLabel + "' as part of appointment approval label");
			takeScreenshot("Screen shot of appointment approval verification page");
			report.endLevel();
			
		}
		
		catch (Exception ex) {
			
			report.log("There was exception: "+ex);
			takeScreenshot("Screen shot of exception in "+Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}
	
	
	 private void initParams() throws Exception {
		
		FileInputStream input = new FileInputStream("src/main/resources/_005_ZimunTorimAppointmentApprovalTest.properties");
		Properties prop = new Properties();
		prop.load(new InputStreamReader(input, Charset.forName("UTF-8")));
		expectedVerifiedAppointmentSetLabel = prop.getProperty("expectedVerifiedAppointmentSetLabel");
		
	}
	
}

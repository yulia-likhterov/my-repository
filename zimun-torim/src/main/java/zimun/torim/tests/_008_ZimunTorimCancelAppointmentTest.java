package zimun.torim.tests;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

import org.testng.annotations.Test;

import zimun.torim.infra.pages.ZimunTorimAppointmentApprovalUpdateCancelSetAnotherPage;
import zimun.torim.infra.utils.AssertUtils;

public class _008_ZimunTorimCancelAppointmentTest extends AbstractTest {

	private String expectedAppointmentCanceledLabel;
	private String actualAppointmentCanceledLabel;
	
	@Test (priority=8)
	public void _008_ZimunTorimCancelAppointment() throws Exception {
		
		initParams();
		// Step 1 - Verify the appointment was set successfully
		report.startLevel("Step 1 - Click cancel and verify the appointment has been canceled successfully");
		ZimunTorimAppointmentApprovalUpdateCancelSetAnotherPage zimunTorimAppointmentApprovalUpdateCancelSetAnotherPage = new ZimunTorimAppointmentApprovalUpdateCancelSetAnotherPage(driver);
		zimunTorimAppointmentApprovalUpdateCancelSetAnotherPage.clickOnCancelAppointmentButton();
		report.endLevel();
		// Step 2 - Verify appointment has been cancelled successfully
		report.startLevel("Step 2 - Verify appointment has been cancelled successfully");
		actualAppointmentCanceledLabel=zimunTorimAppointmentApprovalUpdateCancelSetAnotherPage.getVerifiedAppointmentCanceledLabelText();
		AssertUtils.assertEquals(actualAppointmentCanceledLabel, expectedAppointmentCanceledLabel, "Appointment canceled label should be: " + actualAppointmentCanceledLabel);
		report.endLevel();
	}
	
	private void initParams() throws Exception {
		
		FileInputStream input = new FileInputStream("src/main/resources/_006_ZimunTorimCancelAppointmentTest.properties");
		Properties prop = new Properties();
		prop.load(new InputStreamReader(input, Charset.forName("UTF-8")));
		expectedAppointmentCanceledLabel = prop.getProperty("expectedAppointmentCanceledLabel");
		
	}
	
}

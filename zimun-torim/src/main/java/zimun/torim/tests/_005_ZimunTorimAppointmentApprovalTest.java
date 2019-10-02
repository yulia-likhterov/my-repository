package zimun.torim.tests;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

import org.testng.annotations.Test;

import zimun.torim.infra.pages.ZimunTorimAppointmentApprovalUpdateCancelSetAnotherPage;
import zimun.torim.infra.utils.AssertUtils;

public class _005_ZimunTorimAppointmentApprovalTest extends AbstractTest {
	
	private String expectedVerifiedAppointmentSetLabel;
	private String actualVerifiedAppointmentSetLabel;
	
	@Test (priority=5)
	public void ZimunTorimAppointmentApproval() throws Exception {
		
		initParams();
		// Step 1 - Verify the appointment was set successfully
		report.startLevel("Step 1 - Verify the appointment was set successfully");
		ZimunTorimAppointmentApprovalUpdateCancelSetAnotherPage zimunTorimAppointmentApprovalUpdateCancelSetAnotherPage = new ZimunTorimAppointmentApprovalUpdateCancelSetAnotherPage(driver);
		actualVerifiedAppointmentSetLabel=zimunTorimAppointmentApprovalUpdateCancelSetAnotherPage.getverifyAppointmentSetLabel();
		AssertUtils.assertTrue(actualVerifiedAppointmentSetLabel.contains(expectedVerifiedAppointmentSetLabel), "Expecting to see '" + expectedVerifiedAppointmentSetLabel + "' as part of appointment approval label");
		report.endLevel();
	
	}
	
	
	 private void initParams() throws Exception {
		
		FileInputStream input = new FileInputStream("src/main/resources/_005_ZimunTorimAppointmentApprovalTest.properties");
		Properties prop = new Properties();
		prop.load(new InputStreamReader(input, Charset.forName("UTF-8")));
		expectedVerifiedAppointmentSetLabel = prop.getProperty("expectedVerifiedAppointmentSetLabel");
		
	}
	
}

package zimun.torim.tests;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

import org.openqa.selenium.By;
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
		actualVerifiedAppointmentSetLabel=zimunTorimAppointmentApprovalUpdateCancelSetAnotherPage.getVerifyAppointmentSetLabel();
		AssertUtils.assertTrue(actualVerifiedAppointmentSetLabel.contains(expectedVerifiedAppointmentSetLabel), "Expecting to see '" + expectedVerifiedAppointmentSetLabel + "' as part of appointment approval label");
		takeScreenshot("Screen shot of appointment approval verification page");
		report.endLevel();
		
		// get text By.xpath("//div[@class='col-md-1']/span[@class='fhc-padding regularfont ng-binding']")
		// click By.id("butChangingDateAppointment") עדכון מועד תור
		// get text By.xpath("//tr[1]/td[@class='appintment-time']//li[1]")
		// click By.xpath("//tr[1]//a[text()[contains(.,'הזמנת תור')]]")
	
	}
	
	
	 private void initParams() throws Exception {
		
		FileInputStream input = new FileInputStream("src/main/resources/_005_ZimunTorimAppointmentApprovalTest.properties");
		Properties prop = new Properties();
		prop.load(new InputStreamReader(input, Charset.forName("UTF-8")));
		expectedVerifiedAppointmentSetLabel = prop.getProperty("expectedVerifiedAppointmentSetLabel");
		
	}
	
}

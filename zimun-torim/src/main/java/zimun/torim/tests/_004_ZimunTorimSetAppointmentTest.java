package zimun.torim.tests;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

import org.testng.annotations.Test;

import zimun.torim.infra.pages.ZimunTorimSetAppointmentPage;

public class _004_ZimunTorimSetAppointmentTest extends AbstractTest {
	
	private String mobilePhoneBaseNumber;
	private String mobilePhonePrefix;
	
	@Test (priority=4)
	public void ZimunTorimSetAppointment() throws Exception {
		
		initParams();
		// Step 1 - Add phone number and email in order to receive reminders for the appointment which was set
		report.startLevel("Step 1 - Add phone number and email in order to receive reminders for the appointment which was set");	
		ZimunTorimSetAppointmentPage zimunTorimSetAppointmentPage = new ZimunTorimSetAppointmentPage(driver);
		zimunTorimSetAppointmentPage.writeToMobilePhoneBaseNumber(mobilePhoneBaseNumber);
		zimunTorimSetAppointmentPage.clickOnMobilePhonePrefixSelect();
		zimunTorimSetAppointmentPage.setSelectedMobilePrifix(mobilePhonePrefix);
		zimunTorimSetAppointmentPage.clickOnSelectedMobilePrifix();
		// add code to add email
		report.endLevel();
				
		// Step 2 - Verify the appointment is set
		report.startLevel("Step 2 - Verify the appointment is set");
		zimunTorimSetAppointmentPage.clickOnIshurButton();
		report.endLevel();
		
	}
	
	private void initParams() throws Exception {
		
		FileInputStream input = new FileInputStream("src/main/resources/_004_ZimunTorimSelectAppointmentTest.properties");
		Properties prop = new Properties();
		prop.load(new InputStreamReader(input, Charset.forName("UTF-8")));

		mobilePhoneBaseNumber = prop.getProperty("mobilePhoneBaseNumber");
		mobilePhonePrefix = prop.getProperty("mobilePhonePrefix");
		
		
	}

}

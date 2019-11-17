package zimun.torim.tests;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.testng.annotations.Test;

import zimun.torim.infra.pages.ZimunTorimSetAppointmentPage;

public class _006_ZimunTorimSetAppointmentTest extends AbstractTest {
	
	private String mobilePhoneBaseNumber;
	private String mobilePhonePrefix;
	private static SimpleDateFormat dateFormat1 = new SimpleDateFormat("ddMMyyyy_HHmmss");
	private static Date testStartTime = new Date();
	private static String email="";
	private String emailAddress;
	
	// This test adds mobile number, creates a new public email box in mailinator.com site - 
	// where would be sent several appointment notifications
	// and finally sets the appointment
	// In the next test, will verify that the appointment as set successfully - notification on the next page: appointment approval
	@Test (priority=6)
	public void  _006_ZimunTorimSetAppointment() throws Exception {
		
		try {
			
			initParams();
			// Step 1 - Add phone number 
			report.startLevel("Step 1 - Add phone number and email in order to receive reminders for the appointment which was set");	
			ZimunTorimSetAppointmentPage zimunTorimSetAppointmentPage = new ZimunTorimSetAppointmentPage(driver);
			if (!zimunTorimSetAppointmentPage.hasMobilePhoneBaseNumberInputAValue()) {
				
				zimunTorimSetAppointmentPage.writeToMobilePhoneBaseNumber(mobilePhoneBaseNumber);
				zimunTorimSetAppointmentPage.clickOnMobilePhonePrefixSelect();
				zimunTorimSetAppointmentPage.setSelectedMobilePrefix(mobilePhonePrefix);
				zimunTorimSetAppointmentPage.clickOnSelectedMobilePrifix();
			}
			report.endLevel();
			
			// Step 2 - Add email address
			report.startLevel("Step 2 - Add email address");
			email = getEmailName();
			emailAddress = email + "@mailinator.com";
			if (!zimunTorimSetAppointmentPage.hasEmailAddressInputAValue()) {
				
				zimunTorimSetAppointmentPage.writeToEmailAddressInput(emailAddress);
			}
			takeScreenshot("Screen shot of filled contact details for notifications to be sent upon appointment being set");
			report.endLevel();
			
			// Step 3 - Set the appointment
			report.startLevel("Step 3 - Set the appointment");
			zimunTorimSetAppointmentPage.clickOnIshurButton();
			report.endLevel();
		}
		
		catch (Exception ex) {
			
			report.log("There was exception: "+ex);
			takeScreenshot("Screen shot of exception in "+Thread.currentThread().getStackTrace()[1].getMethodName());
		}
				
	}
	
	private void initParams() throws Exception {
		
		FileInputStream input = new FileInputStream("src/main/resources/_004_ZimunTorimSelectAppointmentTest.properties");
		Properties prop = new Properties();
		prop.load(new InputStreamReader(input, Charset.forName("UTF-8")));
		mobilePhoneBaseNumber = prop.getProperty("mobilePhoneBaseNumber");
		mobilePhonePrefix = prop.getProperty("mobilePhonePrefix");
		
	}
	
	public static String getEmailName() {
		
		if (email=="")
		{
			email = "newEmail_" + dateFormat1.format(testStartTime); 
		}
		return email;
	}

}

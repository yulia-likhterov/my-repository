package zimun.torim.tests;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.testng.annotations.Test;

import zimun.torim.infra.pages.ZimunTorimSetAppointmentPage;
import zimun.torim.infra.utils.AssertUtils;

public class _004_ZimunTorimSetAppointmentTest extends AbstractTest {
	
	private String mobilePhoneBaseNumber;
	private String mobilePhonePrefix;
	SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMdd_HHmmss");
	Date testStartTime = new Date();
	private String email;
	private String emailAddress;
	private String emailSiteUrl;
	private boolean isDismissCookiesVisible;
	
	private String actualEmailAppointmentApprovalLabel;
	private String expectedEmailAppointmentApprovalLabel;
	
	@Test (priority=4)
	public void ZimunTorimSetAppointment() throws Exception {
		
		initParams();
		// Step 1 - Add phone number 
		report.startLevel("Step 1 - Add phone number and email in order to receive reminders for the appointment which was set");	
		ZimunTorimSetAppointmentPage zimunTorimSetAppointmentPage = new ZimunTorimSetAppointmentPage(driver);
		zimunTorimSetAppointmentPage.writeToMobilePhoneBaseNumber(mobilePhoneBaseNumber);
		zimunTorimSetAppointmentPage.clickOnMobilePhonePrefixSelect();
		zimunTorimSetAppointmentPage.setSelectedMobilePrifix(mobilePhonePrefix);
		zimunTorimSetAppointmentPage.clickOnSelectedMobilePrifix();
		report.endLevel();
		
		// Step 2 - Add email address
		report.startLevel("Step 2 - Add email address");
		email = "newEmail_" + dateFormat1.format(testStartTime); 
		emailAddress = email + "@mailinator.com";
		zimunTorimSetAppointmentPage.writeToEmailAddressInput(emailAddress);
		takeScreenshot("Screen shot of filled contact details for notifications to be send upon appointment set");
		report.endLevel();
				
		// Step 3 - Set the appointment
		report.startLevel("Step 3 - Set the appointment");
		zimunTorimSetAppointmentPage.clickOnIshurButton();
		report.endLevel();
		
		// Step 4 - Verify the appointment set notification received by the newly created email from step 3
		report.startLevel("Step 4 - Verify the appointment set notification received by the newly created email from step 3");
		zimunTorimSetAppointmentPage.switchToMailinatorTab(emailSiteUrl);
		zimunTorimSetAppointmentPage.writeToEmailNameInput(email);
		takeScreenshot("Screen shot of landing page of mailinator.com with filled new inbox address");
		zimunTorimSetAppointmentPage.clickOnGoNewInboxButton();
		isDismissCookiesVisible=zimunTorimSetAppointmentPage.isDismissCookiesButtonVisible();
		if (isDismissCookiesVisible) {
		zimunTorimSetAppointmentPage.clickOnDismissCookiesButton();
		}								
		zimunTorimSetAppointmentPage.clickOnSetAppointmentSubjectLabel();
		zimunTorimSetAppointmentPage.switchToMsgBodyIframe();
		actualEmailAppointmentApprovalLabel=zimunTorimSetAppointmentPage.getEmailAppointmentApprovalLabel();
		takeScreenshot("Screen shot of appointment set notification in email");
		AssertUtils.assertTrue(actualEmailAppointmentApprovalLabel.contains(expectedEmailAppointmentApprovalLabel), "Expecting to see '" + expectedEmailAppointmentApprovalLabel + "' as part of mail set appoitment notification label");
		zimunTorimSetAppointmentPage.switchBackToZimunTorim();
		report.endLevel();
		
	}
	
	private void initParams() throws Exception {
		
		FileInputStream input = new FileInputStream("src/main/resources/_004_ZimunTorimSelectAppointmentTest.properties");
		Properties prop = new Properties();
		prop.load(new InputStreamReader(input, Charset.forName("UTF-8")));

		mobilePhoneBaseNumber = prop.getProperty("mobilePhoneBaseNumber");
		mobilePhonePrefix = prop.getProperty("mobilePhonePrefix");
		emailSiteUrl = prop.getProperty("emailSiteUrl");
		expectedEmailAppointmentApprovalLabel = prop.getProperty("expectedEmailAppointmentApprovalLabel");
		
	}

}

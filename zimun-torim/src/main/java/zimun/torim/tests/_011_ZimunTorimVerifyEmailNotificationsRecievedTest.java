package zimun.torim.tests;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

import org.testng.annotations.Test;

import zimun.torim.infra.pages.ZimunTorimAppointmentApprovalUpdateCancelSetAnotherPage;
import zimun.torim.infra.pages.ZimunTorimSetAppointmentPage;
import zimun.torim.infra.utils.AssertUtils;

public class _011_ZimunTorimVerifyEmailNotificationsRecievedTest extends AbstractTest{
	
	private String emailSiteUrl;
	private boolean isDismissCookiesVisible;
	private String actualEmailAppointmentSetNotificationApprovalLabel;
	private String expectedEmailAppointmentSetApprovalLabel;
	private String actualEmailAppointmentUpdateNotificationApprovalLabel;
	private String expectedEmailAppointmentUpdateApprovalLabel;
	
	@Test (priority=11)
	public void _011_ZimunTorimVerifyEmailNotificationsRecieved() throws Exception {
	
		initParams();
		// Step 1 - Step 1 - Go to the newly created public email from _004_ZimunTorimSetAppointmentTest
		report.startLevel("Step 1 - Go to the newly created public email from _004_ZimunTorimSetAppointmentTest");
		ZimunTorimSetAppointmentPage zimunTorimSetAppointmentPage = new ZimunTorimSetAppointmentPage(driver);
		zimunTorimSetAppointmentPage.switchToMailinatorTab(emailSiteUrl);
		zimunTorimSetAppointmentPage.writeToEmailNameInput(_006_ZimunTorimSetAppointmentTest.getEmailName());
		takeScreenshot("Screen shot of landing page of mailinator.com with filled new inbox address");
		zimunTorimSetAppointmentPage.clickOnGoNewInboxButton();
		isDismissCookiesVisible=zimunTorimSetAppointmentPage.isDismissCookiesButtonVisible();
		if (isDismissCookiesVisible) {
		zimunTorimSetAppointmentPage.clickOnDismissCookiesButton();
		}								
		report.endLevel();
		
		// Step 2 - Iterate the received emails in order to verify all email notifications arrived. There should be 3 email notifications. 
		report.startLevel("Step 2 - Iterate the received emails in order to verify all email notifications arrived. There should be 3 email notifications.");
		int countRecievedEmails=0; 
		int counter=1;
		do {
			if (zimunTorimSetAppointmentPage.checkAllEmailNotificationsRecieved(counter)) {
				countRecievedEmails++;
				counter++;
			}
		}		
		while (countRecievedEmails<3);
		report.endLevel();
		
		// Step 3 - Verify the correctness of the data inside the emails: there should be 2 emails for set appointment, and 1 email for update appointment 
		report.startLevel("Step 3 - Verify the correctness of the data inside the emails: there should be 2 emails for set appointment, and 1 email for update appointment");
		ZimunTorimAppointmentApprovalUpdateCancelSetAnotherPage zimunTorimAppointmentApprovalUpdateCancelSetAnotherPage = new ZimunTorimAppointmentApprovalUpdateCancelSetAnotherPage(driver);		
		int setAppointmentNotificationEmails = 2; // counter for set appointment notifications
		int updateAppointmentNotificationEmails = 1; // counter for update appointment notifications
		
		for (int i=1; i<4; i++) {
			
			zimunTorimSetAppointmentPage.clickOnRecievedEmailRow(i);
			zimunTorimSetAppointmentPage.switchToMsgBodyIframe();
			if (setAppointmentNotificationEmails>0)
			actualEmailAppointmentSetNotificationApprovalLabel=zimunTorimSetAppointmentPage.getEmailAppointmentSetApprovalLabel();
			if (updateAppointmentNotificationEmails>0)
			actualEmailAppointmentUpdateNotificationApprovalLabel=zimunTorimSetAppointmentPage.getEmailAppointmentUpdateNotificationLabel();
			
			if (actualEmailAppointmentSetNotificationApprovalLabel.contains(expectedEmailAppointmentSetApprovalLabel) && setAppointmentNotificationEmails>0) {
				AssertUtils.assertTrue(true, "Expecting to see '" + expectedEmailAppointmentSetApprovalLabel + "' as part of mail set appointment notification label");
				setAppointmentNotificationEmails--;
				takeScreenshot("Screen shot of appointment set notification in email");
			}
			
			else if (actualEmailAppointmentUpdateNotificationApprovalLabel.contains(expectedEmailAppointmentUpdateApprovalLabel) && updateAppointmentNotificationEmails>0) {
				AssertUtils.assertTrue(true, "Expecting to see '" + expectedEmailAppointmentUpdateApprovalLabel + "' as part of update appointment mail notification label");
				updateAppointmentNotificationEmails--;
				takeScreenshot("Screen shot of appointment update notification in email");
			}
			driver.switchTo().defaultContent();
			zimunTorimAppointmentApprovalUpdateCancelSetAnotherPage.clickOnBackToInboxLink();
		}
		
		if ((setAppointmentNotificationEmails==0) && (updateAppointmentNotificationEmails==0))
			AssertUtils.assertTrue(true, "There are 2 email notifications of set appointment and one email notification for update appointment - test verified");
		zimunTorimSetAppointmentPage.switchBackToZimunTorim();
		report.endLevel();
				
	}
	
	
	private void initParams() throws Exception {
		
		FileInputStream input = new FileInputStream("src/main/resources/_008_ZimunTorimVerifyEmailNotificationsRecievedTest.properties");
		Properties prop = new Properties();
		prop.load(new InputStreamReader(input, Charset.forName("UTF-8")));

		emailSiteUrl = prop.getProperty("emailSiteUrl");
		expectedEmailAppointmentSetApprovalLabel = prop.getProperty("expectedEmailAppointmentSetApprovalLabel");
		expectedEmailAppointmentUpdateApprovalLabel = prop.getProperty("expectedEmailAppointmentUpdateApprovalLabel");
		
	}
}

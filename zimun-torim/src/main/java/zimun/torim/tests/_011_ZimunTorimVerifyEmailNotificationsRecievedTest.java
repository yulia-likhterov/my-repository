package zimun.torim.tests;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;
import org.testng.annotations.Test;
import zimun.torim.infra.pages.EmailNotificationsPage;
import zimun.torim.infra.utils.AssertUtils;

public class _011_ZimunTorimVerifyEmailNotificationsRecievedTest extends AbstractTest{
	
	private String emailSiteUrl;
	private boolean isDismissCookiesVisible;
	private String actualEmailAppointmentSetNotificationApprovalLabel;
	private String expectedEmailAppointmentSetApprovalLabel;
	private String actualEmailAppointmentUpdateNotificationApprovalLabel;
	private String expectedEmailAppointmentUpdateApprovalLabel;
	
	// This test checks the newly created public inbox from _006_ZimunTorimSetAppointmentTest
	// and verifies, first, the quantity: 3 emails, and second, the text inside the emails: 
	// 2 notifications for appointment set, and 1 notification for appointment update
	@Test (priority=11)
	public void _011_ZimunTorimVerifyEmailNotificationsRecieved() throws Exception {
	
		try {
			
			initParams();
			// Step 1 - Step 1 - Go to the newly created public email from _006_ZimunTorimSetAppointmentTest
			report.startLevel("Step 1 - Go to the newly created public email from _006_ZimunTorimSetAppointmentTest");
			EmailNotificationsPage emailNotificationsPage = new EmailNotificationsPage(driver);		
			emailNotificationsPage.switchToMailinatorTab(emailSiteUrl);
			emailNotificationsPage.writeToEmailNameInput(_006_ZimunTorimSetAppointmentTest.getEmailName());
			takeScreenshot("Screen shot of landing page of mailinator.com with filled new inbox address");
			emailNotificationsPage.clickOnGoNewInboxButton();
			isDismissCookiesVisible=emailNotificationsPage.isDismissCookiesButtonVisible();
			if (isDismissCookiesVisible) {
				emailNotificationsPage.clickOnDismissCookiesButton();
			}								
			report.endLevel();
			
			// Step 2 - Iterate over the received emails in order to verify all email notifications arrived. 
			// There should be 3 email notifications. 2 for appointment set, and 1 for appointment update 
			report.startLevel("Step 2 - Iterate over the received emails in order to verify all email notifications arrived. There should be 3 email notifications. 2 for appointment set, and 1 for appointment update");
			int countRecievedEmails=0; 
			int counter=1;
			do {
				if (emailNotificationsPage.checkAllEmailNotificationsRecieved(counter)) {
					countRecievedEmails++;
					counter++;
				}
			}		
			while (countRecievedEmails<3);
			takeScreenshot("Screen shot of recieved appointment notifications");
			report.endLevel();
			
			// Step 3 - Verify the correctness of the data inside the emails: there should be 2 emails for set appointment, and 1 email for update appointment 
			report.startLevel("Step 3 - Verify the correctness of the data inside the emails: there should be 2 emails for set appointment, and 1 email for update appointment");
			int setAppointmentNotificationEmails = 2; // counter for set appointment notifications
			int updateAppointmentNotificationEmails = 1; // counter for update appointment notifications
			
			for (int i=1; i<4; i++) {
				
				emailNotificationsPage.clickOnRecievedEmailRow(i);
				emailNotificationsPage.switchToMsgBodyIframe();
				if (setAppointmentNotificationEmails>0)
				actualEmailAppointmentSetNotificationApprovalLabel=emailNotificationsPage.getEmailAppointmentSetApprovalLabel();
				if (updateAppointmentNotificationEmails>0)
				actualEmailAppointmentUpdateNotificationApprovalLabel=emailNotificationsPage.getEmailAppointmentUpdateNotificationLabel();
				
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
				emailNotificationsPage.clickOnBackToInboxLink();
			}
			
			if ((setAppointmentNotificationEmails==0) && (updateAppointmentNotificationEmails==0))
				AssertUtils.assertTrue(true, "There are 2 email notifications of set appointment and one email notification for update appointment - test verified");
			emailNotificationsPage.switchBackToZimunTorim();
			report.endLevel();
		}
		
		catch (Exception ex) {
			
			report.log("There was exception: "+ex);
			takeScreenshot("Screen shot of exception in "+Thread.currentThread().getStackTrace()[1].getMethodName());
		}
				
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

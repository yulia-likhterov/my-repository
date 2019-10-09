package zimun.torim.tests;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

import org.testng.annotations.Test;

import zimun.torim.infra.pages.ZimunTorimAppointmentApprovalUpdateCancelSetAnotherPage;
import zimun.torim.infra.pages.ZimunTorimSetAppointmentPage;
import zimun.torim.infra.utils.AssertUtils;

public class _008_ZimunTorimVerifyEmailNotificationsRecievedTest extends AbstractTest{
	
	private String emailSiteUrl;
	private boolean isDismissCookiesVisible;
	private String actualEmailAppointmentSetApprovalLabel;
	private String expectedEmailAppointmentSetApprovalLabel;
	private String actualEmailAppointmentUpdateApprovalLabel;
	private String expectedEmailAppointmentUpdateApprovalLabel;
	
	@Test
	public void _008_ZimunTorimVerifyEmailNotificationsRecieved() throws Exception {
	
		initParams();
		// Step 1 - Verify the appointment set notification received by the newly created email from _004_ZimunTorimSetAppointmentTest
		report.startLevel("Step 1 - Verify the appointment set notification received by the newly created email from _004_ZimunTorimSetAppointmentTest");
		ZimunTorimSetAppointmentPage zimunTorimSetAppointmentPage = new ZimunTorimSetAppointmentPage(driver);
		zimunTorimSetAppointmentPage.switchToMailinatorTab(emailSiteUrl);
		zimunTorimSetAppointmentPage.writeToEmailNameInput(_004_ZimunTorimSetAppointmentTest.getEmailName());
		takeScreenshot("Screen shot of landing page of mailinator.com with filled new inbox address");
		zimunTorimSetAppointmentPage.clickOnGoNewInboxButton();
		isDismissCookiesVisible=zimunTorimSetAppointmentPage.isDismissCookiesButtonVisible();
		if (isDismissCookiesVisible) {
		zimunTorimSetAppointmentPage.clickOnDismissCookiesButton();
		}								
			
		if (zimunTorimSetAppointmentPage.clickOnSetAppointmentSubjectLabel()) {
			zimunTorimSetAppointmentPage.switchToMsgBodyIframe();
			actualEmailAppointmentSetApprovalLabel=zimunTorimSetAppointmentPage.getEmailAppointmentApprovalLabel();
			takeScreenshot("Screen shot of appointment set notification in email");
			AssertUtils.assertTrue(actualEmailAppointmentSetApprovalLabel.contains(expectedEmailAppointmentSetApprovalLabel), "Expecting to see '" + expectedEmailAppointmentSetApprovalLabel + "' as part of mail set appoitment notification label");
		}
		report.endLevel();
		
		// Step 2 - Verify the appointment update notification is received
		report.startLevel("Step 2 - Verify the appointment update notification is received");
		ZimunTorimAppointmentApprovalUpdateCancelSetAnotherPage zimunTorimAppointmentApprovalUpdateCancelSetAnotherPage = new ZimunTorimAppointmentApprovalUpdateCancelSetAnotherPage(driver);		
		driver.switchTo().defaultContent();
		zimunTorimAppointmentApprovalUpdateCancelSetAnotherPage.clickOnBackToInboxLink();
		if (zimunTorimSetAppointmentPage.clickOnUpdatedAppointmentSubjectLabel()) {
			zimunTorimSetAppointmentPage.switchToMsgBodyIframe();
			actualEmailAppointmentUpdateApprovalLabel = zimunTorimSetAppointmentPage.getEmailAppointmentUpdateLabel();
			takeScreenshot("Screen shot of appointment update notification in email");
			AssertUtils.assertTrue(actualEmailAppointmentUpdateApprovalLabel.contains(expectedEmailAppointmentUpdateApprovalLabel), "Expecting to see '" + expectedEmailAppointmentUpdateApprovalLabel + "' as part of update appointment mail notification label");
			}
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

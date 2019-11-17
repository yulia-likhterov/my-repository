package zimun.torim.infra.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import zimun.torim.infra.web.By2;

public class EmailNotificationsPage extends AbstractPage {
	
	private static final By2 backToInboxLink = new By2("'backToInbox' link", By.xpath("//a[text()[contains(.,'Back To Inbox')]]"));
	private static final By2 dismissCookiesButton = new By2("'dismissCookies' button", By.cssSelector("a[aria-label='dismiss cookie message']"));
	private static final By2 goNewInboxButton = new By2("'goNewInbox' button", By.id("go_inbox1"));
	private static final By2 emailNameInput = new By2("'emailName' input", By.id("inboxfield"));
	private By2 recievedEmailRowLabel;
	private static final By2 getEmailAppointmentSetNotificationLabel = new By2("'getAppointmentSetApproval' label", By.xpath("//b[text()[contains(.,'להלן אישור התור')]]"));
	private static final By2 getEmailAppointmentUpdateNotificationLabel = new By2("'getEmailAppointmentUpdateApproval' label", By.xpath("//b[text()[contains(.,'עודכן מועד התור')]]"));
	
	
	public EmailNotificationsPage(WebDriver driver) {
	
		super(driver);

	}
	
	public void clickOnBackToInboxLink() throws Exception {
		
		bot.click(backToInboxLink);
	}
	
	public void clickOnGoNewInboxButton () throws Exception {
		
		bot.click(goNewInboxButton);
	}
	
	public void clickOnDismissCookiesButton() throws Exception {
		
		bot.click(dismissCookiesButton);
	}
	
	
	public boolean isDismissCookiesButtonVisible () {
		
		return bot.isElementVisible(dismissCookiesButton);
	}
	
	public void writeToEmailNameInput (String emailName) throws Exception {
		
		bot.writeToElement (emailNameInput, emailName);
	}
	
	public void switchToMsgBodyIframe() {
		
		bot.switchToIframeById("msg_body");
	}
	
	public void switchToMailinatorTab (String url) {
		
		bot.switchToNewTab(url);
	}
	
	public void switchBackToZimunTorim() {
		
		bot.switchBackToMainTab();
	}
	
	public boolean checkAllEmailNotificationsRecieved(int i) {
		
		recievedEmailRowLabel = new By2("'recievedEmailRow' label", By.xpath("(//td[text()[contains(.,'riut')]])["+ i +"]"));
		return bot.isElementVisible(recievedEmailRowLabel);
	}
	
	public void clickOnRecievedEmailRow(int i) throws Exception {
		
		recievedEmailRowLabel = new By2("'recievedEmailRow' label", By.xpath("(//td[text()[contains(.,'riut')]])["+ i +"]"));
		bot.click(recievedEmailRowLabel);
	}
	
	
	public String getEmailAppointmentSetApprovalLabel () throws Exception {
		
		return bot.getElementText(getEmailAppointmentSetNotificationLabel);
	}
	
	
	public String getEmailAppointmentUpdateNotificationLabel () throws Exception {
		
		return bot.getElementText(getEmailAppointmentUpdateNotificationLabel);
	}
	
}

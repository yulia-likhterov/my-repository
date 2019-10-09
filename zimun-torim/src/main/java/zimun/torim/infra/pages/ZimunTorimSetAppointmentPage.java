package zimun.torim.infra.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import zimun.torim.infra.web.By2;

public class ZimunTorimSetAppointmentPage extends AbstractPage {

	
	private static final By2 selectedAppointmentDetailsForm = new By2("'selectedAppointmentDetails' form", By.id("formCreateAppointment"));
	private static final By2 mobilePhoneBaseNumberInput = new By2("'mobilePhoneBaseNumber' input", By.cssSelector("input[name='BaseNumber']"));
	private static final By2 mobilePhonePrefixSelect = new By2("'mobilePhonePrefix' select", By.cssSelector("a[class='sbSelector'"));
	private By2 selectedMobilePrifix;
	private static final By2 dismissCookiesButton = new By2("'dismissCookies' button", By.cssSelector("a[aria-label='dismiss cookie message']"));
	
	private static final By2 emailAddressInput = new By2("'emailAddress' input", By.cssSelector("input[name='emailAddress']"));
	private static final By2 emailNameInput = new By2("'emailName' input", By.id("inboxfield"));
	private static final By2 goNewInboxButton = new By2("'goNewInbox' button", By.id("go_inbox1"));
	private static final By2 setAppointmentSubjectLabel = new By2("'setAppointmentSubject' label", By.xpath("//td/a[text()[contains(.,'אישור קביעת תור')]]"));
	private static final By2 updatedAppointmentSubjectLabel = new By2("'updatedAppointmentSubject' label", By.xpath("//td/a[text()[contains(.,'עדכון מועד תור')]]"));
	
	private static final By2 getEmailAppointmentApprovalLabel = new By2("'getAppointmentApproval' label", By.xpath("//b[text()[contains(.,'להלן אישור התור לטיפות חלב')]]"));
	private static final By2 getEmailAppointmentUpdateApprovalLabel = new By2("'getEmailAppointmentUpdateApproval' label", By.xpath("//b[text()[contains(.,'עודכן מועד התור לטיפת חלב')]]"));
	
	private static final By2 ishurButton = new By2("'ishurButton' button", By.id("gobut"));
	private static final By2 appointmentSetTimeLabel = new By2("'appointmentSetTime' label", By.xpath("//div[@class='col-md-1']/span[@class='fhc-padding regularfont ng-binding']"));
	
	
	public ZimunTorimSetAppointmentPage (WebDriver driver) {
		
		super(driver);
	}
	
	
	public boolean isSelectedAppointmentDetailsFormDisplayed () {
		
		return bot.isElementVisible(selectedAppointmentDetailsForm);
	}
	
	public void writeToMobilePhoneBaseNumber (String baseNumber) throws Exception {
		
		bot.writeToElement(mobilePhoneBaseNumberInput, baseNumber);
	}
	
	public void writeToEmailAddressInput (String email) throws Exception {
		
		bot.writeToElement(emailAddressInput, email);
	}
	
	public void setSelectedMobilePrifix (String mobilePrefix) {
		
		selectedMobilePrifix = new By2("'selectedStation' label", By.cssSelector("a[href='#" + mobilePrefix + "']"));
		
	}
	
	public void clickOnMobilePhonePrefixSelect() throws Exception {
		
		bot.click(mobilePhonePrefixSelect);
	}
	
	public void clickOnSelectedMobilePrifix () throws Exception {
		
		bot.click(selectedMobilePrifix);
	}
	
	public void clickOnGoNewInboxButton () throws Exception {
		
		bot.click(goNewInboxButton);
	}
	
	
	public boolean clickOnSetAppointmentSubjectLabel() throws Exception {
		
		return bot.clickWithReturn(setAppointmentSubjectLabel);
		
	}	
	
	public boolean clickOnUpdatedAppointmentSubjectLabel() throws Exception {
		
		return bot.clickWithReturn(updatedAppointmentSubjectLabel);
		
	}	
	
	
	public void clickOnIshurButton() throws Exception {
		
		bot.click(ishurButton);
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
	
	public String getEmailAppointmentApprovalLabel () throws Exception {
		
		return bot.getElementText(getEmailAppointmentApprovalLabel);
	}
	
	
	public String getEmailAppointmentUpdateLabel () throws Exception {
		
		return bot.getElementText(getEmailAppointmentUpdateApprovalLabel);
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
	
	public String getAppointmentSetTimeLabel () throws Exception {
		
		return bot.getElementText(appointmentSetTimeLabel);
	}
	
}

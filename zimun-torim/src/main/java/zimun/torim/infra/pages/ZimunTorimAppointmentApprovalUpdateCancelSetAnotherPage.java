package zimun.torim.infra.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import zimun.torim.infra.web.By2;

public class ZimunTorimAppointmentApprovalUpdateCancelSetAnotherPage extends AbstractPage {
	
	private static final By2 verifyAppointmentSetLabel = new By2("'verifyAppointmentSet' label", By.xpath("//div[text()[contains(.,'התור נקבע בהצלחה')]]"));
	private static final By2 cancelAppointmentButton = new By2("'cancelAppointment' button", By.id("butCancelAppointment"));
	private static final By2 verifiedAppointmentCanceledLabel = new By2("'verifiedAppointmentCanceled' label", By.id("ngdialog1-aria-labelledby"));
	private static final By2 updateAppointmentButton = new By2("'updateAppointment' button", By.id("butChangingDateAppointment"));
	private static final By2 appointmentSetTimeLabel = new By2("'appointmentSetTime' label", By.xpath("//div[@class='col-md-1']/span[@class='fhc-padding regularfont ng-binding']"));
	private static final By2 backToInboxLink = new By2("'backToInbox' link", By.xpath("//a[text()[contains(.,'Back To Inbox')]]"));
	private static final By2 setAdditionalAppointmentLink = new By2("'setAdditionalAppointment' link", By.xpath("//a[text()[contains(.,'לזימון תור נוסף')]]"));
	
	
	public ZimunTorimAppointmentApprovalUpdateCancelSetAnotherPage (WebDriver driver) {
		
		super(driver);
	}
	
	public String getVerifyAppointmentSetLabel() throws Exception {
		
		return bot.getElementText(verifyAppointmentSetLabel);
	}		
	
	public void clickOnCancelAppointmentButton () throws Exception {
		
		bot.click(cancelAppointmentButton);
	}
	
	public void clickOnSetAdditionalAppointmentLink() throws Exception {
		
		bot.click(setAdditionalAppointmentLink);
	}
	
	public String getVerifiedAppointmentCanceledLabelText () throws Exception {
		
		return bot.getElementText(verifiedAppointmentCanceledLabel);
	}
	
	public void clickOnUpdateButton() throws Exception {
		
		bot.click(updateAppointmentButton);
	}
	
	public void clickOnBackToInboxLink() throws Exception {
		
		bot.click(backToInboxLink);
	}
	
	public String getAppointmentSetTimeLabel () throws Exception {
		
		return bot.getElementText(appointmentSetTimeLabel);
	}
	
}

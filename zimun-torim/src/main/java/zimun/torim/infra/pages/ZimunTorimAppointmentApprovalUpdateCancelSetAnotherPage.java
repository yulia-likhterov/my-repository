package zimun.torim.infra.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import zimun.torim.infra.web.By2;

public class ZimunTorimAppointmentApprovalUpdateCancelSetAnotherPage extends AbstractPage {
	
	private static final By2 verifyAppointmentSetLabel = new By2("'verifyAppointmentSet' label", By.xpath("//div[text()[contains(.,'התור נקבע בהצלחה')]]"));
	private static final By2 cancelAppointmentButton = new By2("'cancelAppointment' button", By.id("butCancelAppointment"));
	private static final By2 verifiedAppointmentCanceledLabel = new By2("'verifiedAppointmentCanceled' label", By.id("ngdialog1-aria-labelledby"));

	public ZimunTorimAppointmentApprovalUpdateCancelSetAnotherPage (WebDriver driver) {
		
		super(driver);
	}
	
	public String getverifyAppointmentSetLabel() throws Exception {
		
		return bot.getElementText(verifyAppointmentSetLabel);
	}		
	
	public void clickOnCancelAppointmentButton () throws Exception {
		
		bot.click(cancelAppointmentButton);
	}
	
	public String getVerifiedAppointmentCanceledLabelText () throws Exception {
		
		return bot.getElementText(verifiedAppointmentCanceledLabel);
	}
	
}

package zimun.torim.infra.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import zimun.torim.infra.web.By2;

public class ZimunTorimSetAppointmentPage extends AbstractPage {

	
	private static final By2 selectedAppointmentDetailsForm = new By2("'selectedAppointmentDetails' form", By.id("formCreateAppointment"));
	private static final By2 mobilePhoneBaseNumberInput = new By2("'mobilePhoneBaseNumber' input", By.cssSelector("input[name='BaseNumber']"));
	private static final By2 mobilePhonePrefixSelect = new By2("'mobilePhonePrefix' select", By.cssSelector("a[class='sbSelector'"));
	private By2 selectedMobilePrifix;
	private static final By2 ishurButton = new By2("'ishurButton' button", By.id("gobut"));
	
	public ZimunTorimSetAppointmentPage (WebDriver driver) {
		
		super(driver);
	}
	
	
	public boolean isSelectedAppointmentDetailsFormDisplayed () {
		
		return bot.isElementVisible(selectedAppointmentDetailsForm);
	}
	
	public void writeToMobilePhoneBaseNumber (String baseNumber) throws Exception {
		
		bot.writeToElement(mobilePhoneBaseNumberInput, baseNumber);
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
	
	public void clickOnIshurButton() throws Exception {
		
		bot.click(ishurButton);
	}

	
}

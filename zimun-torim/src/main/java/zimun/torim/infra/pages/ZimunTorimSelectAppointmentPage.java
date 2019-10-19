package zimun.torim.infra.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import zimun.torim.infra.web.By2;

public class ZimunTorimSelectAppointmentPage extends AbstractPage {
	
	private static final By2 selectedAppointmentInput = new By2("'selectedAppointment' table row", By.xpath("//tr[1]//a[text()[contains(.,'הזמנת תור')]]"));
	private static final By2 appointmentUpdateTimeLabel = new By2("'appointmentUpdateTime' label", By.xpath("//tr[1]/td[@class='appintment-time']//li[1]"));
	private static final By2 availableAppointmentSlotsLoader = new By2("'availableAppointmentSlots' loader", By.id("circularG"));
	
	
	public ZimunTorimSelectAppointmentPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
	}


	public void clickOnSelectedAppointment() throws InterruptedException {
		
		bot.click(selectedAppointmentInput);
	}
	
	public String getAppointmentUpdateTimeLabel () throws Exception {
		
		bot.waitForElementNotDisplayed(availableAppointmentSlotsLoader);
		return bot.getElementText(appointmentUpdateTimeLabel);
	}

}

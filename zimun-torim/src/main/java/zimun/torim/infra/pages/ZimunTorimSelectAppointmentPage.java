package zimun.torim.infra.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import zimun.torim.infra.web.By2;

public class ZimunTorimSelectAppointmentPage extends AbstractPage {
	
	private static final By2 selectedAppointmentInput = new By2("'selectedAppointment' table row", By.xpath("//tr[1]//a[text()[contains(.,'הזמנת תור')]]"));
	private static final By2 appointmentUpdateTimeLabel = new By2("'appointmentUpdateTime' label", By.xpath("//tr[1]/td[@class='appintment-time']//li[1]"));
	private static final By2 availableAppointmentSlotsLoader = new By2("'availableAppointmentSlots' loader", By.id("circularG"));
	
	public ZimunTorimSelectAppointmentPage(WebDriver driver) {
		
		super(driver);
	}

	public void clickOnSelectedAppointment() throws InterruptedException {
		
		bot.click(selectedAppointmentInput);
	}
	
	public String getAppointmentUpdateTimeLabel () throws Exception {
		
		bot.waitForElementNotDisplayed(availableAppointmentSlotsLoader);
		return bot.getElementText(appointmentUpdateTimeLabel);
	}
	
	public List<String> getReccomendationForNextAppointmentPerPatient() throws Exception {
		
		String reccomendation;
		List<String> reccomendations = new ArrayList<String>(); 
		
		for (int i=1; i<3; i++) {
			
			By2 appointmentType = new By2("'appointmentType' label", By.xpath("(//ul[@class[contains(.,'appointment-type-list')]])[1]/li[@class='appointment-type-li ng-binding ng-scope']["+i+"]"));
			reccomendation = bot.getElementText(appointmentType);
			if (reccomendation!="") {
				reccomendations.add(reccomendation);
			}
		}
		
		return reccomendations;
	}

}

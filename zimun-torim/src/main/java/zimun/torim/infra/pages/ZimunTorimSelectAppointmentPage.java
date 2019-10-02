package zimun.torim.infra.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import zimun.torim.infra.web.By2;

public class ZimunTorimSelectAppointmentPage extends AbstractPage {
	
	private static final By2 selectedAppointmentInput = new By2("'selectedAppointment' table row", By.xpath("//tr[1]//a[text()[contains(.,'הזמנת תור')]]"));
	

	public ZimunTorimSelectAppointmentPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
	}


	public void clickOnSelectedAppointment() throws InterruptedException {
		
		bot.click(selectedAppointmentInput);
	}
}

package zimun.torim.infra.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import zimun.torim.infra.web.By2;

public class ZimunTorimAvailableSlotsPerStationPage extends AbstractPage {
	
	private static final By2 availableSlotsPerStationPageLabel = new By2("'availableSlotsPerStationPage' label", By.xpath("//h3[text()[contains(.,'קביעת תור- טיפת חלב')]]"));
	
	public ZimunTorimAvailableSlotsPerStationPage(WebDriver driver) {
		
		super(driver);
	}
	
	public String getAvailableSlotsPerStationPageLabel () throws Exception {
		
		return bot.getElementText(availableSlotsPerStationPageLabel);
	}
	
}

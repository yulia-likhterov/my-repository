package zimun.torim.infra.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import zimun.torim.infra.web.By2;

public class ZimunTorimStationLocatingPage extends AbstractPage {
	
	private static final By2 stationLocatingPageLabel = new By2("'stationLocatingPage' label", By.xpath("//h3[text()[contains(.,'איתור טיפת חלב')]]"));

	public ZimunTorimStationLocatingPage(WebDriver driver) {
		
		super(driver);
	}
	
	public String getStationLocatingPageLabel() {
		
		return bot.getElementText(stationLocatingPageLabel);
	}

}


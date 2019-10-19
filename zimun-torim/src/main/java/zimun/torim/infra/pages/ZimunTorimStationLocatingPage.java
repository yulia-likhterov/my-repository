package zimun.torim.infra.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import zimun.torim.infra.web.By2;

public class ZimunTorimStationLocatingPage extends AbstractPage {
	
	private static final By2 stationLocatingPageLabel = new By2("'stationLocatingPage' label", By.xpath("//h3[text()[contains(.,'איתור טיפת חלב')]]"));
	private static final By2 enterCityInput = new By2("'enterCity' input", By.cssSelector("input[tabindex='1']"));
	private static final By2 searchCityButton = new By2("'searchCity' button", By.cssSelector("button[ng-click='searchStations($event,0)']"));
	private By2 selectedStationLabel;
	
	
	//input[@aria-owns='ui-select-choices-2']
	
	public void setSelectedStation (String station) {
		
		selectedStationLabel = new By2("'selectedStation' label", By.xpath("//*[text()[contains(.,'"+ station +"')]]"));
		
	}
	
	public ZimunTorimStationLocatingPage (WebDriver driver) {
		
		super(driver);
	}
	
	
	
	
	
	public void clickOnCityInput() throws InterruptedException {
		
		bot.clickWithEnter(enterCityInput);
	}
	
	public void writeToEnterCity(String city) throws InterruptedException {
		
		bot.writeToElement(enterCityInput, city);
	}
	
	public void clickOnSearchCity() throws InterruptedException {
		
		bot.click(searchCityButton);
	}
	
	public void clickOnselectedStation() throws InterruptedException {
		
		bot.click(selectedStationLabel);
	}
	
	public String getStationLocatingPageLabel () throws Exception {
		
		return bot.getElementText(stationLocatingPageLabel);
		
	}
	
	
}


package zimun.torim.infra.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import zimun.torim.infra.web.By2;

public class ZimunTorimPersonalDetailsPage extends AbstractPage {

	private static final By2 personalDetailsPageMainLabel = new By2("'personalDetailsPageMain' label", By.xpath("//h1[text()[contains(.,'איתור טיפות חלב וזימון תורים')]]"));
	private static final By2 personalDetailsPageSecondaryLabel = new By2("'personalDetailsPageSecondary' label", By.xpath("//div[text()[contains(.,'פרטים אישיים')]]"));
	
	private static final By2 idInput = new By2("'Id' input", By.id("personalId"));
	private static final By2 dobDayInput = new By2("'dobDate' input", By.cssSelector("input[type='text'][tabindex='2']"));
	private static final By2 dobMonthInput = new By2("'dobMonth' input", By.cssSelector("input[type='text'][tabindex='3']"));
	private static final By2 dobYearInput = new By2("'dobYear' input", By.cssSelector("input[type='text'][tabindex='4']"));
	private static final By2 selectServiceDropDown = new By2("'selectService' dropdown", By.cssSelector("div[class='ui-select-match']"));
	private static final By2 selectAServiceFromDropDown = new By2("'selectAServiceFrom' dropdown", By.cssSelector("span[class='ui-select-placeholder text-muted ng-binding']"));
	private static final By2 selectedServiceFromDropDown = new By2("'selectedServiceFrom' dropdown", By.id("ui-select-choices-row-0-0"));
	private static final By2 nextPageButton = new By2("'nextPage' button", By.id("gobut"));
	
	public ZimunTorimPersonalDetailsPage(WebDriver driver) {
		super(driver);	
	}
	
	public ZimunTorimPersonalDetailsPage() {
		
	}
	
	public String getPersonalDetailsPageMainLabel() throws Exception {
		
		return bot.getElementText(personalDetailsPageMainLabel);
	}		
	
	public String getPersonalDetailsPageSecondaryLabel() throws Exception {
		
		return bot.getElementText(personalDetailsPageSecondaryLabel);
	}
	
	public void writeId(String id) throws InterruptedException {
		
		bot.writeToElement(idInput, id);
	}

	public void writeDOB(String dobDay, String dobMonth, String dobYear) throws InterruptedException {
	
		bot.writeToElementWithEnter(dobDayInput, dobDay);
		bot.writeToElementWithEnter(dobMonthInput, dobMonth);
		bot.writeToElementWithTwoEnters(dobYearInput, dobYear);
		
	}

	public void clickOnSelectedService() throws InterruptedException {
	
			bot.click(selectServiceDropDown);
			bot.click(selectAServiceFromDropDown);
			bot.click(selectedServiceFromDropDown);
	}
	
	public void clickNextPageButton() throws InterruptedException {
		
		bot.click(nextPageButton);
	}

}

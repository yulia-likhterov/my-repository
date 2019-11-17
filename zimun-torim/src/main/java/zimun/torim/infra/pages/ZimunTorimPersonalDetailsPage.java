package zimun.torim.infra.pages;

import java.util.ArrayList;
import java.util.List;

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
	private static final By2 getSelectedServiceLabel = new By2("'getSelectedService' label", By.cssSelector("span[ng-bind='$select.selected.VisitTypeDesc'"));
	private static final By2 nextPageButton = new By2("'nextPage' button", By.id("gobut"));
	private static final By2 firstNameInput = new By2("'firstName' input", By.xpath("//input[@name='FirstName']"));
	private static final By2 lastNameInput = new By2("'lastName' input", By.xpath("//input[@name='LastName']"));
	private static final By2 searchCityInputForNewCustomer = new By2("'searchCity' select", By.xpath("//div[@name='SelectedCity']"));
	private static final By2 focusOnStreetLabelForNewCustomer = new By2("'searchCity' label", By.xpath("//label[text()[contains(.,'רחוב')]]"));
	private static final By2 enterCityInputForNewCustomer = new By2("'enterCity' input", By.xpath("//input[@aria-owns='ui-select-choices-2']"));
	private static final By2 searchStreetInputForNewCustomer = new By2("'searchStreet' select", By.xpath("//div[@name='SelectedStreet']/div[@placeholder='בחר']"));
	private static final By2 enterStreetInputForNewCustomer = new By2("'enterStreet' input", By.xpath("//input[@aria-owns='ui-select-choices-3']"));
	
	public ZimunTorimPersonalDetailsPage(WebDriver driver) {
		super(driver);	
	}
	
	public ZimunTorimPersonalDetailsPage() {
		
	}
	
	public void writeToFirstNameInput (String firstName) throws Exception {
		
		bot.writeToElement(firstNameInput, firstName);
	}
	
	public void writeToLastNameInput (String lastName) throws Exception {
		
		bot.writeToElement(lastNameInput, lastName);
	}
	
	public void clickOnSearchCityInputForNewCustomer() throws Exception {
		
		bot.click(searchCityInputForNewCustomer);
	}
	
	public void writeToSearchCityInputForNewCustomer(String city) throws Exception {
		
		bot.writeToElementWithEnter(enterCityInputForNewCustomer, city);
	}
	
	public void clickOnSearchStreetInputForNewCustomer() throws Exception {
		
		bot.click(focusOnStreetLabelForNewCustomer);
		bot.click(searchStreetInputForNewCustomer);
	}
	
	public void writeToSearchStreetInputForNewCustomer(String street) throws Exception {
		
		bot.writeToElementWithEnter(enterStreetInputForNewCustomer, street);
	}
	
	public String getSelectedServiceLabel() throws Exception {
		
		return bot.getElementText(getSelectedServiceLabel);
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

	public void clickToOpenTheServiceSelect() throws Exception {
		
		bot.click(selectServiceDropDown);
		bot.click(selectAServiceFromDropDown);
	}
	
	
	public String getSelectedServiceFromDropDownText() throws Exception {
		
		return bot.getElementText(selectedServiceFromDropDown);
	}
	
	public List<String> getAvailableServiceOptions() throws Exception {
		
		String service;
		List<String> services = new ArrayList<String>(); 
		
		for (int i=0; i<2; i++) {
			
			By2 optionalService = new By2("'optionalService' label", By.id("ui-select-choices-row-0-"+i));
			service = bot.getElementText(optionalService);
			if (service!="") {
				services.add(service);
			}
		}
		
		return services;
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

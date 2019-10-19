package zimun.torim.tests;

//import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.List;
import java.util.Properties;

import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
//import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
//import org.testng.xml.XmlSuite;

import zimun.torim.infra.config.MainConfig;
import zimun.torim.infra.pages.ZimunTorimPersonalDetailsPage;
import zimun.torim.infra.pages.ZimunTorimStationLocatingPage;
import zimun.torim.infra.utils.AssertUtils;
//import zimun.torim.infra.web.Patient;

public class _002_ZimunTorimVerifyPersonalDetailsTest extends AbstractTest {

	ZimunTorimPersonalDetailsPage zimunTorimPersonalDetailsPage = new ZimunTorimPersonalDetailsPage();
	
	private String id;
	private String dobDay;
	private String dobMonth;
	private String dobYear;
	private String expectedPersonalDetailsMainLabel;
	private String expectedPersonalDetailsSecondaryLabel;
	private String stationLocatingPageLabel;
	private String suiteName;
	private String firstName;
	private String lastName;
	private String city;
	private String street;
	
	@BeforeTest 
	public void getCurrentSuite (ITestContext ctx) {
	  suiteName = ctx.getCurrentXmlTest().getSuite().getName();
	}
		 
	
	@Test (priority=2)
	public void _002_ZimunTorimVerifyPersonalDetails() throws Exception {
		
		//ZimunTorimPersonalDetailsPage zimunTorimPersonalDetailsPage = new ZimunTorimPersonalDetailsPage(driver);
		browseToUrl(MainConfig.baseUrl);
		
		if (suiteName.equalsIgnoreCase("Suite1")) {
		
			initParams();
		}
		
		else if (suiteName.equalsIgnoreCase("Suite2")) {
			
			initParamsForNewCustomer();
			fillNewCustomerDetails();
			
		}
		
		report.startLevel("Step 1 - Verify that you are on personal details page");
		//browseToUrl(MainConfig.baseUrl);
		//ZimunTorimPersonalDetailsPage zimunTorimPersonalDetailsPage = new ZimunTorimPersonalDetailsPage(driver);
		String mainPersonalDetailsPageLabel=zimunTorimPersonalDetailsPage.getPersonalDetailsPageMainLabel();
		AssertUtils.assertEquals(mainPersonalDetailsPageLabel, expectedPersonalDetailsMainLabel, "Personal details page main label should be: " + mainPersonalDetailsPageLabel);
		String secondaryPersonalDetailsPageLabel = zimunTorimPersonalDetailsPage.getPersonalDetailsPageSecondaryLabel();
	    AssertUtils.assertEquals(secondaryPersonalDetailsPageLabel, expectedPersonalDetailsSecondaryLabel, "Personal details page secondary label should be: " + secondaryPersonalDetailsPageLabel);
		report.endLevel();
		
		// Step 2 -  Enter patient personal details
		report.startLevel("Step 2 - Enter patient personal details");
		checkPatientPersonalDetails (id, dobDay, dobMonth, dobYear);
		report.endLevel();

		
		// Step 3 - Verify the patient details are correct by getting to station location page 
		report.startLevel("Step 3 - Verify pateint details are correct by getting the station location page");
		zimunTorimPersonalDetailsPage.clickOnSelectedService();
		takeScreenshot("Screen shot of filled personal details");
		zimunTorimPersonalDetailsPage.clickNextPageButton();
		ZimunTorimStationLocatingPage zimunTorimStationLocatingPage = new ZimunTorimStationLocatingPage(driver);
		String expectedStationLocatingPageLabel = zimunTorimStationLocatingPage.getStationLocatingPageLabel();
		AssertUtils.assertEquals(stationLocatingPageLabel, expectedStationLocatingPageLabel, "Station locating page label should be: " + stationLocatingPageLabel);
		takeScreenshot("Screen shot of station locatin page");
		report.endLevel();
		
		
		
	}
	
	private void initParams() throws Exception {
		
		FileInputStream input = new FileInputStream("src/main/resources/_001_ZimunTorimVerifyPersonalDetailsTest.properties");
		Properties prop = new Properties();
		prop.load(new InputStreamReader(input, Charset.forName("UTF-8")));

		id = prop.getProperty("id");
		dobDay = prop.getProperty("dobDay");
		dobMonth = prop.getProperty("dobMonth");
		dobYear = prop.getProperty("dobYear");
		expectedPersonalDetailsMainLabel = prop.getProperty("expectedPersonalDetailsMainLabel");
		expectedPersonalDetailsSecondaryLabel= prop.getProperty("expectedPersonalDetailsSecondaryLabel");
		stationLocatingPageLabel=prop.getProperty("stationLocatingPageLabel");
		
		
	}
	
	
	private void initParamsForNewCustomer() throws Exception {
		
		FileInputStream input = new FileInputStream("src/main/resources/_001_ZimunTorimVerifyPersonalDetailsForNewCustomerTest.properties");
		Properties prop = new Properties();
		prop.load(new InputStreamReader(input, Charset.forName("UTF-8")));

		id = prop.getProperty("id");
		dobDay = prop.getProperty("dobDay");
		dobMonth = prop.getProperty("dobMonth");
		dobYear = prop.getProperty("dobYear");
		expectedPersonalDetailsMainLabel = prop.getProperty("expectedPersonalDetailsMainLabel");
		expectedPersonalDetailsSecondaryLabel= prop.getProperty("expectedPersonalDetailsSecondaryLabel");
		stationLocatingPageLabel=prop.getProperty("stationLocatingPageLabel");
		firstName=prop.getProperty("firstName");
		lastName=prop.getProperty("lastName");
		city=prop.getProperty("city");
		street=prop.getProperty("street");
		
	}
	
	
	public void fillNewCustomerDetails() throws Exception {
		
		ZimunTorimPersonalDetailsPage zimunTorimPersonalDetailsPage = new ZimunTorimPersonalDetailsPage(driver);
		checkPatientPersonalDetails (id, dobDay, dobMonth, dobYear);
		zimunTorimPersonalDetailsPage.clickOnSelectedService();
		zimunTorimPersonalDetailsPage.clickNextPageButton(); // opens the form to fill new customer's details
		// Fill in required fields for a new customer: first name, last name, city and street
		zimunTorimPersonalDetailsPage.writeToFirstNameInput(firstName);
		zimunTorimPersonalDetailsPage.writeToLastNameInput(lastName);
		zimunTorimPersonalDetailsPage.clickOnSearchCityInputForNewCustomer();
		zimunTorimPersonalDetailsPage.writeToSearchCityInputForNewCustomer(city);
		//driver.switchTo().defaultContent();
		Thread.sleep(1000);
		zimunTorimPersonalDetailsPage.clickOnSearchStreetInputForNewCustomer();
		zimunTorimPersonalDetailsPage.writeToSearchStreetInputForNewCustomer(street);
		zimunTorimPersonalDetailsPage.clickNextPageButton();
	}
	
	
}

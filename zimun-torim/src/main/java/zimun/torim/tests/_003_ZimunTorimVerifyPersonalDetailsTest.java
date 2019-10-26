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

public class _003_ZimunTorimVerifyPersonalDetailsTest extends AbstractTest {

	private String id;
	private String dobDay;
	private String dobMonth;
	private String dobYear;
	private String expectedPersonalDetailsMainLabel;
	private String expectedPersonalDetailsSecondaryLabel;
	private String stationLocatingPageLabel;
	//private String suiteName;
	private String firstName;
	private String lastName;
	private String city;
	private String street;
	private String expectedFirstService;
	
	/*@BeforeTest 
	public void getCurrentSuite (ITestContext ctx) {
		
	  suiteName = ctx.getCurrentXmlTest().getSuite().getName();
	  
	}*/
		 
	
	@Test (priority=3)
	public void _003_ZimunTorimVerifyPersonalDetails() throws Exception {
		
		browseToUrl(MainConfig.baseUrl);
		
		if (suiteName.equalsIgnoreCase("Suite1")) {
		
			initParams();
			
			report.startLevel("Step 1 - Verify that you are on personal details page");
			//browseToUrl(MainConfig.baseUrl);
			ZimunTorimPersonalDetailsPage zimunTorimPersonalDetailsPage = new ZimunTorimPersonalDetailsPage(driver);
			String mainPersonalDetailsPageLabel=zimunTorimPersonalDetailsPage.getPersonalDetailsPageMainLabel();
			AssertUtils.assertEquals(mainPersonalDetailsPageLabel, expectedPersonalDetailsMainLabel, "Personal details page main label should be: " + mainPersonalDetailsPageLabel);
			String secondaryPersonalDetailsPageLabel = zimunTorimPersonalDetailsPage.getPersonalDetailsPageSecondaryLabel();
		    AssertUtils.assertEquals(secondaryPersonalDetailsPageLabel, expectedPersonalDetailsSecondaryLabel, "Personal details page secondary label should be: " + secondaryPersonalDetailsPageLabel);
			report.endLevel();
			
			// Step 2 -  Enter patient personal details and select service
			report.startLevel("Step 2 - Enter patient personal details and select service");
			checkPatientPersonalDetails (id, dobDay, dobMonth, dobYear);
			zimunTorimPersonalDetailsPage.clickOnSelectedService();
			takeScreenshot("Screen shot of filled personal details");
			zimunTorimPersonalDetailsPage.clickNextPageButton();
			report.endLevel();
			
			
			// Step 3 - Verify the patient details are correct by getting to station location page 
			report.startLevel("Step 3 - Verify pateint details are correct by getting the station location page");
			verifyStationLocatingPageLabel();
			report.endLevel();
		}
		
		else if (suiteName.equalsIgnoreCase("Suite2")) {
			
			initParamsForNewPatient();
			fillNewPatientDetails();
			// Step 3 - Verify getting to the station locating page by verifying the page label
			report.startLevel("Step 3 - Verify getting to the station locating page by verifying the page label");
			verifyStationLocatingPageLabel();
			report.endLevel();
			
		}
				
	}
	
	private void verifyStationLocatingPageLabel() throws Exception {
		
		ZimunTorimStationLocatingPage zimunTorimStationLocatingPage = new ZimunTorimStationLocatingPage(driver);
		String expectedStationLocatingPageLabel = zimunTorimStationLocatingPage.getStationLocatingPageLabel();
		AssertUtils.assertEquals(stationLocatingPageLabel, expectedStationLocatingPageLabel, "Station locating page label should be: " + stationLocatingPageLabel);
		takeScreenshot("Screen shot of station locating page");
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
	
	
	private void initParamsForNewPatient() throws Exception {
		
		FileInputStream input = new FileInputStream("src/main/resources/_001_ZimunTorimVerifyPersonalDetailsForNewCustomerTest.properties");
		Properties prop = new Properties();
		prop.load(new InputStreamReader(input, Charset.forName("UTF-8")));

		id = prop.getProperty("id");
		dobDay = prop.getProperty("dobDay");
		dobMonth = prop.getProperty("dobMonth");
		dobYear = prop.getProperty("dobYear");
		firstName=prop.getProperty("firstName");
		lastName=prop.getProperty("lastName");
		city=prop.getProperty("city");
		street=prop.getProperty("street");
		expectedFirstService=prop.getProperty("expectedFirstService");
		stationLocatingPageLabel=prop.getProperty("stationLocatingPageLabel");
		
	}
	
	
	public void fillNewPatientDetails() throws Exception {
		
		ZimunTorimPersonalDetailsPage zimunTorimPersonalDetailsPage = new ZimunTorimPersonalDetailsPage(driver);
		// Step 1 - Fill new baby patient details and verify the customer doesn't exist by getting the first value of ביקור ראשון לתינוק נוסף במשפחה as the first value of השירות המבוקש
		report.startLevel("Step 1 - Fill new baby patient details and verify the customer doesn't exist by getting the first value of ביקור ראשון לתינוק נוסף במשפחה as the first value of השירות המבוקש");
		checkPatientPersonalDetails (id, dobDay, dobMonth, dobYear);
		zimunTorimPersonalDetailsPage.clickToOpenTheServiceSelect();
		// Verify that first service in the select is a service for new baby patient: ביקור ראשון לתינוק נוסף במשפחה
		String actualFirstService = zimunTorimPersonalDetailsPage.getSelectedServiceFromDropDownText();
		AssertUtils.assertEquals(actualFirstService, expectedFirstService, "Fisrt service for new baby patient should be: " + expectedFirstService);
		zimunTorimPersonalDetailsPage.clickOnSelectedService();
		takeScreenshot("Screen shot of personal details for new baby patient");
		zimunTorimPersonalDetailsPage.clickNextPageButton(); // opens the form to fill new customer's details
		report.endLevel();
		
		// Step 2 - Fill in required fields for a new patient: first name, last name, city and street
		report.startLevel("Step 2 - Fill in required fields for a new patient: first name, last name, city and street");
		zimunTorimPersonalDetailsPage.writeToFirstNameInput(firstName);
		zimunTorimPersonalDetailsPage.writeToLastNameInput(lastName);
		zimunTorimPersonalDetailsPage.clickOnSearchCityInputForNewCustomer();
		zimunTorimPersonalDetailsPage.writeToSearchCityInputForNewCustomer(city);
		Thread.sleep(1000);
		zimunTorimPersonalDetailsPage.clickOnSearchStreetInputForNewCustomer();
		zimunTorimPersonalDetailsPage.writeToSearchStreetInputForNewCustomer(street);
		takeScreenshot("Screen shot of personal details filled form for new baby patient");
		zimunTorimPersonalDetailsPage.clickNextPageButton();
		report.endLevel();
		
	}
	
	
}

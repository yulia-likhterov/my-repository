package zimun.torim.tests;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

import org.testng.annotations.Test;

import zimun.torim.infra.config.MainConfig;
import zimun.torim.infra.pages.ZimunTorimPersonalDetailsPage;
import zimun.torim.infra.pages.ZimunTorimStationLocatingPage;
import zimun.torim.infra.utils.AssertUtils;

public class _001_ZimunTorimVerifyPersonalDetailsTest extends AbstractTest {

	ZimunTorimPersonalDetailsPage zimunTorimPersonalDetailsPage = new ZimunTorimPersonalDetailsPage();
	
	private String id;
	private String dobDay;
	private String dobMonth;
	private String dobYear;
	private String expectedPersonalDetailsMainLabel;
	private String expectedPersonalDetailsSecondaryLabel;
	private String stationLocatingPageLabel;
	
	@Test (priority=1)
	public void _001_ZimunTorimVerifyPersonalDetails() throws Exception {
		
		initParams();
		//String station = "חרושת" for test
		
		//String expectedAvailableSlotsPerStationPageLabel = "קביעת תור- טיפת חלב "+station;
		
		//String city = "בת-ים" for test
		// Step 1 -Verify personal details page
		
		report.startLevel("Step 1 - Verify that you are on personal details page");
		browseToUrl(MainConfig.baseUrl);
		ZimunTorimPersonalDetailsPage zimunTorimPersonalDetailsPage = new ZimunTorimPersonalDetailsPage(driver);
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
		zimunTorimPersonalDetailsPage.clickNextPageButton();
		ZimunTorimStationLocatingPage zimunTorimStationLocatingPage = new ZimunTorimStationLocatingPage(driver);
		String expectedStationLocatingPageLabel = zimunTorimStationLocatingPage.getStationLocatingPageLabel();
		AssertUtils.assertEquals(stationLocatingPageLabel, expectedStationLocatingPageLabel, "Station locating page label should be: " + stationLocatingPageLabel);
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
	
	
}

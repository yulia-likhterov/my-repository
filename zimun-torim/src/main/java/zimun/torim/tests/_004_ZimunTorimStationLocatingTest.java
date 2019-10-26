package zimun.torim.tests;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

import org.testng.annotations.Test;

import zimun.torim.infra.pages.ZimunTorimAvailableSlotsPerStationPage;
import zimun.torim.infra.pages.ZimunTorimStationLocatingPage;
import zimun.torim.infra.utils.AssertUtils;

public class _004_ZimunTorimStationLocatingTest extends AbstractTest {
	
	private String city;
	private String station;
	private String expectedAvailableSlotsPerStationPageLabel;
	private boolean expectedGoToNextPageEnabled;
	
	@Test (priority=4)
	public void _004_ZimunTorimStationLocating() throws Exception {
		
		initParams();
		
		if (suiteName.equalsIgnoreCase("Suite1")) {
			
			// Step 1 - Locate station and verify patient's station
			report.startLevel("Step 1 - Locate station and verify patient's station by getting appointment slots on the next page");
			ZimunTorimStationLocatingPage zimunTorimStationLocatingPage = new ZimunTorimStationLocatingPage(driver);
			zimunTorimStationLocatingPage.setSelectedStation(station);
			zimunTorimStationLocatingPage.clickOnCityInput();
			zimunTorimStationLocatingPage.writeToEnterCity(city);
			zimunTorimStationLocatingPage.clickOnSearchCity();
			takeScreenshot("Screen shot of selected station");
			zimunTorimStationLocatingPage.clickOnselectedStation();
			verifySelectAppointmentPageLabel();
			report.endLevel();
		}
		
		else if (suiteName.equalsIgnoreCase("Suite2")) {
			
			// Step 1 - Verify that the button which goes to next page - קביעת תור - is disabled before the selection of one the stations
			report.startLevel("Step 1 - Verify that the button which goes to next page - קביעת תור - is disabled before the selection of one the stations");
			ZimunTorimStationLocatingPage zimunTorimStationLocatingPage = new ZimunTorimStationLocatingPage(driver);
			boolean actualGoToNextPageEnabled = zimunTorimStationLocatingPage.isGoToNextButtonEnabled();
			AssertUtils.assertEquals(actualGoToNextPageEnabled, expectedGoToNextPageEnabled, "the button which goes to next page - קביעת תור - should be disabled before the selection of one the stations");
			takeScreenshot("Screen shot of station locating page before a station was selected");
			report.endLevel();
			
			// Step 2 - select the חוסניה station
			report.startLevel("Step 2 - select the חוסניה station");
			zimunTorimStationLocatingPage.clickOnStationForNewPatientLabel();
			// Verify that the go to next page - קביעת תור - button has become enabled after the selection of a station
			actualGoToNextPageEnabled = zimunTorimStationLocatingPage.isGoToNextButtonEnabled();
			if (actualGoToNextPageEnabled)
				report.log("The go to next page - קביעת תור - button has become enabled after the selection of a station, as it should - OK");
			else
				report.log("The go to next page - קביעת תור - button has not become enabled after the selection of a station, there is a problem - Fail");
			takeScreenshot("Screen shot of station locating page after a station was selected");
			report.endLevel();
			
			// Step 3 - Press on the go to next page - קביעת תור - button. Expected to get the available appointment slots for the selected חוסנייה station
			report.startLevel("Step 3 - Press on the go to next page - קביעת תור - button. Expected to get the available appointment slots for the selected חוסנייה station");
			//zimunTorimStationLocatingPage.clickOnGoToNextButton();
			//verifySelectAppointmentPageLabel();
			report.endLevel();
			
		}
	}
	
		private void verifySelectAppointmentPageLabel () throws Exception {
			
			ZimunTorimAvailableSlotsPerStationPage zimunTorimAvailableSlotsPerStationPage = new ZimunTorimAvailableSlotsPerStationPage(driver);
			String availableSlotsPerStationPageLabel = zimunTorimAvailableSlotsPerStationPage.getAvailableSlotsPerStationPageLabel();
			AssertUtils.assertTrue(availableSlotsPerStationPageLabel.contains(expectedAvailableSlotsPerStationPageLabel), "Expecting to see '" + expectedAvailableSlotsPerStationPageLabel + "' as part of page title label");
			takeScreenshot("Screen shot of available slots per station");
			
		}
	
		
		private void initParams() throws Exception {
		
		FileInputStream input = new FileInputStream("src/main/resources/_002_ZimunTorimStationLocatingTest.properties");
		Properties prop = new Properties();
		prop.load(new InputStreamReader(input, Charset.forName("UTF-8")));

		city = prop.getProperty("city");
		station = prop.getProperty("station");
		expectedAvailableSlotsPerStationPageLabel = prop.getProperty("expectedAvailableSlotsPerStationPageLabel");
		expectedGoToNextPageEnabled = Boolean.parseBoolean(prop.getProperty("expectedGoToNextPageEnabled"));
	}

}

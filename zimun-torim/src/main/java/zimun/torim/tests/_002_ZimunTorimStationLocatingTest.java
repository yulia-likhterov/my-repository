package zimun.torim.tests;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

import org.testng.annotations.Test;

import zimun.torim.infra.pages.ZimunTorimAvailableSlotsPerStationPage;
import zimun.torim.infra.pages.ZimunTorimStationLocatingPage;
import zimun.torim.infra.utils.AssertUtils;

public class _002_ZimunTorimStationLocatingTest extends AbstractTest {
	
	private String city;
	private String station;
	private String expectedAvailableSlotsPerStationPageLabel;
	
	@Test (priority=2)
	public void _002_ZimunTorimStationLocating() throws Exception {
		
		initParams();
		// Step 1 - Locate station and verify patient's station
		report.startLevel("Step 1 - Locate station and verify patient's station by getting appointment slots on the next page");
		ZimunTorimStationLocatingPage zimunTorimStationLocatingPage = new ZimunTorimStationLocatingPage(driver);
		zimunTorimStationLocatingPage.setSelectedStation(station);
		zimunTorimStationLocatingPage.clickOnCityInput();
		zimunTorimStationLocatingPage.writeToEnterCity(city);
		zimunTorimStationLocatingPage.clickOnSearchCity();
		zimunTorimStationLocatingPage.clickOnselectedStation();
		ZimunTorimAvailableSlotsPerStationPage zimunTorimAvailableSlotsPerStationPage = new ZimunTorimAvailableSlotsPerStationPage(driver);
		String availableSlotsPerStationPageLabel = zimunTorimAvailableSlotsPerStationPage.getAvailableSlotsPerStationPageLabel();
		AssertUtils.assertTrue(availableSlotsPerStationPageLabel.contains(expectedAvailableSlotsPerStationPageLabel), "Expecting to see '" + expectedAvailableSlotsPerStationPageLabel + "' as part of page title label");
		
		report.endLevel();
	}
	
		private void initParams() throws Exception {
		
		FileInputStream input = new FileInputStream("src/main/resources/_002_ZimunTorimStationLocatingTest.properties");
		Properties prop = new Properties();
		prop.load(new InputStreamReader(input, Charset.forName("UTF-8")));

		city = prop.getProperty("city");
		station = prop.getProperty("station");
		expectedAvailableSlotsPerStationPageLabel = prop.getProperty("expectedAvailableSlotsPerStationPageLabel");
		
	}

}

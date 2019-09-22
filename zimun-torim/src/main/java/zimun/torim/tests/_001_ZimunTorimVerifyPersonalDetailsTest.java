package zimun.torim.tests;

import java.lang.reflect.InvocationTargetException;

import org.testng.annotations.Test;

import zimun.torim.infra.utils.AssertUtils;
import zimun.torim.infra.pages.ZimunTorimPersonalDetailsPage;
import zimun.torim.infra.pages.ZimunTorimStationLocatingPage;

public class _001_ZimunTorimVerifyPersonalDetailsTest extends AbstractTest {

	ZimunTorimPersonalDetailsPage zimunTorimPersonalDetailsPage = new ZimunTorimPersonalDetailsPage();
	
	@Test
	public void _001_ZimunTorimVerifyPersonalDetails() throws InterruptedException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		String id = "310807359";
		String dobDay = "06";
		String dobMonth = "04";
		String dobYear = "2018";
		//String [] personalDetailsPageLabels;
		
		String expectedPersonalDetailsMainLabel="איתור טיפות חלב וזימון תורים";
		String expectedPersonalDetailsSecondaryLabel="פרטים אישיים";
		String stationLocatingPageLabel="איתור טיפת חלב";
		//Object classToCheck = null;
		
		// Step 1 - Enter patient personal details
		report.startLevel("Step 1 - Enter patient personal details");
		initializeDriverInPage();
		checkPatientPersonalDetails (id, dobDay, dobMonth, dobYear);
		report.endLevel();
		
		// Step 2 - Verify personal details page
		report.startLevel("Step 2 - Verify that you are on personal details page");
		ZimunTorimPersonalDetailsPage zimunTorimPersonalDetailsPage = new ZimunTorimPersonalDetailsPage(driver);
		String mainPersonalDetailsPageLabel=zimunTorimPersonalDetailsPage.getPersonalDetailsPageMainLabel();
		AssertUtils.assertEquals(mainPersonalDetailsPageLabel, expectedPersonalDetailsMainLabel, "Personal details page main label should be: " + mainPersonalDetailsPageLabel);
		String secondaryPersonalDetailsPageLabel = zimunTorimPersonalDetailsPage.getPersonalDetailsPageSecondaryLabel();
	    AssertUtils.assertEquals(secondaryPersonalDetailsPageLabel, expectedPersonalDetailsSecondaryLabel, "Personal details page secondary label should be: " + secondaryPersonalDetailsPageLabel);
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
	
	
}

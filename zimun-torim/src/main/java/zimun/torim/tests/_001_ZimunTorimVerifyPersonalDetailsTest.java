package zimun.torim.tests;

import org.testng.annotations.Test;

import zimun.torim.infra.utils.AssertUtils;
import zimun.torim.infra.pages.ZimunTorimPersonalDetailsPage;
import zimun.torim.infra.pages.ZimunTorimStationLocatingPage;

public class _001_ZimunTorimVerifyPersonalDetailsTest extends AbstractTest {

	ZimunTorimPersonalDetailsPage zimunTorimPersonalDetailsPage = new ZimunTorimPersonalDetailsPage();
	
	@Test
	public void _001_ZimunTorimVerifyPersonalDetails() {
		
		String id = "310807359";
		String dobDay = "06";
		String dobMonth = "04";
		String dobYear = "2018";
		String [] personalDetailsPageLabels;
		String mainPersonalDetailsPageLabel="איתור טיפות חלב וזימון תורים";
		String secondaryPersonalDetailsPageLabel="פרטים אישיים";
		String stationLocatingPageLabel="איתור טיפת חלב";
		
		// Step 1 - Enter patient personal details
		report.startLevel("Step 1 - Enter patient personal details");
		checkPatientPersonalDetails (id, dobDay, dobMonth, dobYear);
		report.endLevel();
		
		// Step 2 - Verify personal details page
		report.startLevel("Step 2 - Verify that you are on personal details page");
		personalDetailsPageLabels=VerifyPersonalDetailsPageLabels();
		String expectedPersonalDetailsMainLabel = personalDetailsPageLabels[0];
		AssertUtils.assertEquals(mainPersonalDetailsPageLabel, expectedPersonalDetailsMainLabel, "Personal details page main label should be: " + mainPersonalDetailsPageLabel);
		String expectedPersonalDetailsSecondaryLabel = personalDetailsPageLabels[1];
		AssertUtils.assertEquals(secondaryPersonalDetailsPageLabel, expectedPersonalDetailsSecondaryLabel, "Personal details page secondary label should be: " + secondaryPersonalDetailsPageLabel);
		report.endLevel();
		
		// Step 3 - Verify the patient details are correct by getting to station location page 
		report.startLevel("Step 3 - Verify pateint details are correct by getting the station location page");
		zimunTorimPersonalDetailsPage.clickOnSelectedService();
		zimunTorimPersonalDetailsPage.clickNextPageButton();
		ZimunTorimStationLocatingPage zimunTorimStationLocatingPage = new ZimunTorimStationLocatingPage();
		String expectedStationLocatingPageLabel = zimunTorimStationLocatingPage.getStationLocatingPageLabel();
		AssertUtils.assertEquals(stationLocatingPageLabel, expectedStationLocatingPageLabel, "Station locating page label should be: " + stationLocatingPageLabel);
		report.endLevel();
		
	}
	
	public String[] VerifyPersonalDetailsPageLabels() {
		
		String personalDetailsMainLabel="";
		String personalDetailsSecondaryLabel="";
		String[] personalDetailsPageLabels = {personalDetailsMainLabel,personalDetailsSecondaryLabel};
		
		
		personalDetailsMainLabel=zimunTorimPersonalDetailsPage.getPersonalDetailsPageMainLabel();
		personalDetailsSecondaryLabel=zimunTorimPersonalDetailsPage.getPersonalDetailsPageSecondaryLabel();
		return personalDetailsPageLabels;
		
	}
}

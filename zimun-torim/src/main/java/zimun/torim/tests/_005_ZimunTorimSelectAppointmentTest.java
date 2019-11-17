package zimun.torim.tests;

import org.testng.annotations.Test;

import zimun.torim.infra.pages.ZimunTorimSelectAppointmentPage;
import zimun.torim.infra.pages.ZimunTorimSetAppointmentPage;

public class _005_ZimunTorimSelectAppointmentTest extends AbstractTest {
	
	// This test selects the first available appointment slot
	// and verifies it as selected properly, by getting the selected slot time frame on the next set appointment page
	@Test (priority=5)
	public void _005_ZimunTorimSelectAppointment() throws Exception {
		
		try {
			// Step 1 - Select the first appointment slot from table
			report.startLevel("Step 1 - Select the fourth appointment slot from table");
			ZimunTorimSelectAppointmentPage zimunTorimSelectAppointmentPage = new ZimunTorimSelectAppointmentPage(driver);
			takeScreenshot("Screen shot of the available appointment slots, from where we click on the first one");
			zimunTorimSelectAppointmentPage.clickOnSelectedAppointment();
			report.endLevel();
			
			// Step 2 - Verify the selected appointment slot is seen on the set appointment page
			report.startLevel("Step 1 - Verify the selected appointment slot is seen on the set appointment page");
			ZimunTorimSetAppointmentPage zimunTorimSetAppointmentPage = new ZimunTorimSetAppointmentPage(driver);
			if (zimunTorimSetAppointmentPage.isSelectedAppointmentDetailsFormDisplayed()) {
				report.log("Verified the selected appointment slot is seen on the set appointment page");
				takeScreenshot("Screen shot of selected appointment slot is seen on the setting reminders page");
			}
			else {
				report.log("Didin't verify the selected appointment slot is seen on the set appointment page");
			}
			report.endLevel();
		}
		
		catch (Exception ex) {
			
			report.log("There was exception: "+ex);
			takeScreenshot("Screen shot of exception in "+Thread.currentThread().getStackTrace()[1].getMethodName());
		}
			
	}
	   
}

package zimun.torim.tests;


import org.testng.annotations.Test;

import zimun.torim.infra.pages.ZimunTorimSelectAppointmentPage;
import zimun.torim.infra.pages.ZimunTorimSetAppointmentPage;

public class _003_ZimunTorimSelectAppointmentTest extends AbstractTest {
	
	
	
	@Test (priority=3)
	public void ZimunTorimSelectAppointment() throws Exception {
		
		// Step 1 - Select the fourth appointment slot from table
		report.startLevel("Step 1 - Select the fourth apoointment slot from table");
		ZimunTorimSelectAppointmentPage zimunTorimSelectAppointmentPage = new ZimunTorimSelectAppointmentPage(driver);
		zimunTorimSelectAppointmentPage.clickOnSelectedAppointment();
		report.endLevel();
		
		// Step 2 - Verify the selected appointment slot is seen on the setting reminders page
		report.startLevel("Step 1 - Verify the selected appointment slot is seen on the setting reminders page");
		ZimunTorimSetAppointmentPage zimunTorimSetAppointmentPage = new ZimunTorimSetAppointmentPage(driver);
		if (zimunTorimSetAppointmentPage.isSelectedAppointmentDetailsFormDisplayed()) {
			report.log("Verified the selected appointment slot is seen on the setting reminders page");
		}
		else {
			report.log("Didin't verify the selected appointment slot is seen on the setting reminders page");
		}
		report.endLevel();
		
		
		
	}
	
	
   
}

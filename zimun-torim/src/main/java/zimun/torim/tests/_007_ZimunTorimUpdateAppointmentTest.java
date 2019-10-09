package zimun.torim.tests;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

import org.testng.annotations.Test;

import zimun.torim.infra.pages.ZimunTorimAppointmentApprovalUpdateCancelSetAnotherPage;
import zimun.torim.infra.pages.ZimunTorimSelectAppointmentPage;
import zimun.torim.infra.pages.ZimunTorimSetAppointmentPage;
import zimun.torim.infra.utils.AssertUtils;

public class _007_ZimunTorimUpdateAppointmentTest extends AbstractTest {
	
	private String setAppointmentTime;
	private String updateAppointmentTime;
	private String updatedAppointmentTime;
	
	
	@Test
	public void _007_ZimunTorimUpdateAppointment() throws Exception {
		
		// Step 1 - Click on update the appointment
		report.startLevel("Step 1 - Update the appointment");
		ZimunTorimAppointmentApprovalUpdateCancelSetAnotherPage zimunTorimAppointmentApprovalUpdateCancelSetAnotherPage = new ZimunTorimAppointmentApprovalUpdateCancelSetAnotherPage(driver);
		setAppointmentTime = zimunTorimAppointmentApprovalUpdateCancelSetAnotherPage.getAppointmentSetTimeLabel();
		zimunTorimAppointmentApprovalUpdateCancelSetAnotherPage.clickOnUpdateButton();
		ZimunTorimSelectAppointmentPage ZimunTorimSelectAppointmentPage = new ZimunTorimSelectAppointmentPage(driver);
		updateAppointmentTime = ZimunTorimSelectAppointmentPage.getAppointmentUpdateTimeLabel();
		report.endLevel();
		
		// Step 2 - Verify that the time of the updated appointment differs from the time of the set appointment and update the appointment with the new time
		report.startLevel("Step 2 - Verify that the time of the updated appointment differs from the time of the set appointment");
		if (!setAppointmentTime.equals(updateAppointmentTime)) {
			report.log("The time of the updated appointment differes from the time of the set appointment - OK");
			ZimunTorimSelectAppointmentPage.clickOnSelectedAppointment();
		}
		else {
			report.log("The time of the updated appointment is the same time of the set appointment - Error");
		}
		report.endLevel();
		
		// Step 3 - Verify the appointment was updated successfully
		report.startLevel("Step 3 - Verify the appointment was updated successfully");
		ZimunTorimSetAppointmentPage zimunTorimSetAppointmentPage = new ZimunTorimSetAppointmentPage(driver);
		updatedAppointmentTime=zimunTorimSetAppointmentPage.getAppointmentSetTimeLabel();
		if (updateAppointmentTime.equals(updatedAppointmentTime)) {
			report.log("Set the appointment with the updated time");
			zimunTorimSetAppointmentPage.clickOnIshurButton();
		}
		else {
			report.log("The updated shown time is not the updated time which was selected by prior step - Error");
		}
		report.endLevel();
		
		// Step 4 - Verify the appointment set successfully 
		report.startLevel("Step 4 - Verify the appointment set successfully ");
		_005_ZimunTorimAppointmentApprovalTest zimunTorimAppointmentSetLabel = new _005_ZimunTorimAppointmentApprovalTest();
		zimunTorimAppointmentSetLabel.ZimunTorimAppointmentApproval();
		report.endLevel();
		
		
		
	}
	
	
	
		
}

package zimun.torim.tests;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;
import org.testng.annotations.Test;
import zimun.torim.infra.pages.ZimunTorimAppointmentApprovalUpdateCancelSetAnotherPage;
import zimun.torim.infra.pages.ZimunTorimPersonalDetailsPage;

public class _010_ZimunTorimSetAdditionalAppointmentTest extends AbstractTest {
	
	private String id;
	private String dobDay;
	private String dobMonth;
	private String dobYear;
	
	// This test sets an appointment for additional existing patient
	@Test (priority=10)
	public void _010_ZimunTorimSetAdditionalAppointment() throws Exception {
		
		try {
		
			initParams();
			// Step 1 - Click on set additional appointment and fill another existing patients' details
			report.startLevel("Step 1 - Click on set additional appointment and fill another existing patients' details");
			ZimunTorimAppointmentApprovalUpdateCancelSetAnotherPage zimunTorimAppointmentApprovalUpdateCancelSetAnotherPage = new ZimunTorimAppointmentApprovalUpdateCancelSetAnotherPage(driver);
			zimunTorimAppointmentApprovalUpdateCancelSetAnotherPage.clickOnSetAdditionalAppointmentLink();
			ZimunTorimPersonalDetailsPage zimunTorimPersonalDetailsPage = new ZimunTorimPersonalDetailsPage(driver);
			checkPatientPersonalDetails (id, dobDay, dobMonth, dobYear);
			zimunTorimPersonalDetailsPage.clickOnSelectedService();
			takeScreenshot("Screen shot of filled personal details");
			zimunTorimPersonalDetailsPage.clickNextPageButton();
			report.endLevel();
			
			// Step 2 - Go to station locating page and select the patients' station
			report.startLevel("Step 2 - Go to station locating page and select the patients' station");
			_004_ZimunTorimStationLocatingTest zimunTorimStationLocating = new _004_ZimunTorimStationLocatingTest();
			zimunTorimStationLocating._004_ZimunTorimStationLocating();
			report.endLevel();
			
			// Step 3 - Go to available appointment slots page and select the first appointment
			report.startLevel("Step 3 - Go to available appointment slots page and select the first appointment");
			_005_ZimunTorimSelectAppointmentTest zimunTorimSelectAppointment = new _005_ZimunTorimSelectAppointmentTest();
			zimunTorimSelectAppointment._005_ZimunTorimSelectAppointment();
			report.endLevel();
			
			// Step 4 - Go to set appointment page, fill in patients' mobile phone and email address -
			// the same as for the previously set appointment and press on set appointment
			report.startLevel("Step 4 - Go to set appointment page, fill in patients' mobile phone and email address - the same as for the previously set appointment and press on set appointment");
			_006_ZimunTorimSetAppointmentTest zimunTorimSetAppointment = new _006_ZimunTorimSetAppointmentTest();
			zimunTorimSetAppointment._006_ZimunTorimSetAppointment(); 
			report.endLevel();
			
			// Step 5 - Verify the appointment was set successfully by getting the approval text from appointment approval page
			report.startLevel("Step 5 - Verify the appointment was set successfully by getting the approval text from appointment approval page");
			_007_ZimunTorimAppointmentApprovalTest zimunTorimAppointmentApproval = new _007_ZimunTorimAppointmentApprovalTest();
			zimunTorimAppointmentApproval._007_ZimunTorimAppointmentApproval();
			report.endLevel();
		}
		
		catch (Exception ex) {
			
			report.log("There was exception: "+ex);
			takeScreenshot("Screen shot of exception in "+Thread.currentThread().getStackTrace()[1].getMethodName());
		}
		
	}
	
	private void initParams() throws Exception {
		
		FileInputStream input = new FileInputStream("src/main/resources/_009_ZimunTorimSetAdditionalAppointmentTest.properties");
		Properties prop = new Properties();
		prop.load(new InputStreamReader(input, Charset.forName("UTF-8")));
		id = prop.getProperty("id");
		dobDay = prop.getProperty("dobDay");
		dobMonth = prop.getProperty("dobMonth");
		dobYear = prop.getProperty("dobYear");	
			
	}

}

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
	
	@Test (priority=10)
	public void _010_ZimunTorimSetAdditionalAppointment() throws Exception {
		
		initParams();
		ZimunTorimAppointmentApprovalUpdateCancelSetAnotherPage zimunTorimAppointmentApprovalUpdateCancelSetAnotherPage = new ZimunTorimAppointmentApprovalUpdateCancelSetAnotherPage(driver);
		zimunTorimAppointmentApprovalUpdateCancelSetAnotherPage.clickOnSetAdditionalAppointmentLink();
		ZimunTorimPersonalDetailsPage zimunTorimPersonalDetailsPage = new ZimunTorimPersonalDetailsPage(driver);
		checkPatientPersonalDetails (id, dobDay, dobMonth, dobYear);
		zimunTorimPersonalDetailsPage.clickOnSelectedService();
		takeScreenshot("Screen shot of filled personal details");
		zimunTorimPersonalDetailsPage.clickNextPageButton();
		_004_ZimunTorimStationLocatingTest zimunTorimStationLocating = new _004_ZimunTorimStationLocatingTest();
		zimunTorimStationLocating._004_ZimunTorimStationLocating();
		_005_ZimunTorimSelectAppointmentTest zimunTorimSelectAppointment = new _005_ZimunTorimSelectAppointmentTest();
		zimunTorimSelectAppointment._005_ZimunTorimSelectAppointment();
		_006_ZimunTorimSetAppointmentTest zimunTorimSetAppointment = new _006_ZimunTorimSetAppointmentTest();
		zimunTorimSetAppointment._006_ZimunTorimSetAppointment(); 
		_007_ZimunTorimAppointmentApprovalTest zimunTorimAppointmentApproval = new _007_ZimunTorimAppointmentApprovalTest();
		zimunTorimAppointmentApproval._007_ZimunTorimAppointmentApproval(); 
		
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

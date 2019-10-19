package zimun.torim.tests;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

import org.testng.annotations.Test;

import zimun.torim.infra.pages.ZimunTorimAppointmentApprovalUpdateCancelSetAnotherPage;
import zimun.torim.infra.pages.ZimunTorimPersonalDetailsPage;

public class _008_ZimunTorimSetAdditionalAppointmentTest extends AbstractTest {
	
	private String id;
	private String dobDay;
	private String dobMonth;
	private String dobYear;
	
	@Test (priority=8)
	public void _008_ZimunTorimSetAdditionalAppointment() throws Exception {
		
		initParams();
		ZimunTorimAppointmentApprovalUpdateCancelSetAnotherPage zimunTorimAppointmentApprovalUpdateCancelSetAnotherPage = new ZimunTorimAppointmentApprovalUpdateCancelSetAnotherPage(driver);
		zimunTorimAppointmentApprovalUpdateCancelSetAnotherPage.clickOnSetAdditionalAppointmentLink();
		ZimunTorimPersonalDetailsPage zimunTorimPersonalDetailsPage = new ZimunTorimPersonalDetailsPage(driver);
		checkPatientPersonalDetails (id, dobDay, dobMonth, dobYear);
		zimunTorimPersonalDetailsPage.clickOnSelectedService();
		takeScreenshot("Screen shot of filled personal details");
		zimunTorimPersonalDetailsPage.clickNextPageButton();
		_003_ZimunTorimStationLocatingTest zimunTorimStationLocating = new _003_ZimunTorimStationLocatingTest();
		zimunTorimStationLocating._003_ZimunTorimStationLocating();
		_004_ZimunTorimSelectAppointmentTest zimunTorimSelectAppointment = new _004_ZimunTorimSelectAppointmentTest();
		zimunTorimSelectAppointment._004_ZimunTorimSelectAppointment();
		_005_ZimunTorimSetAppointmentTest zimunTorimSetAppointment = new _005_ZimunTorimSetAppointmentTest();
		zimunTorimSetAppointment._005_ZimunTorimSetAppointment(); 
		_006_ZimunTorimAppointmentApprovalTest zimunTorimAppointmentApproval = new _006_ZimunTorimAppointmentApprovalTest();
		zimunTorimAppointmentApproval._006_ZimunTorimAppointmentApproval(); 
		
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

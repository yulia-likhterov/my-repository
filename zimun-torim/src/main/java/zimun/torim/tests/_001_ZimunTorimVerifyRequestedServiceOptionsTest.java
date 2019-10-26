package zimun.torim.tests;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//import net.bytebuddy.description.annotation.AnnotationList.Empty;
import zimun.torim.infra.config.MainConfig;
import zimun.torim.infra.pages.ZimunTorimPersonalDetailsPage;
import zimun.torim.infra.utils.AssertUtils;
//import zimun.torim.infra.utils.AssertUtils;
import zimun.torim.infra.web.Patient;

public class _001_ZimunTorimVerifyRequestedServiceOptionsTest extends AbstractTest {
	
	@DataProvider(name = "csvParamsPatients")
	public Object[][] dataProviderPatients() throws Exception {
		
		FileInputStream fstream = new FileInputStream("src/main/resources/Patients.csv");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream, StandardCharsets.UTF_8));

		int numOfLines = 0;
		String line;

		ArrayList<Patient> patients = new ArrayList<Patient>();
		
		while ((line = br.readLine()) != null) {
			
			if (numOfLines > 0) {
				
				//String[] splitStr = line.split(",");
				String[] splitStr = line.split(",", -1);
				Patient patient = new Patient(splitStr[0], splitStr[1], splitStr[2], splitStr[3], splitStr[4], splitStr[5]);
				patients.add(patient);
			}
			
			numOfLines++;
		}
		
		br.close();
		
		Object[][] params = new Object[numOfLines-1][1];
		
		for (int i=0; i<numOfLines-1; i++) {
			params[i][0] = patients.get(i);
		}

		return params;
	}
	
	
	// The next test verifies that the requested service select options are filled according to id number and year of birth
	// The logic is as following: first, to check whether the patient is an existing one (according to id number check in db)
	// Second, the values in the service select depend also on the year of birth of the patient:
	// According to the year of birth the patient is defined either as a baby or as a pregnant woman, and the optional services filled accordingly
	// In Patiens.csv file the tested patients are ordered as following:
	// 1. an existing pregnant woman patient
	// 2. an existing baby patient
	// 3. a new pregnant woman patient
	// 4. a new baby patient
	@Test(dataProvider = "csvParamsPatients", priority=1)
	public void getServiceToSelectAccordingToPatientIdAndYearOfBirth (Patient patient) throws Exception {
		
		report.startLevel("Step 1 - Fill services from file to expectedServices list");
		List<String> servicesFromCSVFile = new ArrayList<String>();
		servicesFromCSVFile.add(patient.serviceSelectExpectedResultFirst);
		servicesFromCSVFile.add(patient.serviceSelectExpectedResultSecond);
		List<String> expectedServices = new ArrayList<String>();
		for (String service : servicesFromCSVFile) {
			if (!service.equals("")) {
				expectedServices.add(service);
			}
		}
		report.endLevel();
		
		// Step 1 - Fill id and date of birth values from file
		report.startLevel("Step 1 - Fill id and date of birth values from file");
		browseToUrl(MainConfig.baseUrl);
		checkPatientPersonalDetails (patient.id, patient.dobDay, patient.dobMonth, patient.dobYear);
		report.endLevel();
		
		// Step 2 - Get the available values in the requested service select
		report.startLevel("Step 2 - Get the available values in the requested service select");
		ZimunTorimPersonalDetailsPage zimunTorimPersonalDetailsPage = new ZimunTorimPersonalDetailsPage(driver);
		zimunTorimPersonalDetailsPage.clickToOpenTheServiceSelect();
		List<String> actualServices = new ArrayList<String>();
		actualServices = zimunTorimPersonalDetailsPage.getAvailableServiceOptions();
		report.endLevel();
		
		// Step 3 - Compare between 3 lists, and Verify that the actual available values of the select, are identical to the expected values received from the csv file
		report.startLevel("Step 3 - Compare between 3 lists, and Verify that the actual available values of the select, are identical to the expected values received from the csv file");
		boolean isIdentical = false;
		for (int i = 0; i<actualServices.size(); i++) {
			if (actualServices.get(i).toString().equals(expectedServices.get(i).toString())) {
				isIdentical=true;
				AssertUtils.assertTrue(isIdentical, "Expecting to see '" + expectedServices.get(i) + "' within the options of service select");
			}
			else {
				isIdentical=false;
				AssertUtils.assertTrue(isIdentical, "Expecting to see '" + expectedServices.get(i) + "' within the options of service select, but recieved: '" + actualServices.get(i) + "'");
			}
		}
		report.endLevel();
	}
	

}

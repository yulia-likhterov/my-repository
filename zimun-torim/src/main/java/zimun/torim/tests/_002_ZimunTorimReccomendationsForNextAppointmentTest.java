package zimun.torim.tests;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import zimun.torim.infra.config.MainConfig;
import zimun.torim.infra.pages.ZimunTorimPersonalDetailsPage;
import zimun.torim.infra.pages.ZimunTorimSelectAppointmentPage;
import zimun.torim.infra.pages.ZimunTorimStationLocatingPage;
import zimun.torim.infra.utils.AssertUtils;
import zimun.torim.infra.web.Reccomendation;

public class _002_ZimunTorimReccomendationsForNextAppointmentTest extends AbstractTest {
	
	@DataProvider(name = "csvParamsReccomendationsForNextAppointment")
	public Object[][] dataProviderPatients() throws Exception {
		
		FileInputStream fstream = new FileInputStream("src/main/resources/ReccomendationsForNextAppointment.csv");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream, StandardCharsets.UTF_8));

		int numOfLines = 0;
		String line;

		ArrayList<Reccomendation> reccomendations = new ArrayList<Reccomendation>();
		
		while ((line = br.readLine()) != null) {
			
			if (numOfLines > 0) {
				
				String[] splitStr = line.split(",", -1);
				Reccomendation reccomendation = new Reccomendation(splitStr[0], splitStr[1], splitStr[2], splitStr[3], splitStr[4], splitStr[5], splitStr[6], splitStr[7]);
				reccomendations.add(reccomendation);
			}
			
			numOfLines++;
		}
		
		br.close();
		
		Object[][] params = new Object[numOfLines-1][1];
		
		for (int i=0; i<numOfLines-1; i++) {
			params[i][0] = reccomendations.get(i);
		}

		return params;
	}
	
	
	// Each patient that visits tipat chalav can receive a recommendation for next appointment, which practically means 
	// which appointment type/s he should schedule to for his next visit at tipat chalav. At tipat chalav there are 2 appointment types:
	// a nurse or a doctor. Sometimes there is a recommendation for a combined appointment, then the visit includes appointment 
	// to the nurse and to the doctor at the same visit, with close to each other appointment hours (in usual, the second appointment is right after the first one)
	// In some cases, there is no recommendation at all, then the patient will receive the default appointment type - nurse
	// when he will try to schedule an appointment - an appointment would be to a nurse. 
	// This test verifies that the given recommendations to patients regarding their next appointment are as they appear in the tipat chalav system
	// and as it appears in the csv file:
	// patient id 300736386 - no recommendation. We should see a nurse appointment type
	// patient id 310807359 - recommendation to combined appointment - we should see 2 appointment types: nurse + doctor
	@Test(dataProvider = "csvParamsReccomendationsForNextAppointment", priority=2)
	public void getAppoitmentTypesAccordingToPatientsReccomendation (Reccomendation reccomendation) throws Exception {
		
		try {
			//Step 1 - Fill appointment types from file to expectedReccomendations list
			report.startLevel("Step 1 - Fill appointment types from file to expectedReccomendations list");
			List<String> reccomendationsFromCSVFile = new ArrayList<String>();
			reccomendationsFromCSVFile.add(reccomendation.expectedAppointmentTypeFirst);
			reccomendationsFromCSVFile.add(reccomendation.expectedAppointmentTypeSecond);
			List<String> expectedReccomendations = new ArrayList<String>();
			for (String expectedReccomendation : reccomendationsFromCSVFile) {
				if (!expectedReccomendation.equals("")) {
					expectedReccomendations.add(expectedReccomendation);
				}
			}
			report.endLevel();
			
			// Step 2 - Fill id and date of birth values from file
			report.startLevel("Step 2 - Fill id and date of birth values from file");
			browseToUrl(MainConfig.baseUrl);
			checkPatientPersonalDetails (reccomendation.patientID, reccomendation.patientDOBDay, reccomendation.patientDOBMonth, reccomendation.patientDOBYear);
			report.endLevel();
			
			// Step 3 - Select the first service and go to the next - station locating page
			report.startLevel("Step 3 - Select the first service and go to the next - station locating page");
			ZimunTorimPersonalDetailsPage zimunTorimPersonalDetailsPage = new ZimunTorimPersonalDetailsPage(driver);
			zimunTorimPersonalDetailsPage.clickOnSelectedService();
			zimunTorimPersonalDetailsPage.clickNextPageButton();
			report.endLevel();
			
			// Step 4 - Select the city and station of the patient, and go to the next - select appointment page
			report.startLevel("Step 4 - Select the city and station of the patient, and go to the next - select appointment page");
			ZimunTorimStationLocatingPage zimunTorimStationLocatingPage = new ZimunTorimStationLocatingPage(driver);
			zimunTorimStationLocatingPage.setSelectedStation(reccomendation.patientsStation);
			zimunTorimStationLocatingPage.clickOnCityInput();
			zimunTorimStationLocatingPage.writeToEnterCity(reccomendation.patientsCity);
			zimunTorimStationLocatingPage.clickOnSearchCity();
			zimunTorimStationLocatingPage.clickOnselectedStation();
			report.endLevel();
			
			// Step 5 - Get the appointment type/s for each of the patients from the csv file, 
			report.startLevel("Step 5 - Get the appointment type/s for each of the patients from the csv file");
			ZimunTorimSelectAppointmentPage zimunTorimSelectAppointmentPage = new ZimunTorimSelectAppointmentPage(driver);
			List<String> actualReccomendations = new ArrayList<String>();
			actualReccomendations = zimunTorimSelectAppointmentPage.getReccomendationForNextAppointmentPerPatient();
			takeScreenshot("Screen shot of appointment type\\s per patient");
			report.endLevel();
			
			// Step 6 - Compare between the expected and actual appointment type values and verify that the actual appointment type values are identical to the expected values received from the csv file
			report.startLevel("Step 6 - Compare between the expected and actual appointment type values and verify that the actual appointment type values are identical to the expected values received from the csv file");
			boolean isIdentical = false;
			for (int i = 0; i<actualReccomendations.size(); i++) {
				if (actualReccomendations.get(i).toString().equals(expectedReccomendations.get(i).toString())) {
					isIdentical=true;
					AssertUtils.assertTrue(isIdentical, "Expecting to see '" + expectedReccomendations.get(i) + "' within the appointment type for the patient");
				}
				else {
					isIdentical=false;
					AssertUtils.assertTrue(isIdentical, "Expecting to see '" + expectedReccomendations.get(i) + "' within the appointment type for the patient, but recieved: '" + actualReccomendations.get(i) + "'");
				}
			}
			report.endLevel();
		}
		
		catch (Exception ex) {
			
			report.log("There was exception: "+ex);
			takeScreenshot("Screen shot of exception in "+Thread.currentThread().getStackTrace()[1].getMethodName());
		}
		
	}
	

}

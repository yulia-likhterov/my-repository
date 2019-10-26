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
				
				//String[] splitStr = line.split(",");
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
	
	
	@Test(dataProvider = "csvParamsReccomendationsForNextAppointment", priority=2)
	public void getAppoitmentTypesAccordingToPatientsReccomendation (Reccomendation reccomendation) throws Exception {
		
		report.startLevel("Step 1 - Fill services from file to expectedServices list");
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
		
		// Step 1 - Fill id and date of birth values from file
		report.startLevel("Step 1 - Fill id and date of birth values from file");
		browseToUrl(MainConfig.baseUrl);
		checkPatientPersonalDetails (reccomendation.patientID, reccomendation.patientDOBDay, reccomendation.patientDOBMonth, reccomendation.patientDOBYear);
		report.endLevel();
		
		// Step 2 - Select the service and go to the next - station locating page
		report.startLevel("Step 2 - Select the service");
		ZimunTorimPersonalDetailsPage zimunTorimPersonalDetailsPage = new ZimunTorimPersonalDetailsPage(driver);
		zimunTorimPersonalDetailsPage.clickOnSelectedService();
		zimunTorimPersonalDetailsPage.clickNextPageButton();
		report.endLevel();
		
		// Step 3 - Select the city and station of the patient, and go to the next - select appointment page
		report.startLevel("Step 3 - Select the city and station of the patient, and go to the next - select appointment page");
		ZimunTorimStationLocatingPage zimunTorimStationLocatingPage = new ZimunTorimStationLocatingPage(driver);
		zimunTorimStationLocatingPage.setSelectedStation(reccomendation.patientsStation);
		zimunTorimStationLocatingPage.clickOnCityInput();
		zimunTorimStationLocatingPage.writeToEnterCity(reccomendation.patientsCity);
		zimunTorimStationLocatingPage.clickOnSearchCity();
		zimunTorimStationLocatingPage.clickOnselectedStation();
		report.endLevel();
		
		// Step 4 - Get the appointment type/s for each of the patients from the csv file, 
		report.startLevel("Step 4 - Get the appointment type/s for each of the patients from the csv file");
		ZimunTorimSelectAppointmentPage zimunTorimSelectAppointmentPage = new ZimunTorimSelectAppointmentPage(driver);
		List<String> actualReccomendations = new ArrayList<String>();
		actualReccomendations = zimunTorimSelectAppointmentPage.getReccomendationForNextAppointmentPerPatient();
		report.endLevel();
		
		// Step 5 - Compare between the expected and actual appointment type values and verify that the actual appointment type values are identical to the expected values received from the csv file
		report.startLevel("Step 5 - Compare between the expected and actual appointment type values and verify that the actual appointment type values are identical to the expected values received from the csv file");
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
	

}

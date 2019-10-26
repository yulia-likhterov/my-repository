package zimun.torim.infra.web;

public class Reccomendation {
	
	public String patientID;
	public String patientDOBDay;
	public String patientDOBMonth;
	public String patientDOBYear;
	public String patientsCity;
	public String patientsStation;
	public String expectedAppointmentTypeFirst;
	public String expectedAppointmentTypeSecond;
	
	public Reccomendation (String patientID, String patientDOBDay, String patientDOBMonth, String patientDOBYear, String patientsCity, String patientsStation, String expectedAppointmentTypeFirst, String expectedAppointmentTypeSecond) {
		this.patientID = patientID;
		this.patientDOBDay = patientDOBDay;
		this.patientDOBMonth = patientDOBMonth;
		this.patientDOBYear = patientDOBYear;
		this.patientsCity = patientsCity;
		this.patientsStation = patientsStation;
		this.expectedAppointmentTypeFirst = expectedAppointmentTypeFirst;
		this.expectedAppointmentTypeSecond = expectedAppointmentTypeSecond;
	}
	
	public String toString() {
		return "Reccomendation for patient id: " + patientID + "; should be expected appointment type: " + expectedAppointmentTypeFirst + "; " + expectedAppointmentTypeSecond;
	}


}

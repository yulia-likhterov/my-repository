package zimun.torim.infra.web;

public class Patient {
	
	public String id;
	public String dobDay;
	public String dobMonth;
	public String dobYear;
	public String serviceSelectExpectedResultFirst;
	public String serviceSelectExpectedResultSecond;
	
	public Patient (String id, String dobDay, String dobMonth, String dobYear, String serviceSelectExpectedResultFirst, String serviceSelectExpectedResultSecond) {
		this.id = id;
		this.dobDay = dobDay;
		this.dobMonth = dobMonth;
		this.dobYear = dobYear;
		this.serviceSelectExpectedResultFirst = serviceSelectExpectedResultFirst;
		this.serviceSelectExpectedResultSecond = serviceSelectExpectedResultSecond;
	}
	
	public String toString() {
		return "Patient id: " + id + "; Year of birth: " + dobYear + "; expected first service to select from: " + serviceSelectExpectedResultFirst + "; expected second service to select from: " + serviceSelectExpectedResultSecond;
	}

}

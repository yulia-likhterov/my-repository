package zimun.torim.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;


import zimun.torim.infra.pages.ZimunTorimPersonalDetailsPage;
import zimun.torim.infra.web.BrowserType;
import zimun.torim.infra.web.WebDriverFactory;
import il.co.topq.difido.ReportDispatcher;
import il.co.topq.difido.ReportManager;

@Listeners(il.co.topq.difido.ReportManagerHook.class)
public abstract class AbstractTest {
	
	protected WebDriver driver;
	protected ReportDispatcher report = ReportManager.getInstance();
	
	@BeforeMethod
	public void beforeMethod() throws Exception {
		
		if (driver == null) {
			driver = WebDriverFactory.getDriver(BrowserType.CHROME);
		}
	}
	
	public void checkPatientPersonalDetails (String id, String dobDay, String dobMonth, String dobYear) {
		
		String url = "https://my.health.gov.il/MyPortal/Pages/FHCAppointWOP.aspx";
		report.log("Browse to URL: " + url);
		driver.get(url);
		
		ZimunTorimPersonalDetailsPage zimunTorimPersonalDetailsPage = new ZimunTorimPersonalDetailsPage(driver);
		zimunTorimPersonalDetailsPage.writeId(id);
		zimunTorimPersonalDetailsPage.writeDOB(dobDay, dobMonth, dobYear);
		zimunTorimPersonalDetailsPage.clickOnSelectedService();
	}
	
	

}

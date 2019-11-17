package zimun.torim.tests;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import zimun.torim.infra.pages.ZimunTorimPersonalDetailsPage;
import zimun.torim.infra.web.BrowserType;
import zimun.torim.infra.web.WebDriverFactory;
import zimun.torim.infra.config.MainConfig;
import il.co.topq.difido.ReportDispatcher;
import il.co.topq.difido.ReportManager;
import il.co.topq.difido.model.Enums.Status;
import java.io.File;

@Listeners(il.co.topq.difido.ReportManagerHook.class)
public abstract class AbstractTest {
	
	protected static WebDriver driver;
	protected static ReportDispatcher report = ReportManager.getInstance();
	protected static String suiteName;
	
	@BeforeSuite
	public static void beforeMethod() throws Exception {
		
		MainConfig.initFromFile("src/main/resources/MainConfig.properties");
		
		if (driver == null) {
			driver = WebDriverFactory.getDriver(BrowserType.CHROME);
		}
	}
	
	@BeforeSuite 
	protected void getCurrentSuite (ITestContext ctx) {
		
	  suiteName = ctx.getCurrentXmlTest().getSuite().getName();
	  
	}
	
	public void browseToUrl (String url) {
		
		report.log("Browse to URL: " + url);
		driver.get(url);
	}
	
	public void checkPatientPersonalDetails (String id, String dobDay, String dobMonth, String dobYear) throws InterruptedException {
	
		ZimunTorimPersonalDetailsPage zimunTorimPersonalDetailsPage = new ZimunTorimPersonalDetailsPage(driver);
		zimunTorimPersonalDetailsPage.writeId(id);
		zimunTorimPersonalDetailsPage.writeDOB(dobDay, dobMonth, dobYear);
		
	}
	
	public static void takeScreenshot(String description) throws Exception {
		
		if (driver != null) {
			File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			report.log(description);
			report.addImage(screenshotFile, description);
			
		}
		else {
			report.log("driver == null; Can't take screenshot.", Status.warning);
		}
	}
	
}

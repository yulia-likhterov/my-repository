package zimun.torim.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import zimun.torim.infra.pages.ZimunTorimPersonalDetailsPage;
import zimun.torim.infra.pages.AbstractPage;
import zimun.torim.infra.web.BrowserType;
import zimun.torim.infra.web.WebDriverFactory;
import zimun.torim.infra.config.MainConfig;
import il.co.topq.difido.ReportDispatcher;
import il.co.topq.difido.ReportManager;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@Listeners(il.co.topq.difido.ReportManagerHook.class)
public abstract class AbstractTest {
	
	protected static WebDriver driver;
	protected ReportDispatcher report = ReportManager.getInstance();
	
	@BeforeSuite
	public static void beforeMethod() throws Exception {
		
		MainConfig.initFromFile("src/main/resources/MainConfig.properties");
		
		if (driver == null) {
			driver = WebDriverFactory.getDriver(BrowserType.CHROME);
		}
	}
	
	public void browseToUrl (String url) {
		
		report.log("Browse to URL: " + url);
		driver.get(url);
	}
	
	public void checkPatientPersonalDetails (String id, String dobDay, String dobMonth, String dobYear) throws InterruptedException {
	
		ZimunTorimPersonalDetailsPage zimunTorimPersonalDetailsPage = new ZimunTorimPersonalDetailsPage(driver);
		zimunTorimPersonalDetailsPage.writeId(id);
		zimunTorimPersonalDetailsPage.writeDOB(dobDay, dobMonth, dobYear);
		//zimunTorimPersonalDetailsPage.clickOnSelectedService();
	}
	
	
	/*public String verifyLabelOnPage(WebDriver driver, String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		String labelOnPage="";
		Class<?> c = Class.forName(className);
		Constructor<?> cons = c.getConstructor(WebDriver.class);
		Object object = cons.newInstance(driver);
		
		labelOnPage=((ZimunTorimPersonalDetailsPage) object).getPersonalDetailsPageMainLabel();
		return labelOnPage;
		
	}*/

}

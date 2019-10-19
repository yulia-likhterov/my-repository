package zimun.torim.infra.web;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import zimun.torim.infra.web.BrowserType;

public class WebDriverFactory {
	
	protected static WebDriver driver;
	
	@BeforeSuite
	public static WebDriver getDriver(BrowserType browserType) throws Exception {	
	
		driver = null;
		
			switch(browserType) {
			case CHROME:
				System.setProperty("webdriver.chrome.driver", "webdrivers/chromedriver.exe");
				driver = new ChromeDriver();
				break;
			case FIREFOX:
				System.setProperty("webdriver.gecko.driver", "webdrivers/geckodriver.exe");
				driver = new FirefoxDriver();
				break;
			default:
				throw new Exception("Browser type: " + browserType + " is not supported yet.");
				
			}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		
		return driver;
	}
	
	@AfterSuite
	public void afterSuite() {
		
	    driver.quit();
	    
	}

}

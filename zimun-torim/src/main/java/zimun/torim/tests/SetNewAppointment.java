package zimun.torim.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import il.co.topq.difido.ReportDispatcher;
import il.co.topq.difido.ReportManager;

@Listeners(il.co.topq.difido.ReportManagerHook.class)


public class SetNewAppointment {
	
	protected ReportDispatcher report = ReportManager.getInstance();
	
	@Test
	
	public void setNewAppointment() throws InterruptedException {
		
		
		System.setProperty("webdriver.chrome.driver", "webdrivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); // instead of Thread.sleep(500)
		
		report.startLevel("Browse to zimun torim portal");
		driver.get("https://my.health.gov.il/MyPortal/Pages/FHCAppointWOP.aspx");
		
		String mainTitleOnPageExpected = "איתור טיפות חלב וזימון תורים";
		String mainTitleOnPageLabel = driver.findElement(By.xpath("//h1[text()[contains(.,'איתור טיפות חלב וזימון תורים')]]")).getText();
		report.log("Verifying page titles");
		Assert.assertEquals(mainTitleOnPageLabel, mainTitleOnPageExpected, "Main title on page expected to be:" + mainTitleOnPageExpected);
		report.endLevel();
		
		WebElement id = driver.findElement(By.id("personalId"));
		id.sendKeys("310807359");
		//id.sendKeys("223068925");
		
		WebElement dobDay = driver.findElement(By.cssSelector("input[type='text'][tabindex='2']"));
		dobDay.sendKeys("06");
		//dobDay.sendKeys("07");
		dobDay.sendKeys(Keys.ENTER);
		
		WebElement dobMonth = driver.findElement(By.cssSelector("input[type='text'][tabindex='3']"));
		dobMonth.sendKeys("04");
		//dobMonth.sendKeys("06");
		dobMonth.sendKeys(Keys.ENTER);
		
		WebElement dobYear = driver.findElement(By.cssSelector("input[type='text'][tabindex='4']"));
		dobYear.sendKeys("2018");
		//dobYear.sendKeys("2015");
		dobYear.sendKeys(Keys.ENTER);
		dobYear.sendKeys(Keys.ENTER);
		
		//WebElement selectDropDown = driver.findElement(By.cssSelector("span[ng-click='$select.activate()']"));
		//selectDropDown.click();
		
		//WebElement selectDropDown = driver.findElement(By.cssSelector("span[class='ui-select-choices-row-inner']"));
		WebElement selectDropDown = driver.findElement(By.cssSelector("div[class='ui-select-match']"));
		selectDropDown.click();
		//selectDropDown.sendKeys(Keys.ENTER);
		WebElement selectSherut = driver.findElement(By.cssSelector("span[class='ui-select-placeholder text-muted ng-binding']"));
		selectSherut.click();
		WebElement selectedSherut = driver.findElement(By.id("ui-select-choices-row-0-0"));
		selectedSherut.click();
		//Select selectServiceType = new Select (driver.findElement(By.cssSelector("span[ng-click='$select.activate()']")));
		//selectServiceType.selectByIndex(0);
		
		WebElement nextPageButton = driver.findElement(By.id("gobut"));
		nextPageButton.click();
		
		WebElement selectCity = driver.findElement(By.cssSelector("input[tabindex='1']"));
		selectCity.click();
		selectCity.sendKeys("חוסנייה");
		selectCity.sendKeys(Keys.ENTER);
		//selectCity.sendKeys("ירושלים");
		//selectCity.sendKeys(Keys.ENTER);
		
		
		WebElement searchCityButton = driver.findElement(By.cssSelector("button[ng-click='searchStations($event,0)']"));
		searchCityButton.click();
		
		//WebElement selectedStation = driver.findElement(By.xpath("//*[text()[contains(.,'בית הכרם')]]"));
		WebElement selectedStation = driver.findElement(By.xpath("//*[text()[contains(.,'חוסניה')]]"));
		selectedStation.click();
		
		Thread.sleep(30000);
		WebElement selectedAppointment = driver.findElement(By.xpath("//tr[4]//a[@class='btn book-appointment-btn ng-binding']")); 
		boolean displayedAppointment = selectedAppointment.isDisplayed();
		selectedAppointment.click();
		
		WebElement mobilePhoneBaseNumber = driver.findElement(By.cssSelector("input[name='BaseNumber']"));
		mobilePhoneBaseNumber.sendKeys("8657654");
		
		//Select selectMobileProfix = new Select (driver.findElement(By.id("prefixNumb")));
		//selectMobileProfix.selectByIndex(2);
		
		
		WebElement mobilePhonePrefix = driver.findElement(By.cssSelector("a[class='sbSelector'"));
		mobilePhonePrefix.click();
		WebElement selectedMobilePrifix = driver.findElement(By.cssSelector("a[href='#052']"));
		selectedMobilePrifix.click();
		
		
		Thread.sleep(30000);
		WebElement ishurButton = driver.findElement(By.id("gobut"));
		ishurButton.click();
		
		WebElement verifyAppointmentSet = driver.findElement(By.xpath("//div[text()[contains(.,'התור נקבע בהצלחה')]]"));
		//verifyAppointmentSet.
		
		WebElement cancelAppointmentButton = driver.findElement(By.id("butCancelAppointment"));
		cancelAppointmentButton.click();
		
		
		
		
	}

}

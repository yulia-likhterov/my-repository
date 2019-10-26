package zimun.torim.infra.web;

import java.time.Duration;
import java.util.ArrayList;
//import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import zimun.torim.infra.web.By2;
import il.co.topq.difido.ReportDispatcher;
import il.co.topq.difido.ReportManager;


public class ActionBot {
	
	private WebDriver driver;
	protected ReportDispatcher report = ReportManager.getInstance();
	private boolean isClicked = false;
	
	public ActionBot(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isEnabled (By2 by2) {
		
		String value =  driver.findElement(by2.by).getAttribute("disabled");
		
		if (value!=null) {
			return false;
		}
		return true;
	}
	
	public boolean clickWithReturn (By2 by2) {
		
		try {
			report.log("Click: " + by2);
			if (!driver.findElement(by2.by).isDisplayed()) {
				waitForElementToBeVisible(by2, 80);
			}
			if (!driver.findElement(by2.by).isSelected()) {
				waitForElementToBeClickable(by2, 40);
			}
			driver.findElement(by2.by).click();
			isClicked = true;
			return isClicked;
		}
		catch (Exception ex) {
			report.log("Didn't click on the element: " + by2);
			isClicked = false;
			return isClicked;
		}
	}
	
	public boolean hasValue(By2 by2) {
		
		String text = driver.findElement(by2.by).getAttribute("value");
		if (text.isEmpty())
			return false;
		return true;
	}
	
	public void click(By2 by2) throws InterruptedException {
		report.log("Click: " + by2);
		if (!driver.findElement(by2.by).isDisplayed()) {
			//waitForElementToBeVisible(by2, 80);
			Thread.sleep(1000);
		}
		else if (!driver.findElement(by2.by).isSelected()) {
			waitForElementToBeClickable(by2, 40);
		}
		driver.findElement(by2.by).click();
		
	}
	
	public void clickWithEnter(By2 by2) throws InterruptedException {
		report.log("Click: " + by2);
		if (!driver.findElement(by2.by).isDisplayed()) {
			Thread.sleep(1000);
		}
		else if (!driver.findElement(by2.by).isSelected()) {
			Thread.sleep(1000);
		}
		driver.findElement(by2.by).click();
		driver.findElement(by2.by).sendKeys(Keys.ENTER);
		//driver.findElement(by2.by).sendKeys(Keys.ENTER);
	}
	
	public void writeToElementWithEnter(By2 by2, String text) throws InterruptedException {
		report.log("Write '" + text + "' to: " + by2);
		if (!driver.findElement(by2.by).isDisplayed()) {
			Thread.sleep(1000);
		}
		driver.findElement(by2.by).sendKeys(text);
		driver.findElement(by2.by).sendKeys(Keys.ENTER);
		//driver.findElement(by2.by).sendKeys(Keys.ENTER);
	}
	
	public void writeToElementWithTwoEnters(By2 by2, String text) throws InterruptedException {
		report.log("Write '" + text + "' to: " + by2);
		if (!driver.findElement(by2.by).isDisplayed()) {
			Thread.sleep(1000);
		}
		driver.findElement(by2.by).sendKeys(text);
		driver.findElement(by2.by).sendKeys(Keys.ENTER);
		driver.findElement(by2.by).sendKeys(Keys.ENTER);
	}
	
	public void writeToElement(By2 by2, String text) throws InterruptedException {
		report.log("Write '" + text + "' to: " + by2);
		if (!driver.findElement(by2.by).isDisplayed()) {
			Thread.sleep(1000);
		}
		driver.findElement(by2.by).sendKeys(text);
		
	}
	
	public String getElementTextForAvailableSlots(By2 by2) throws Exception{
		
		String text;
		try {
			waitForElementToBeVisible(by2, 30);	
		}
		catch (Exception ex) {
			text="";
			report.log("Element " + by2 + " inner text: '" + text + "'");
			return text;
		}
		text = driver.findElement(by2.by).getText();
		report.log("Element " + by2 + " inner text: '" + text + "'");
		return text;
	}
	
	public String getElementText(By2 by2) throws Exception{
		
		String text;
		try {
			if (!driver.findElement(by2.by).isDisplayed())
				Thread.sleep(1000);
		}
		catch (Exception ex) {
			text="";
			report.log("Element " + by2 + " inner text: '" + text + "'");
			return text;
		}
		text = driver.findElement(by2.by).getText();
		report.log("Element " + by2 + " inner text: '" + text + "'");
		return text;
	}
	
	
	public boolean isElementVisible (By2 by2) {
		
		if (driver.findElement(by2.by).isDisplayed())
			return true;
		
		return false;
	}
	
	public void waitForElementNotDisplayed(By2 by2) {
		try {
		WebElement element = driver.findElement(by2.by);
		WebDriverWait webDriverWait = new WebDriverWait(driver, 50);
		webDriverWait.until(ExpectedConditions.invisibilityOf(element));
		}
		catch (Exception ex) {
			System.out.println("Exception from wait for element not visible: "+ex);
		}
	}
	
	public void waitForElementToBeVisible(By2 by2, int timeoutInSeconds) {
		
		/*WebDriverWait webDriverWait = new WebDriverWait(driver, 350);
		WebElement element = driver.findElement(by2.by);
		webDriverWait.until(ExpectedConditions.visibilityOf(element));*/
		FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
		        .withTimeout(Duration.ofSeconds(40))
		        .pollingEvery(Duration.ofMillis(10))
		        .ignoring(NoSuchElementException.class);
		
		fluentWait.until(ExpectedConditions.elementToBeSelected(driver.findElement(by2.by)));
	}
	
	
	public void waitForElementToBeClickable(By2 by2, int timeoutInSeconds) {
		WebElement element = driver.findElement(by2.by);
		WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSeconds);
		webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void switchToIframeById(String iframeId) {
		
		driver.switchTo().frame(iframeId);
	}
	
	public void switchToNewTab(String url) {
		
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		if (tabs.size()<2) {
			
			((JavascriptExecutor)driver).executeScript("window.open()");
			tabs = new ArrayList<String> (driver.getWindowHandles());
			
		}
		driver.switchTo().window(tabs.get(1)); // switches to new tab
		driver.get(url);
	}
	
	public void switchBackToMainTab() {
		
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0)); // switch back to main screen        
	
	}
	
}

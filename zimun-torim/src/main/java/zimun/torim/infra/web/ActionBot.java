package zimun.torim.infra.web;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import zimun.torim.infra.web.By2;
import il.co.topq.difido.ReportDispatcher;
import il.co.topq.difido.ReportManager;

public class ActionBot {
	
	private WebDriver driver;
	protected ReportDispatcher report = ReportManager.getInstance();
	
	public ActionBot(WebDriver driver) {
		this.driver = driver;
	}
	
	public void click(By2 by2) throws InterruptedException {
		report.log("Click: " + by2);
		/*if (!driver.findElement(by2.by).isDisplayed()) {
			Thread.sleep(1000);
		}
		*/
		if (!driver.findElement(by2.by).isSelected()) {
			waitForElementToBeClickable(by2, 15);
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
	
	public String getElementText(By2 by2) throws Exception {
		String text = driver.findElement(by2.by).getText();
		/*if (!driver.findElement(by2.by).isDisplayed()) {
			waitForElementNotDisplayed(by2);
		}*/
		report.log("Element " + by2 + " inner text: '" + text + "'");
		return text;
	}
	
	public boolean isElementVisible (By2 by2) {
		
		if (driver.findElement(by2.by).isDisplayed())
			return true;
		
		return false;
	}
	
	public void waitForElementNotDisplayed(By2 by2) {
		WebElement element = driver.findElement(by2.by);
		WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
		webDriverWait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public void waitForElementToBeClickable(By2 by2, int timeoutInSeconds) {
		WebElement element = driver.findElement(by2.by);
		WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSeconds);
		webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
	}

}

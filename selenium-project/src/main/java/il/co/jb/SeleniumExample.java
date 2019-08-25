package il.co.jb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumExample {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "webdrivers/chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http:/google.co.il");
		
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("John Bryce");
		searchBox.submit();

	}

}

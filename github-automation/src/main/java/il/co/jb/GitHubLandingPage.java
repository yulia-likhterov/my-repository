package il.co.jb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GitHubLandingPage {
	
	private WebDriver driver;
	private static final By signInLink = By.xpath("//a[@href='/login']"); // a variable value that will never change
	private static final By signUpButton = By.cssSelector("div.HeaderMenu a[href='join?source=header-home']");
	
	public GitHubLandingPage(WebDriver driver) {
		
		this.driver = driver;
	}

	public void clickSignInLink() {
		driver.findElement(signInLink).click();	
	}
	
	public void clickSignUpButton() {
		driver.findElement(signUpButton).click();	
	}
}

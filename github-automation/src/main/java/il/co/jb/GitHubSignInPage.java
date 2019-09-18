package il.co.jb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GitHubSignInPage {
	
	private WebDriver driver;
	
	By usernameInput = By.id("login_field");
	By passordInput = By.id("password");
	By signInButton = By.xpath("(//*[@name='commit'])");
	
	public GitHubSignInPage(WebDriver driver) {
		
		this.driver = driver;
	}
	
	public void writeUserName(String username) {
		
		driver.findElement(usernameInput).sendKeys(username);
	}
	
	public void writePassword(String password) {
		
		driver.findElement(usernameInput).sendKeys(password);
	}
	
	public void signIn() {
		
		driver.findElement(signInButton).click();
	}

}

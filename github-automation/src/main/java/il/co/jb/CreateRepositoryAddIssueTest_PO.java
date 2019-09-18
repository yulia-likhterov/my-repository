package il.co.jb;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class CreateRepositoryAddIssueTest_PO {

	@Test
	
	public void createRepositoryAddIssueTest() {
	
	System.setProperty("webdriver.chrome.driver", "webdrivers/chromedriver.exe");

	String newRepoName = "yulias-test-repository";
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // instead of Thread.sleep(500)
	
	driver.get("https://github.com/");
	
	GitHubLandingPage gitHubLandingPage = new GitHubLandingPage(driver);
	
	gitHubLandingPage.clickSignInLink();
	
	String username = "yulia-likhterov";
	String password = "juli0919!";
	GitHubSignInPage gitHubSignInPage = new GitHubSignInPage(driver);
	gitHubSignInPage.writeUserName(username);
	gitHubSignInPage.writePassword(password);
	gitHubSignInPage.signIn();
	
	}
	
}

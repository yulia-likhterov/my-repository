package il.co.jb;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class TestGitHubLogin {

	@Test
	public static void testGitHub() throws Exception {

		System.setProperty("webdriver.chrome.driver", "webdrivers/chromedriver.exe");

		String newRepoName = "yulias-test-repository";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // instead of Thread.sleep(500)
		
		driver.get("https://github.com/");
		driver.findElement(By.xpath("//a[@href='/login']")).click();
		//WebElement signInOption = driver.findElement(By.cssSelector("a[href*='/login']"));
		//signInOption.click();

		driver.findElement(By.xpath("(//*[@id='login_field'])")).sendKeys("yulia-likhterov");
		//WebElement userName = driver.findElement(By.id("login_field"));
		//userName.sendKeys("yulia-likhterov");
		// searchBox.sendKeys("John Bryce\n");

		driver.findElement(By.xpath("(//*[@id='password'])")).sendKeys("juli0919!");
		//WebElement password = driver.findElement(By.id("password"));
		//password.sendKeys("juli0919!");

		driver.findElement(By.xpath("(//*[@name='commit'])")).click();
		// WebElement signInButton = driver.findElement(By.name("commit"));
		//WebElement signInButton = driver.findElement(By.cssSelector("[name='commit']"));
		//signInButton.click();
		
		driver.findElement(By.xpath("//div[@class='mb-3 Details js-repos-container mt-5']//div[@class='js-repos-container']//a[@class='btn btn-sm btn-primary text-white']")).click();
		//WebElement addNewRepository = driver.findElement(By.cssSelector("div>div>aside>div>div>div>h2>a.btn.btn-sm.btn-primary.text-white[href='/new']"));
		//addNewRepository.click();
		
		driver.findElement(By.xpath("//*[@id='repository_name']")).sendKeys(newRepoName);
		//WebElement repositoryName = driver.findElement(By.id("repository_name"));
		//repositoryName.sendKeys(newRepoName);
		
		driver.findElement(By.xpath("//*[@id='repository_description']")).sendKeys("just a regular description");
		//WebElement repositoryDescription = driver.findElement(By.id("repository_description"));
		//repositoryDescription.sendKeys("just a regular description");
		
		driver.findElement(By.xpath("//*[@aria-describedby='private-description']")).click();
		//WebElement privateRepository = driver.findElement(By.cssSelector("[aria-describedby='private-description']"));
		//privateRepository.click();
		
		driver.findElement(By.xpath("//*[@class='btn btn-primary first-in-line']")).click();
		//WebElement createRepository = driver.findElement(By.cssSelector(".btn.btn-primary.first-in-line"));
		//createRepository.click();
		
		driver.findElement(By.xpath("//a[@href='/yulia-likhterov/"+newRepoName +"/issues']")).click();
		//WebElement selectIssues = driver.findElement(By.cssSelector("a[href='/yulia-likhterov/"+newRepoName +"/issues']"));
		//selectIssues.click();
		
		driver.findElement(By.xpath("//a[@class='btn btn-primary float-right']")).click();
		//Thread.sleep(1000);
		//WebElement newIssue = driver.findElement(By.cssSelector("a[href='/yulia-likhterov/"+newRepoName+"/issues/new'].btn.btn-primary.float-right"));
		//WebElement newIssue = driver.findElement(By.cssSelector("div>div>#js-repo-pjax-container>div>div.repository-content>div.js-check-all-container>div.subnav>a[href='/yulia-likhterov/"+newRepoName+"/issues/new'].btn.btn-primary.float-right"));
		//WebElement newIssue = driver.findElement(By.cssSelector("a[href='/yulia-likhterov/"+newRepoName +"/issues/new'].dropdown-item"));
		//WebElement newIssue = driver.findElement(By.cssSelector("div>header.Header>div.Header-item.position-relative>details.details-overlay.details-reset>details-menu.dropdown-menu.dropdown-menu-sw>a[href='/yulia-likhterov/eclipse-workspace/issues/new'].dropdown-item"));
		//newIssue.click();
		
		driver.findElement(By.xpath("//*[@id='issue_title']")).sendKeys("Test issue");
		//WebElement issueTitle = driver.findElement(By.id("issue_title"));
		//issueTitle.sendKeys("Test issue");
		
		driver.findElement(By.xpath("//*[@id='issue_body']")).sendKeys("This is a test to check my comments inside a new issue....");
		//WebElement issueComment = driver.findElement(By.id("issue_body"));
		//issueComment.sendKeys("This is a test to check my comments inside a new issue....");
		
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		//WebElement submitNewIssue = driver.findElement(By.cssSelector("div>div>#js-repo-pjax-container>div>div>div>#new_issue>div>div>div>div>div.flex-items-center.flex-justify-end.mx-3.mb-3.px-0.d-none.d-md-flex>button.btn.btn-primary"));
		//submitNewIssue.click();
		
		
	}

}

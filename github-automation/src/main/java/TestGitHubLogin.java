import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestGitHubLogin {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "webdrivers/chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://github.com/login");
		
		WebElement userName = driver.findElement(By.id("login_field"));
		userName.sendKeys("yulia-likhterov");
		//searchBox.sendKeys("John Bryce\n");
		
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("juli0919!\n");
		
		WebElement signInButton = driver.findElement(By.name("commit"));
		signInButton.click();

	}

}

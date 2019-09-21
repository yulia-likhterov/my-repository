package zimun.torim.infra.pages;

import org.openqa.selenium.WebDriver;
import il.co.topq.difido.ReportDispatcher;
import il.co.topq.difido.ReportManager;
import zimun.torim.infra.web.ActionBot;

public abstract class AbstractPage {
	
	protected WebDriver driver;
	protected ActionBot bot;
	
	protected ReportDispatcher report = ReportManager.getInstance();
	
	public AbstractPage(WebDriver driver) {
		report.log("*** Current page: " + this.getClass().getSimpleName());
		this.driver = driver;
		this.bot = new ActionBot(driver);

	}
	
	public AbstractPage () {
		
	}
	
}

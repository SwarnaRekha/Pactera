package core.com.pactera;


import org.openqa.selenium.WebDriver;
import project.com.pactera.pages.HomePage;
import core.com.pactera.framework.WaitHandler;
public class Browser{

	public WebDriver driver = null;
	public TestEnvironment testEnv = null;
	public HomePage homePage = null;
 	public WaitHandler waitHandler = null;
	
	public Browser(TestEnvironment testEnv)
	{
		 this.testEnv = testEnv;
		 this.driver = testEnv.getDriver();
		 
	}
	
	public HomePage open() {
		return open(testEnv.getConfiguration().getURL());
	}

	
	public HomePage open(String url) {
		driver.get(url);
		getWaitHandler().waitForDocStateReady();
		return getHomePage();
		
	}
	
	
	public void close() {
		driver.close();
	}
	
	


	public HomePage getHomePage() {
		
		if(homePage==null)
		{
			homePage = new HomePage(testEnv);
		}
		return homePage;

	}
	
	
	public WaitHandler getWaitHandler() {
		if(waitHandler == null)
		{
			waitHandler = new WaitHandler(testEnv);
		}
		return waitHandler;
	}

	
}

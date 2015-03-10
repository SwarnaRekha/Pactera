package core.com.pactera.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.com.pactera.TestEnvironment;


public class WaitHandler {

	private static final long WAIT_TIME = 40;
	private WebDriver driver;
	
	
	public WaitHandler(TestEnvironment testEnv) {
		driver = testEnv.getDriver();
		
	}
 
	public void waitForDocStateReady() {
	    ExpectedCondition<Boolean> pageLoadCondition = new
	        ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver driver) {
	                return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	            }
	        };
	    WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
	    wait.until(pageLoadCondition);
	}
	
	
	public void waitForElementVisibility(By locator) {
		Wait<WebDriver> wait = new WebDriverWait(driver,WAIT_TIME);
				
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	
}

package project.com.pactera.pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.com.pactera.Browser;
import core.com.pactera.TestEnvironment;

public class HomePage {
	
	private WebDriver driver = null;
	private TestEnvironment testEnv = null;
	private Browser browser = null;
	public SearchResults searchResults = null;
	
	public HomePage(TestEnvironment testEnv) {
		this.testEnv = testEnv;
		this.driver = testEnv.getDriver();
		this.browser = testEnv.getBrowser();
	}

    //Search in Home Page
	public SearchResults search(String searchText)  {
		
		WebElement searchElmt = driver.findElement(By.id("s"));
				
		searchElmt.clear();
		searchElmt.sendKeys(searchText);
		searchElmt.sendKeys(Keys.ENTER);
		browser.waitHandler.waitForElementVisibility(By.id("content"));
		return getSearchResults();       

	}
	
	//To verify home page is loaded
	public boolean isAt()
    {
		
	    try {
		        driver.findElement(By.id("home-slides"));
		    }
	    catch (NoSuchElementException e)
	    	{
		        return false;
		    }
		    return true;
	}
	
	public SearchResults getSearchResults() {
		
		if(searchResults==null)
		{
			searchResults = new SearchResults(testEnv);
		}
		return searchResults;

	}
	
}
	
	
	
	
		





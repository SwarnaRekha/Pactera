/**
 * 
 */
package project.com.pactera.pages;

import org.openqa.selenium.WebDriver;


import core.com.pactera.*;

public class SearchResults {

	
	private WebDriver driver = null;
	private TestEnvironment testEnv = null;
	private Browser browser = null;
	
	public SearchResults(TestEnvironment testEnv) {
		this.testEnv = testEnv;
		this.driver = testEnv.getDriver();
		this.browser = testEnv.getBrowser();
	}

	    
	 //Get the title of the page
        public String getTitle()
        {
             
             return driver.getTitle();
        }
	
}

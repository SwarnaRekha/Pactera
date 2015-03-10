package com.pactera.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import project.com.pactera.pages.SearchResults;
import core.com.pactera.TestNG.TestBase;
import core.com.pactera.TestNG.TestInfo;

public class Search extends TestBase{
	
	@Test(dataProvider="TestData",groups={"smoke"})
	@TestInfo(tcid="TC-001",dataFileName="TestSearchPactera.csv")
	public void testSearch(String SearchString) 
	{
		
		//assert that user is at home page
		Assert.assertEquals(homePage.isAt(),true,"Home page not loaded");
	
		//search for the string in search box
		SearchResults searchResults = homePage.search(SearchString);

        //Assert the title of search results page
		Assert.assertEquals(searchResults.getTitle(), SearchString+" | Search Results | Pactera", "Search page is not loaded");
		
	}

}

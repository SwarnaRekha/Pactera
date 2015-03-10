/**
 * 
 */
package core.com.pactera.TestNG;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.TestNGException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import project.com.pactera.pages.HomePage;
import core.com.pactera.Browser;
import core.com.pactera.TestEnvironment;


public abstract class TestBase {
	
	private static final String DEFAULT_DATA_FILE_FORMAT = ".csv";
	
	
	public static TestEnvironment testEnv = null;
	public static Browser browser = null;
	public static WebDriver driver = null;
	public static HomePage homePage = null;
	
	@BeforeClass(alwaysRun=true)
	public void aaaSetUp()
	{
		testEnv = new TestEnvironment();
		browser = testEnv.getBrowser();
		homePage = browser.open();
		driver = testEnv.getDriver();
		
	}
		
	@AfterClass(alwaysRun=true)
	public void zzzTearDown(){
					browser.close();
		
	}
	
	@AfterClass(alwaysRun=true)
	public void zzzzCloseAllOpenBrowsers(){
			testEnv.terminate();
		
	}
    @DataProvider(name = "TestData")
	public Iterator<Object[]> dataProvider(Method m) {
		
		
		System.setProperty("data.dir",System.getProperty("user.dir")+System.getProperty("file.separator")+"config"+System.getProperty("file.separator")+"Data");
		
		String fileName = getFileName(m);
		
		if(!fileName.toLowerCase().endsWith(DEFAULT_DATA_FILE_FORMAT))
			fileName = fileName + DEFAULT_DATA_FILE_FORMAT;
		
		File dataFile = new File(System.getProperty("data.dir")
				+ System.getProperty("file.separator") + fileName);

		BufferedReader br = null;
		String line = "";
		StringTokenizer tokens = null;
		String delimiter = ",";
		List<Object[]> data = new LinkedList<Object[]>();
		Object[] rowData = null;
		int counter = 0;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					dataFile)));
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				tokens = new StringTokenizer(line, delimiter);
				rowData = new Object[tokens.countTokens()];
				counter = 0;
				while (tokens.hasMoreTokens())
					rowData[counter++] = tokens.nextToken();
				data.add(rowData);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new WebDriverException("Data file with name : \""+dataFile.getName()+"\" is not found");
		} catch (IOException e) {
			e.printStackTrace();
			throw new WebDriverException("Error reading data from file: "
					+ fileName);
		} finally {
			try {
				if(br != null){
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				throw new WebDriverException(
						"Error closing reader object on file: " + fileName);
			}
		}
		return data.iterator();

	}
	
	/**
	 * to get the file name from annotation declared along with <b>@Test</b> for a method
	 * @author Sachin
	 * @return name of the file name
	 */
	private String getFileName(Method m){
		TestInfo testInfo = (TestInfo)m.getAnnotation(TestInfo.class);
		if(testInfo == null)
			throw new TestNGException("@TestInfo annotation is not declared on Test method");
		Test test = (Test) m.getAnnotation(Test.class);
		if(!test.dataProvider().equals("")){
			if(testInfo.dataFileName().equals("")){
				throw new TestNGException("Element 'dataFileName' is not defined for @TestInfo on a Test Method "
						+ "that has 'dataProvider' element defined in @Test annotation");
			}
				
		}
		String fileName = testInfo.dataFileName();
		return fileName;
	}

}


	
	



package core.com.pactera;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class Configuration{
	
	private String url;
	private String browserName;
	private String chromeDriverPath;
	private String ieDriverPath;
	private Properties prop;
	
	public Configuration() {
		prop = new Properties();
		try {
			prop.load(new FileInputStream(new File(System.getProperty("user.dir")+"/config/cunit.properties")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(System.getenv("browser.name") == null){
			browserName = prop.getProperty("browser.name");
		}else{
			browserName = System.getenv("browser.name");
		}
		if(System.getenv("instance.url") == null){
			url = prop.getProperty("instance.url");
		}else{
			url = System.getenv("instance.url");
		}
		System.out.println("Configuration Properties:");
		System.out.println("instance.url="+url);
		System.out.println("browser="+browserName);
		chromeDriverPath = prop.getProperty("chrome.driver");
		ieDriverPath = prop.getProperty("ie.driver");
		}

	
	public String getURL() {
		return url;
	}

	
	public String getBrowserName() {
		return browserName;
	}

	
	public String getChromeDriverPath() {
		return chromeDriverPath;
	}
	
	
	public String getIEDriverPath() {
		return ieDriverPath;
	}
	
	public String getProperty(String propName) {
		return prop.getProperty(propName);
	}

}

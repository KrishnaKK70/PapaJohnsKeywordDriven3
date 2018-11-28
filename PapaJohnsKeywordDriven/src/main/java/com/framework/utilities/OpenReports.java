package com.framework.utilities;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

public class OpenReports implements IReporter  {
	
		public WebDriver driver;
	
		@Override
		public void generateReport(List<XmlSuite> arg0, List<ISuite> arg1, String arg2) {
		  
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(System.getProperty("user.dir")+"/Reports/CurrentReport.html");
		
		}

}

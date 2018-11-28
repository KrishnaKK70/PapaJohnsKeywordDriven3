package com.framework.platforms;

import static com.framework.keywords.RunTestCases.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.framework.reports.ExtentReporting;
import com.framework.utilities.ExcelReader;

public class Browsers extends ExtentReporting{

	public static String oFilepath = "./src/main/resources/ExcelReports/";
	public static WebDriver driver;
	public static String ChromeDriverPath="./src/main/resources/drivers/chromedriver.exe";
	public static String IEDriverPath="./src/main/resources/drivers/IEDriverServer.exe";
	public static String FirefoxDriverPath="./src/main/resources/drivers/geckodriver.exe";
	
	 public void setup() throws IOException, InterruptedException{
		 GeneratingReport();
		 oFilepath = oFilepath+getDateandtime()+".xlsx";
		 XSSFWorkbook wbook=new XSSFWorkbook(new FileInputStream(new File(Filepath)));
		  XSSFSheet sheet = wbook.getSheet(SheetName);
		  sheet.setColumnHidden(4, true);
		  sheet.setColumnHidden(5, true);
		  sheet.setColumnHidden(6, true);
		  sheet.setColumnHidden(7, true);
		  sheet.setColumnHidden(8, true);
		try{
			
			FileOutputStream outputStream = new FileOutputStream(oFilepath);
			wbook.write(outputStream);
			
		   } catch (Exception e) {
			
			e.printStackTrace();
			
		   }
	 ExcelReader data = new ExcelReader(Filepath);
	  String browserName = data.getCellData("Environment", "Browser", 2);
	  //open Firefox Browser 
	  //open the browser based on parameter passed in .xml file.
	  if(browserName.equalsIgnoreCase("firefox")){
		  //if you are using selenium 3.0	  
		  System.setProperty("webdriver.gecko.driver", FirefoxDriverPath);
		  driver = new FirefoxDriver();
//		  DesiredCapabilities cap = DesiredCapabilities.firefox();
//	 	cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
//		 	driver = new RemoteWebDriver(new URL("http://192.168.0.101:4444/wd/hub"),cap);
//		   driver.manage().window().maximize();
	  }
	  else if(browserName.equalsIgnoreCase("chrome")){
	   
	   //open chrome browser
		System.setProperty("webdriver.chrome.driver",ChromeDriverPath );
	    driver= new ChromeDriver();
//	   DesiredCapabilities cap = DesiredCapabilities.chrome();
//	   cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
//	   driver = new RemoteWebDriver(new URL("http://192.168.0.101:4444/wd/hub"),cap);
	   driver.manage().window().maximize();

	  }
	  
	  else if(browserName.equalsIgnoreCase("ie")){
	  // open Internet Explorer Browser
        System.setProperty("webdriver.ie.driver", IEDriverPath);
       driver= new InternetExplorerDriver();
     /* DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
//        cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
      driver = new RemoteWebDriver(new URL("http://192.168.0.101:4444/wd/hub"),cap);
//	   driver.manage().window().maximize();
*/
	   
	  }
	  else {
		  throw new RuntimeException("only chrome, firefox, ie supported");
		  
	  }
	  
	  
	  String URL = data.getCellData("Environment", "URL", 2);
	  //Navigating URL
	  driver.get(URL);	
}
	 
	 
		public static String getDateandtime() {
			Date d = new Date();
			Timestamp t = new Timestamp(d.getTime());
			String timeStamp = t.toString();
			timeStamp = timeStamp.replace(' ', '_');
			return timeStamp = timeStamp.replace(':', '_');
			
		}
	
}
package com.framework.keywords;

import static com.framework.reports.Reports.*;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.framework.common.CommonMethod;
import com.framework.platforms.Browsers;
import com.framework.reports.LoggerHelper;
import com.relevantcodes.extentreports.LogStatus;

public class Keywords extends Browsers {
	public static Logger log = LoggerHelper.getLogger(Keywords.class);
	
	
	
	public static void keyword(String TestCaseName,String actionContent,String elementLocator,String value, String expected, int statusRow) throws IOException{
		
		long start = System.currentTimeMillis();
		
		try{
			
		switch(actionContent.toLowerCase()){
		
		case "teststart":
			System.out.println("******Testcases Started******");
			
			test = extent.startTest(TestCaseName);
			break;
		
		case "endtest":
			extent.endTest(test);
			extent.flush();
			break;
	 
		case "click":
			try {
			if(isElementPresent(driver, elementLocator)) {
				CommonMethod.findElement(driver, elementLocator).click();
				log.info("clicking the field...." +elementLocator);
				test.log(LogStatus.PASS, " clicking on element Successfully "+elementLocator,test.addScreenCapture(takeScreenShot()));
				setExcelReports("Pass", statusRow, start, "element Clicked");
			}
			}
			catch(Throwable t) {
				log.error("clicking the field...." +elementLocator);
				test.log(LogStatus.FAIL, " clicking on element Failed "+elementLocator,test.addScreenCapture(takeScreenShot()));
				setExcelReports("Fail", statusRow, start, "element not Clicked");;
				takeScreenShotOnFailure("ElementclickFailed");
				System.out.println("Element not visible or not displayed on the webpage" +elementLocator);

			}
		break;
		
		case "clear":
			try {
			if(isElementPresent(driver, elementLocator)) {
				CommonMethod.findElement(driver, elementLocator).clear();
				log.info("clicking the field...." +elementLocator);
				test.log(LogStatus.PASS, " clearing element Successfully "+elementLocator,test.addScreenCapture(takeScreenShot()));
				setExcelReports("Pass", statusRow, start, "element cleared");			
				}
			}
			catch(Throwable t) {
				log.error("clicking the field...." +elementLocator);
				test.log(LogStatus.FAIL, " clearing element Failed "+elementLocator,test.addScreenCapture(takeScreenShot()));
				setExcelReports("Fail", statusRow, start, "element not cleared");
				takeScreenShotOnFailure("ElementClearFailed");
				System.out.println("Element not visible or not displayed on the webpage" +elementLocator);

			}

	case "enter":
		try {
		if(isElementPresent(driver, elementLocator)) {
			
			CommonMethod.findElement(driver,elementLocator).sendKeys(value);
			log.info("passing data to the field...." +elementLocator);
			test.log(LogStatus.PASS, " Passing data to the element Successfully "+elementLocator,test.addScreenCapture(takeScreenShot()));
			setExcelReports("Pass", statusRow, start, "enter data passed");
		}
		}
		catch(Throwable t) {
			log.error("passing data to the field...." +elementLocator);
			test.log(LogStatus.FAIL, " Passing data to the element Failed "+elementLocator,test.addScreenCapture(takeScreenShot()));
			setExcelReports("Fail", statusRow, start, "enter data failed");
			takeScreenShotOnFailure("ElementEnterFailed");
			System.out.println("Element not visible or not displayed on the webpage" +elementLocator);
		}
		break;
	
		case "checkcontentequal":
			String actual = null;
			try {
				
			  actual = CommonMethod.findElement(driver, elementLocator).getText();

			 if(actual.equals(expected)){
					log.info("Actual value......"+actual+ "..... is  matching with expected value...."+expected+".......");
					test.log(LogStatus.PASS, actual+ "..... is  matching with expected value...."+expected,test.addScreenCapture(takeScreenShot()));
					setExcelReports("Pass", statusRow, start, "Content Equal");				
					}
			 else {
				 	log.error("Actual value......"+actual+ "..... is  not matching with expected value...."+expected+".......");
					test.log(LogStatus.FAIL, actual+ "..... is  not matching with expected value...."+expected,test.addScreenCapture(takeScreenShot()));
					setExcelReports("Fail", statusRow, start, "Content not Equal");
				 
			 }
			 }
			catch(Throwable t) {
				log.error("Actual value......"+actual+ "..... is  not matching with expected value...."+expected+".......");
				test.log(LogStatus.FAIL, actual+ "..... is  not matching with expected value...."+expected,test.addScreenCapture(takeScreenShot()));
				setExcelReports("Fail", statusRow, start, "Content not Equal");
				takeScreenShotOnFailure("checkcontentequalFailed");
				System.out.println("Element not visible or not displayed on the webpage" +elementLocator);
			}
			 break;
			 
		case "checkcontentcontains":
			String actual1 = null;
			try {
			  actual1 = CommonMethod.findElement(driver, elementLocator).getText();

			 if(actual1.contains(expected)){
					log.info("Actual value......"+actual1+ "..... is  matching with expected value...."+expected+".......");
					test.log(LogStatus.PASS, actual1+ "..... is  matching with expected value...."+expected,test.addScreenCapture(takeScreenShot()));
					setExcelReports("Pass", statusRow, start, "Content Equal");						}
			 
			 else {
				log.error("Actual value......"+actual1+ "..... is  not matching with expected value...."+expected+".......");
				test.log(LogStatus.FAIL, actual1+ "..... is  not matching with expected value...."+expected,test.addScreenCapture(takeScreenShot()));
				setExcelReports("Fail", statusRow, start, "Content not Equal");	
				
			 }}
			catch(Throwable t) {
				log.error("Actual value......"+actual1+ "..... is  not matching with expected value...."+expected+".......");
				test.log(LogStatus.FAIL, actual1+ "..... is  not matching with expected value...."+expected,test.addScreenCapture(takeScreenShot()));
				setExcelReports("Fail", statusRow, start, "Content not Equal");	
				takeScreenShotOnFailure("checkcontentcontainsFailed");
				System.out.println("Element not visible or not displayed on the webpage" +elementLocator);
			}
			 break; 
			 
		case "checkelementpresent":
			try {
			 if(isElementPresent(driver, expected)){
					log.info("Actual value......"+expected+ "..... is  present");
					test.log(LogStatus.PASS, expected+ "..... is present....",test.addScreenCapture(takeScreenShot()));
					setExcelReports("Pass", statusRow, start, "Element present");							
					}
			 else {
				 log.error("Actual value......"+expected+ "..... is  not present");
					test.log(LogStatus.FAIL, expected+ "..... is not present....",test.addScreenCapture(takeScreenShot()));
					setExcelReports("Fail", statusRow, start, "Element not Present");
			     }
			 	}
			catch(Throwable t) {
				log.error("Actual value......"+expected+ "..... is  not present");
				test.log(LogStatus.FAIL, expected+ "..... is not present....",test.addScreenCapture(takeScreenShot()));
				setExcelReports("Fail", statusRow, start, "Element not Present");
				takeScreenShotOnFailure("checkelementpresentFailed");
				System.out.println("Element not visible or not displayed on the webpage" +elementLocator);
			}
			 break; 
					
		case  "switchingchildwindow" :
			try {
			Set<String> allWindowHandles = driver.getWindowHandles();
			Iterator<String> iter=allWindowHandles.iterator();
			int size=allWindowHandles.size();
			String child = null;
			for(int i=0;i<size;i++){
				child=iter.next();
			}
			
			driver.switchTo().window(child);
			setExcelReports("Pass", statusRow, start, "switching child window passed");
			}
			catch(Throwable t) {
				setExcelReports("Fail", statusRow, start, "switching child window failed");
				takeScreenShotOnFailure("switchingchildwindowFailed");
				System.out.println("Element not visible or not displayed on the webpage" +elementLocator);
			}
			break;
			
			
		case  "switchingparentwindow" :
			try {
			Set<String> allWindowHandles1 = driver.getWindowHandles();
			String parent = (String) allWindowHandles1.toArray()[0];
			driver.switchTo().window(parent);
			setExcelReports("Pass", statusRow, start, "switching parent window passed");

			}
			catch(Throwable t) {
				setExcelReports("Fail", statusRow, start, "switching parent window Failed");
				takeScreenShotOnFailure("switchingparentwindowFailed");
				System.out.println("Element not visible or not displayed on the webpage" +elementLocator);
			}
			break;

		
		case "parentchildwindow":
			try {
			Set<String> allWindowHandles2 = driver.getWindowHandles();
			String window1 = (String) allWindowHandles2.toArray()[1];
			driver.switchTo().window(window1);
			setExcelReports("Pass", statusRow, start, "switching child parent window passed");
			}
			catch(Throwable t) {
				setExcelReports("Fail", statusRow, start, "switching child parent window Failed");
				takeScreenShotOnFailure("parentchildwindowFailed");
				System.out.println("Element not visible or not displayed on the webpage" +elementLocator);
			}
			break;
		
		case "dropdownwithtext":
			try {
			if(isElementPresent(driver, elementLocator)) {
				
			Select se = new Select(CommonMethod.findElement(driver, elementLocator));
			se.selectByVisibleText(value);
			log.info("Selecting dropdown failed.... " +elementLocator);
			test.log(LogStatus.PASS, " Selecting dropdown field Successfully "+elementLocator,test.addScreenCapture(takeScreenShot()));
			setExcelReports("Pass", statusRow, start, "dropdown with text passed");

			}
			}
			catch(Throwable t) {
				log.error("Selecting dropdown failed.... " +elementLocator);
				test.log(LogStatus.PASS, " Selecting dropdown field Failed "+elementLocator,test.addScreenCapture(takeScreenShot()));
				setExcelReports("Fail", statusRow, start, "dropdown with text failed");
				takeScreenShotOnFailure("dropdownwithtextFailed");
				System.out.println("Element not visible or not displayed on the webpage" +elementLocator);
			}
			break;
			
		case "dropdownwithindex":
			try {
			if(isElementPresent(driver, elementLocator)) {
				
			Select se = new Select(CommonMethod.findElement(driver, elementLocator));
			se.selectByIndex(Integer.parseInt(value));
			log.info("Selecting dropdown failed.... " +elementLocator);
			test.log(LogStatus.PASS, " Selecting dropdown field Successfully "+elementLocator,test.addScreenCapture(takeScreenShot()));
			setExcelReports("Pass", statusRow, start, "dropdown with index passed");

			}
			
			}
			catch(Throwable t) {
				log.error("Selecting dropdown failed.... " +elementLocator);
				test.log(LogStatus.PASS, " Selecting dropdown field Failed "+elementLocator,test.addScreenCapture(takeScreenShot()));
				setExcelReports("Fail", statusRow, start, "dropdown with text failed");
				takeScreenShotOnFailure("dropdownwithindexFailed");
				System.out.println("Element not visible or not displayed on the webpage" +elementLocator);
			}
			break;
		
		case "dropdownwithvalue":
			try {
			if(isElementPresent(driver, elementLocator)) {
				
			Select se = new Select(CommonMethod.findElement(driver, elementLocator));
			se.selectByValue(value);
			log.info("Selecting dropdown failed.... " +elementLocator);
			test.log(LogStatus.PASS, " Selecting dropdown field Successfully "+elementLocator,test.addScreenCapture(takeScreenShot()));
			setExcelReports("Pass", statusRow, start, "dropdown with value passed");

			}
			}
			catch(Throwable t) {
				log.error("Selecting dropdown filed.... " +elementLocator);
				test.log(LogStatus.PASS, " Selecting dropdown field Failed "+elementLocator,test.addScreenCapture(takeScreenShot()));
				setExcelReports("Fail", statusRow, start, "dropdown with value failed");
				takeScreenShotOnFailure("dropdownwithvalueFailed");
				System.out.println("Element not visible or not displayed on the webpage" +elementLocator);
			}
			break;
			
		case "mouseover":
			try {
				Thread.sleep(30000);
			Actions action = new Actions(driver);
			if(isElementPresent(driver, elementLocator)) {
				action.moveToElement(CommonMethod.findElement(driver, elementLocator));
				log.info("Hover on element .... " +elementLocator);
				test.log(LogStatus.PASS, " Hovering on element Successfully "+elementLocator,test.addScreenCapture(takeScreenShot()));
				setExcelReports("Pass", statusRow, start, "mousehover works");

				}
			}
			catch(Throwable t) {
				log.error("Hover on element failed.... " +elementLocator);
				test.log(LogStatus.PASS, " Hovering on element Failed "+elementLocator,test.addScreenCapture(takeScreenShot()));
				setExcelReports("Fail", statusRow, start, "mousehover failed");
				takeScreenShotOnFailure("mouseoverFailed");
				System.out.println("Element not visible or not displayed on the webpage" +elementLocator);
				System.out.println("Element not visible or not displayed on the webpage" +elementLocator);
			}
			  break;
		case "switchingframe":
				
			try {
				driver.switchTo().frame(value);
				log.info("Switching frame .... " +elementLocator);
				test.log(LogStatus.PASS, " Switching frame Successfully "+elementLocator,test.addScreenCapture(takeScreenShot()));
				setExcelReports("Pass", statusRow, start, "switching frame passed");
				}
				catch (Throwable t) {
				log.error("Alert on element failed.... " +elementLocator);
				test.log(LogStatus.PASS, " Alert on element Failed "+elementLocator,test.addScreenCapture(takeScreenShot()));
				setExcelReports("Fail", statusRow, start, "switching frame failed");

				}
			  break;
			  
		case "acceptingalert":
			
			try {
				Alert alert = driver.switchTo().alert();
				alert.accept();
				log.info("Alert on element .... " +elementLocator);
				test.log(LogStatus.PASS, " Alert on element Successfully "+elementLocator,test.addScreenCapture(takeScreenShot()));
				setExcelReports("Pass", statusRow, start, "accepting alert passed");

				}
				catch (Throwable t) {
				log.error("Alert on element failed.... " +elementLocator);
				test.log(LogStatus.PASS, " Alert on element Failed "+elementLocator,test.addScreenCapture(takeScreenShot()));
				setExcelReports("Fail", statusRow, start, "accepting alert  failed");
				}
			  break;
			  
		case "dismissalert":
			
			try {
				Alert alert1 = driver.switchTo().alert();
				alert1.dismiss();
				log.info("Alert on element .... " +elementLocator);
				test.log(LogStatus.PASS, " Alert on element Successfully "+elementLocator,test.addScreenCapture(takeScreenShot()));
				setExcelReports("Pass", statusRow, start, "dismiss alert passed");

				}
				catch (Throwable t) {
				log.error("Alert on element failed.... " +elementLocator);
				test.log(LogStatus.PASS, " Alert on element Failed "+elementLocator,test.addScreenCapture(takeScreenShot()));
				setExcelReports("Fail", statusRow, start, "dismiss alert failed");
				}
			  break;
		
		
		case "comparealerttext":
			String actual4 = null;
			try {
				
			
			Alert alert3 = driver.switchTo().alert();
			
			 actual4 = alert3.getText();

			 if(actual4.equals(expected)){
					log.info("Actual value......"+actual4+ "..... is  matching with expected value...."+expected+".......");
					test.log(LogStatus.PASS, actual4+ "..... is  matching with expected value...."+expected,test.addScreenCapture(takeScreenShot()));
					setExcelReports("Pass", statusRow, start, "compare alert text passed");			}
			}
			catch (Throwable t) {
				{
				    log.error("Actual value......"+actual4+ "..... is  not matching with expected value...."+expected+".......");
					test.log(LogStatus.FAIL, actual4+ "..... is  not matching with expected value...."+expected,test.addScreenCapture(takeScreenShot()));
					setExcelReports("Fail", statusRow, start, "compare alert text Failed");				
					}
			 break;
			}
			
		
		case "uploadfile":
			try {
			if(isElementPresent(driver, elementLocator)) {
				
				CommonMethod.findElement(driver,elementLocator).sendKeys(value);
				log.info("upload file to the field...." +elementLocator);
				test.log(LogStatus.PASS, " upload file  to the element Successfully "+elementLocator,test.addScreenCapture(takeScreenShot()));
				setExcelReports("Pass", statusRow, start, "upload file passed");			}
			}
			catch(Throwable t) {
				log.error("upload file to the field...." +elementLocator);
				test.log(LogStatus.FAIL, " upload file to the element Failed "+elementLocator,test.addScreenCapture(takeScreenShot()));
				setExcelReports("Fail", statusRow, start, "upload file failed");	
				takeScreenShotOnFailure("uploadfileFailed");
				System.out.println("Element not visible or not displayed on the webpage" +elementLocator);
			}
			break;	
			
		case "normalsleep":
			Thread.sleep(10);
			break;
		
		
		case "mediumsleep":
			Thread.sleep(20);
			break;
		
		case "Highsleep":
			Thread.sleep(30);
			break;
		
	}
		}
		catch(Throwable t) {
			
			System.out.println(t.getMessage());
		}
	}
	
	/*public static void setExcelReports(String status, int statusRow,long start,String elementStatus) throws IOException {
		
		setReports("objects", "Status", statusRow, status);	
		setReports("objects", "Report", statusRow, elementStatus);
		long end = System.currentTimeMillis();
		String totalTIme = end-start+" milli Secs";
		setReports("objects", "ExecutionTime", statusRow, totalTIme);
	}
*/
	public static  void  takeScreenShotOnFailure(String screenshotName) throws IOException{
		
		Date d = new Date();
		Timestamp t = new Timestamp(d.getTime());
		String timeStamp = t.toString();
		timeStamp = timeStamp.replace(' ', '_');
		timeStamp = timeStamp.replace(':', '_'); 
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"/Screenshots/FailedScreenshots/"+screenshotName+timeStamp+".jpg"));
	}
     
	public static  String  takeScreenShot() throws IOException{
		
		Date d = new Date();
		Timestamp t = new Timestamp(d.getTime());
		String timeStamp = t.toString();
		timeStamp = timeStamp.replace(' ', '_');
		timeStamp = timeStamp.replace(':', '_'); 
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"/Screenshots/"+timeStamp+".jpg"));
	    String path=System.getProperty("user.dir")+"/Screenshots/"+timeStamp+".jpg";
	    
		return path;		
		
	}
	
	public static boolean isElementPresent(WebDriver driver, final  String elementLocator) throws IOException  {
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	  
		if(CommonMethod.findElements(driver,elementLocator).size()>0){
			return true;
			
		}
		else{
			return false;
		}	   		
}

	

	
}
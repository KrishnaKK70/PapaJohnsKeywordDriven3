package com.framework.keywords;

import java.io.IOException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.framework.platforms.Browsers;
import com.framework.utilities.ExcelReader;

import static com.framework.reports.Reports.*;

public class RunTestCases extends Browsers{
	
public static String Filepath = "./src/main/resources/input.xlsx";
public static String SheetName = "objects";
// Read the XL report
// To read the column values in XL using while loop

public static void run() throws IOException{
	int a=2;
	
	while(true)
	{
	ExcelReader data = new ExcelReader(Filepath);
	String TestcaseName = data.getCellData(SheetName, "Testcase Name", a);
	String actioncoloumn = data.getCellData(SheetName, "Action", a);
	String locator = data.getCellData(SheetName, "Locator", a);
	String testdata = data.getCellData(SheetName, "TestData", a);
	String end = data.getCellData(SheetName, "Testcase id", a);
	String expected = data.getCellData(SheetName, "Expected Data", a);
	String readyTest = data.getCellData(SheetName, "Ready to test", a);
	

	if(end.equalsIgnoreCase("end")){
		break;
	}
	if(readyTest.equalsIgnoreCase("yes")){
		Keywords.keyword(TestcaseName,actioncoloumn, locator, testdata, expected, a);
	
	}

	else{
	
	if(!readyTest.isEmpty() && readyTest.length()!=0){
		setReports("objects", "Report", a, "Not Executed");
	}
	}
	a++;	
	}
	
}

public static void exit() throws  IOException{
	Browsers.driver.quit();
	System.out.println("******Testcases Completed******");	
}

// Main functions

@Test
public void TestExecution() throws IOException, InterruptedException  {
	setup();
	run();
    exit();
	
	}				
    

	
}
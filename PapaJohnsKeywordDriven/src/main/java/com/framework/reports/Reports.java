package com.framework.reports;

import static com.framework.platforms.Browsers.*;
import static com.framework.reports.Reports.setReports;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.framework.utilities.ExcelReader;


public class Reports {
	
	
	
	
	public static void setReports(String sheetName, String colName, int rowNo, String xlData) throws IOException {
		
		/*FileOutputStream fos=null;
		
		try{
			setwbook=new XSSFWorkbook(oFilepath);
		
		sheet =setwbook.getSheet(sheetName);
		if(sheet.getRow(rowNo)==null){
			Cell setcel=sheet.createRow(rowNo).createCell(columnNo);
			setcel.setCellValue(xlData);
		}
		else{
		Cell setcel=sheet.getRow(rowNo).createCell(columnNo);
		setcel.setCellValue(xlData);
		}
		fos=new FileOutputStream(setwbook.getSheet(sheetName).getSheetName());
		setwbook.write(fos);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	finally{
		
		fos.flush();
		fos.close();
		setwbook.close();
	}*/
		
		ExcelReader dataset = new ExcelReader(oFilepath);
		dataset.setCellData(sheetName, colName, rowNo, xlData);
		
	}	
	
	public static void setExcelReports(String status, int statusRow,long start,String elementStatus) throws IOException {
		
		setReports("objects", "Status", statusRow, status);	
		setReports("objects", "Report", statusRow, elementStatus);
		long end = System.currentTimeMillis();
		String totalTIme = (end-start)/1000+" Secs";
		setReports("objects", "ExecutionTime", statusRow, totalTIme);
	}

	
	
}

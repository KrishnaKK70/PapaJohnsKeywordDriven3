package com.framework.utilities;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.*;
import java.io.*;

public class ExcelReader 
{
 public static String filename = System.getProperty("user.dir");
 public  String path;
 public  FileInputStream fis = null;
 public  FileOutputStream fileOut =null;
 private XSSFWorkbook workbook = null;
 private XSSFSheet sheet = null;
 private XSSFRow row   =null;
 private XSSFCell cell = null;
 public static String sActionKeyword=null;

  //constructor for path setting
 public ExcelReader(String path) 
 {
  this.path=path;
  try 
  {
   fis = new FileInputStream(path);
   workbook = new XSSFWorkbook(fis);
   sheet = workbook.getSheetAt(0);
   fis.close();
  }
  catch (Exception e) 
  {
   e.printStackTrace();
  } 
  
 }

  // returns the data from a cell based on row and column name
 public String getCellData(String sheetName,String colName,int rowNum)
 {
  try
  {
   if(rowNum <=0)
    return "";
  
  int index = workbook.getSheetIndex(sheetName);
  int col_Num=-1;
  if(index==-1)
   return "";
  
  sheet = workbook.getSheetAt(index);
  row=sheet.getRow(0);
  for(int i=0;i<row.getLastCellNum();i++)
  {
   //System.out.println(row.getCell(i).getStringCellValue().trim());
   if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
    col_Num=i;
  }
  if(col_Num==-1)
   return "";
  
  sheet = workbook.getSheetAt(index);
  row = sheet.getRow(rowNum-1);
  if(row==null)
   return "";
  cell = row.getCell(col_Num);
  
  if(cell==null)
   return "";
  //System.out.println(cell.getCellType());
  if(cell.getCellType()==Cell.CELL_TYPE_STRING)
     return cell.getStringCellValue();
  else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC || cell.getCellType()==Cell.CELL_TYPE_FORMULA )
  {
     
      String cellText  = String.valueOf(cell.getNumericCellValue());
    
  return cellText;
    }
  else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
        return ""; 
    else 
     return String.valueOf(cell.getBooleanCellValue());
  
  }
  catch(Exception e)
  {
   
   e.printStackTrace();
   return "row "+rowNum+" or column "+colName +" does not exist in xls";
  }
 }
 // returns true if data is set successfully into cell using column name and row number
 public boolean setCellData(String sheetName,String colName,int rowNum, String data)
 {
  try
  {
  fis = new FileInputStream(path); 
  workbook = new XSSFWorkbook(fis);

   if(rowNum<=0)
   return false;
  
  int index = workbook.getSheetIndex(sheetName);
  int colNum=-1;
  if(index==-1)
   return false;
  sheet = workbook.getSheetAt(index);

   row=sheet.getRow(0);
  for(int i=0;i<row.getLastCellNum();i++)
  {
   //System.out.println(row.getCell(i).getStringCellValue().trim());
   if(row.getCell(i).getStringCellValue().trim().equals(colName))
    colNum=i;
  }
  if(colNum==-1)
   return false;

   sheet.autoSizeColumn(colNum); 
  row = sheet.getRow(rowNum-1);
  if (row == null)
   row = sheet.createRow(rowNum-1);
  
  cell = row.getCell(colNum); 
  if (cell == null)
         cell = row.createCell(colNum);

      
     cell.setCellValue(data);

      fileOut = new FileOutputStream(path);

   workbook.write(fileOut);

      fileOut.close(); 

   }
  catch(Exception e)
  {
   e.printStackTrace();
   return false;
  }
  return true;
 } 
 
}

















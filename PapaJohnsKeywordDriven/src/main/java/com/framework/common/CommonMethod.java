package com.framework.common;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.framework.platforms.Browsers;

public class CommonMethod extends Browsers {

	public static WebElement findElement(WebDriver driver, String objectLocator){
			
			try{
			 String[] objects = objectLocator.split("~");
			 String objectType = objects[0];
			 String objectValue = objects[1];
			 WebDriverWait wait=new WebDriverWait(driver,30);
			 
			 if(objectType.equalsIgnoreCase("id")) {
				 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(objectValue)));
				 return driver.findElement(By.id(objectValue));
			 }
			 else if(objectType.equalsIgnoreCase("name")) {
				 wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(objectValue)));
				 return driver.findElement(By.name(objectValue));
			 }
			 else if(objectType.equalsIgnoreCase("xpath")) {
				 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(objectValue)));
				 return driver.findElement(By.xpath(objectValue));
			 }
			 else if(objectType.equalsIgnoreCase("class")) {
				 wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(objectValue)));
				 return driver.findElement(By.className(objectValue));
			 }
			 else if(objectType.equalsIgnoreCase("css")) {
				 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(objectValue)));
				 return driver.findElement(By.cssSelector(objectValue));
			 }
			 else if(objectType.equalsIgnoreCase("link")) {
				 wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(objectValue)));
				 return driver.findElement(By.linkText(objectValue));
			 }
			}catch(Exception e){
				System.out.println(e.getLocalizedMessage());
				System.out.println(e.getStackTrace());
			}
			return null;
			
		}
		
	public static List<WebElement> findElements(WebDriver driver, String objectLocator){
		
		try{
		 String[] objects = objectLocator.split("~");
		 String objectType = objects[0];
		 String objectValue = objects[1];
		 WebDriverWait wait=new WebDriverWait(driver,30);
		 
		 if(objectType.equalsIgnoreCase("id")) {
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(objectValue)));
			 return driver.findElements(By.id(objectValue));
		 }
		 else if(objectType.equalsIgnoreCase("name")) {
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(objectValue)));
			 return driver.findElements(By.name(objectValue));
		 }
		 else if(objectType.equalsIgnoreCase("xpath")) {
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(objectValue)));
			 return driver.findElements(By.xpath(objectValue));
		 }
		 else if(objectType.equalsIgnoreCase("class")) {
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(objectValue)));
			 return driver.findElements(By.className(objectValue));
		 }
		 else if(objectType.equalsIgnoreCase("css")) {
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(objectValue)));
			 return driver.findElements(By.cssSelector(objectValue));
		 }
		 else if(objectType.equalsIgnoreCase("link")) {
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(objectValue)));
			 return driver.findElements(By.linkText(objectValue));
		 }
		}catch(Exception e){
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getStackTrace());
		}
		return null;
		
	}
	
	
}

package com.automation.utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;

@Getter
public class WebUtil {
private WebDriver driver;
ExtentTest extTest ;
	public void lounchBrowser(String browsername) {
		if(browsername.equalsIgnoreCase("chrome")) {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		extTest.log(Status.INFO, "Browser Launch Successfully Browser is : "+browsername);
		}
		else if(browsername.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			extTest.log(Status.INFO, "Browser Launch Successfully Browser is : "+browsername);
			}
		
	}
	
	
	public void setExtentTestObject(ExtentTest extTest) {
		this.extTest=extTest;
	}
	
	public void hitUrl() {
		driver.get("https://www.saucedemo.com/v1/");
		extTest.log(Status.INFO, " URL Hit Successfully url is : "+"https://www.saucedemo.com/v1/" );

	}
	public void click(WebElement we) {
		we.click();
		extTest.log(Status.INFO, "Click perform Successfully on : "+ we.getAccessibleName());

	}
	
	public void sendkeys(WebElement we,String value) {
		we.sendKeys(value);
		extTest.log(Status.INFO, "Enter Value Successfully and value is : "+value);
	}
	
	public void clear(WebElement we) {
		we.clear();
		extTest.log(Status.INFO, "Clear Text Field Successfully field is : "+we.getAccessibleName());
	}
	
	public String getText(WebElement we) {
		String textValue = we.getText();
		extTest.log(Status.INFO, "Featched and Returing Element Text Value Successfully and Text Value is : "+textValue);
		return textValue; 
	}
	
	public void tearDown() {
		driver.quit();
		extTest.log(Status.INFO, "Browser Close Successfully");
	}
	
	public String takeSnapshot(String testCaseName) {
		TakesScreenshot tss =(TakesScreenshot) driver;
		File tempFileLoc = tss.getScreenshotAs(OutputType.FILE);
		DateFormat df = new SimpleDateFormat("MM--dd--yyyy hh_mm_ss a");
	    String timeStamp =	df.format(new Date());
		File actualFileLoc = new File("snapshots\\"+testCaseName+timeStamp);
		try {
			Files.copy(tempFileLoc, actualFileLoc);
		} catch (IOException e) {
					e.printStackTrace();
		}
		
		return	actualFileLoc.getAbsolutePath();

	}
}

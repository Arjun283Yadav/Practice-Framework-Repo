package com.automation.utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.automation.webdrivermenegar.WebDriverManagerUtil;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.google.common.io.Files;

import lombok.Getter;

@Getter
public class WebUtil {
	
	private ExtentTest extTest = GenricReport_Utility.getExtentTest();

	 // No WebDriver instance variable - use thread-local WebDriver from WebDriverManagerUtil
    public void launchBrowser(String browserName) {
        WebDriverManagerUtil.initializeDriver(browserName);
        extTest.log(Status.INFO, "Browser Launched Successfully: " + browserName);
    }

    // Fetch WebDriver instance from WebDriverManagerUtil
    public WebDriver getDriver() {
        return WebDriverManagerUtil.getDriver();
    }

//    public void setExtentTestObject(ExtentTest extTest) {
//        this.extTest = extTest;
//    }

    public void hitUrl() {
        getDriver().get("https://www.saucedemo.com/v1/");
        extTest.log(Status.INFO, "URL Hit Successfully : " + "https://www.saucedemo.com/v1/");
    }
    
    public void click(WebElement we) {
        try {
        	WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(we));

            // Fetch element details BEFORE clicking
            String elementName = clickableElement.getAccessibleName();  

            clickableElement.click();
            
            // Log success immediately after clicking
            extTest.log(Status.INFO, "Click performed successfully on: " + elementName);
        } catch (Exception e) {
            extTest.log(Status.FAIL, "Click failed: " + e.getMessage());
            Assert.fail("Test Failed due to: " + e.getMessage());
        }
    }
	
    public void sendKeys(WebElement we, String text) {
        try {
            we.sendKeys(text);
            extTest.log(Status.INFO, "Text entered: " + text);
        } catch (Exception e) {
            extTest.log(Status.FAIL, "SendKeys failed: " + e.getMessage());
        }
    }
    
	public void clear(WebElement we) {
		try {
            we.clear();
            extTest.log(Status.INFO, "Text clear: " + we.getAccessibleName());
        } catch (Exception e) {
            extTest.log(Status.FAIL, "clear failed: " + e.getMessage());
        }
	}
	
	public String getText(WebElement we) {
		String textValue = null;
		try {
           textValue = we.getText();
   		extTest.log(Status.INFO, "Featched and Returing Element Text Value Successfully and Text Value is : "+textValue);
        } catch (Exception e) {
    		extTest.log(Status.FAIL, "Unable to Featched and Returing Element Text " + e.getMessage());

        }
		return textValue; 
	}
	
	public String takeSnapshot(String testCaseName) {
		TakesScreenshot tss =(TakesScreenshot) getDriver();
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

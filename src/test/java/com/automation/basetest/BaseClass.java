package com.automation.basetest;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.automation.utils.GenricReport_Utility;
import com.automation.utils.WebUtil;
import com.automation.webdrivermenegar.WebDriverManagerUtil;
import com.aventstack.extentreports.ExtentTest;

public class BaseClass {
	private ExtentTest extTest;
	protected WebUtil we;

	@BeforeSuite
	 public void beforeSuite() {
	        GenricReport_Utility.generateReport(); // Run only once before all tests
	    }
	
	@BeforeMethod
	public void beforeMathod(Method mt) {
		extTest = GenricReport_Utility.createTest(mt.getName());
		GenricReport_Utility.setExtentTest(extTest);
		we = new WebUtil();
		we.launchBrowser("chrome");
		we.hitUrl();

	}

	@AfterMethod
	public void afterMathad(ITestResult result, Method mt) throws InterruptedException {
		if (result.getStatus()==result.FAILURE) {
			String snapshotPath = we.takeSnapshot(mt.getName());
			extTest.addScreenCaptureFromPath(snapshotPath);
		}
		GenricReport_Utility.flush();
		 WebDriverManagerUtil.quitDriver();
	}

	@AfterSuite
	public void afterClass() {
		GenricReport_Utility.flush();
		// extent html report will automaticatly open after complate exceution
		try {
			File htmlFile = new File("reports/TestExecutionReport.html");
			if (htmlFile.exists()) {
				Desktop.getDesktop().browse(htmlFile.toURI());
			} else {
				System.out.println("Report file not found: " + htmlFile.getAbsolutePath());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// extent html report
				try {
				File htmlFile = new File("reports/FaildTestExecutionReport.html");
				if (htmlFile.exists()) {
				Desktop.getDesktop().browse(htmlFile.toURI());
				} else {
				System.out.println("Report file not found: " + htmlFile.getAbsolutePath());
				} 
				}catch (IOException e) {
				e.printStackTrace();
				}
	}
}

package com.automation.basetest;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.automation.utils.GenricReport_Utility;
import com.automation.utils.WebUtil;
import com.aventstack.extentreports.ExtentTest;

public class BaseClass {
	private ExtentTest extTest;
	protected WebUtil we;

	@BeforeClass
	public void beforeClass() {
		GenricReport_Utility.generateReport();
	}

	@BeforeMethod
	public void beforeMathod(Method mt) {
		extTest = GenricReport_Utility.getExtentTest(mt.getName());
		we = new WebUtil();
		we.setExtentTestObject(extTest);
		we.lounchBrowser("chrome");
		we.hitUrl();

	}

	@AfterMethod
	public void afterMathad(ITestResult result, Method mt) throws InterruptedException {
		if (result.getStatus()==result.FAILURE) {
			String snapshotPath = we.takeSnapshot(mt.getName());
			extTest.addScreenCaptureFromPath(snapshotPath);
		}
		we.tearDown();
	}

	@AfterClass
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

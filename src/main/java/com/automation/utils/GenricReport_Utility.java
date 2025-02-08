package com.automation.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class GenricReport_Utility {
	public static ExtentReports ext;
	public static ExtentSparkReporter extspark;
	public static ExtentSparkReporter faildextspark;
	
	public static ExtentReports generateReport() {
//		String timeStamp = new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());
//		extspark=new ExtentSparkReporter("reports/"+timeStamp+"simplehtmlreport");
		extspark=new ExtentSparkReporter("reports\\"+"TestExecutionReport.html");
		faildextspark=new ExtentSparkReporter("reports\\"+"FaildTestExecutionReport.html")
					  .filter().statusFilter().as(new Status [] {Status.FAIL}).apply();
		
		faildextspark.config().setDocumentTitle("Faild Test Report");
		ext=new ExtentReports();
		extspark.config().setTheme(Theme.DARK);
		extspark.config().setDocumentTitle("Extent Report Demo");
		ext.attachReporter(extspark,faildextspark);
		return ext;
		}
	
	public static ExtentTest getExtentTest(String testName) {
		ExtentTest extTest = ext.createTest(testName)
							 .assignAuthor("Arjun")
							 .assignCategory("Smoke")
							 .assignDevice("Window Chrome 131");
		return extTest;
	}
	
	public static void flush() {
		ext.flush();
	}
}

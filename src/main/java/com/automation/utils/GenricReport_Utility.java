package com.automation.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class GenricReport_Utility {
    private static ExtentReports ext;
    private static ExtentSparkReporter extspark;
    private static ExtentSparkReporter failedExtSpark;
    
    // ThreadLocal to store ExtentTest per thread
    private static ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();

    // Generate Extent Reports
    public static synchronized ExtentReports generateReport() {
        extspark = new ExtentSparkReporter("reports\\" + "TestExecutionReport.html");
        failedExtSpark = new ExtentSparkReporter("reports\\" + "FailedTestExecutionReport.html")
                .filter().statusFilter().as(new Status[]{Status.FAIL}).apply();

        failedExtSpark.config().setDocumentTitle("Failed Test Report");
        extspark.config().setTheme(Theme.DARK);
        extspark.config().setDocumentTitle("Extent Report Demo");

        ext = new ExtentReports();
        ext.attachReporter(extspark, failedExtSpark);

        return ext;
    }

    // Create a test and assign it to ThreadLocal
    public static synchronized ExtentTest createTest(String testName) {
        ExtentTest extTest = ext.createTest(testName)
                .assignAuthor("Arjun")
                .assignCategory("Smoke")
                .assignDevice("Windows Chrome 131");

        // Store in ThreadLocal for parallel execution
        extentTestThreadLocal.set(extTest);

        return extTest;
    }
    
    // Explicitly set ExtentTest in ThreadLocal (for Listeners)
    public static synchronized void setExtentTest(ExtentTest extTest) {
        extentTestThreadLocal.set(extTest);
    }

    // Get the current test from ThreadLocal
    public static synchronized ExtentTest getExtentTest() {
        return extentTestThreadLocal.get();
    }

    // Flush reports after execution
    public static synchronized void flush() {
        if (ext != null) {
            ext.flush();
        }
    }
}


//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.configuration.Theme;
//
//public class GenricReport_Utility {
//	public static ExtentReports ext;
//	public static ExtentSparkReporter extspark;
//	public static ExtentSparkReporter faildextspark;
//	public static ExtentTest extTest;
//	
//	public static ExtentReports generateReport() {
////		String timeStamp = new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());
////		extspark=new ExtentSparkReporter("reports/"+timeStamp+"simplehtmlreport");
//		extspark=new ExtentSparkReporter("reports\\"+"TestExecutionReport.html");
//		faildextspark=new ExtentSparkReporter("reports\\"+"FaildTestExecutionReport.html")
//					  .filter().statusFilter().as(new Status [] {Status.FAIL}).apply();
//		
//		faildextspark.config().setDocumentTitle("Faild Test Report");
//		ext=new ExtentReports();
//		extspark.config().setTheme(Theme.DARK);
//		extspark.config().setDocumentTitle("Extent Report Demo");
//		ext.attachReporter(extspark,faildextspark);
//		return ext;
//		}
//	
//	public static ExtentTest createTest(String testName) {
//		ExtentTest extTest = ext.createTest(testName)
//							 .assignAuthor("Arjun")
//							 .assignCategory("Smoke")
//							 .assignDevice("Window Chrome 131");
//		return extTest;
//	}
//	
//	public static void setExtentTest(ExtentTest extTest) {
//		GenricReport_Utility.extTest = extTest;
//	}
//	
//	public static ExtentTest getExtentTest() {
//		return extTest;
//	}
//	
//	public static void flush() {
//		ext.flush();
//	}
//}

package com.automation.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import static com.automation.constant.FrameworkConstants.ICON_BUG;
import static com.automation.constant.FrameworkConstants.ICON_SMILEY_PASS;
import static com.automation.constant.FrameworkConstants.ICON_SMILEY_SKIP;

import java.util.Arrays;

import static com.automation.constant.FrameworkConstants.ICON_SMILEY_FAIL;

public class TestListeners implements ITestListener{
	
	static int count_passedTCs;
	static int count_skippedTCs;
	static int count_failedTCs;
	static int count_totalTCs;

	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getMethod().getMethodName();

	    // Create and set the ExtentTest instance for the current test
//	    ExtentTest test = GenricReport_Utility.createTest(testName);
//	    GenricReport_Utility.setExtentTest(test);

	    // Log the test start information
	    GenricReport_Utility.getExtentTest().info("Test Started: " + testName);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		count_passedTCs = count_passedTCs + 1;
		String logText = "<b>" + result.getMethod().getMethodName() + " is passed.</b>" + "  " + ICON_SMILEY_PASS;
		Markup markup_message = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		GenricReport_Utility.getExtentTest().pass(markup_message);

	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		String testName = result.getMethod().getMethodName();
	    ExtentTest test = GenricReport_Utility.getExtentTest();
	    
	    // Prevent duplicate failure logs
	    if (test.getStatus().equals(Status.FAIL)) {
	        return; // Do nothing if already marked as FAIL
	    }

	    count_failedTCs++;

	    test.fail(ICON_BUG + "  " + "<b><i>" + result.getThrowable().toString() + "</i></b>");

	    String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
	    String message = "<details><summary><b><font color=red> Exception occurred, click to see details: "
	            + ICON_SMILEY_FAIL + " </font></b>" + "</summary>" + exceptionMessage.replaceAll(",", "<br>")
	            + "</details> \n";

	    test.fail(message);

	    String logText = "<b>" + testName + " is failed.</b> " + ICON_SMILEY_FAIL;
	    Markup markup_message = MarkupHelper.createLabel(logText, ExtentColor.RED);
	    test.fail(markup_message);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		count_skippedTCs = count_skippedTCs + 1;

		GenricReport_Utility.getExtentTest().skip(ICON_BUG + "  " + "<b><i>" + result.getThrowable().toString() + "</i></b>");
		String logText = "<b>" + result.getMethod().getMethodName() + " is skipped.</b>" + "  " + ICON_SMILEY_SKIP;
		Markup markup_message = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		GenricReport_Utility.getExtentTest().skip(markup_message);
	}

	@Override
	public void onStart(ITestContext context) {
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		count_totalTCs++;
		ITestListener.super.onFinish(context);
		GenricReport_Utility.flush();
	}
	

}

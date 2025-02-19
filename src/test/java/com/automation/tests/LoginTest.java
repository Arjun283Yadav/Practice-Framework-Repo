package com.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.automation.basetest.BaseClass;
import com.automation.pages.LoginPage;
import com.automation.utils.ReadExcelFile;

@Listeners(com.automation.utils.TestListeners.class)
public class LoginTest extends BaseClass {
	@Test(description = "Verifying login in application with valid details")
	public void verifyValidLogin() {
		LoginPage lp = new LoginPage(we);
		lp.validLogin();
	}
	
	public Object[][] getDataList() {

		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData_for_DataDriven_Without_DataProvider.xlsx";
		int rowCount = ReadExcelFile.getRowCount(filePath, "sheet1");
		int colCount = ReadExcelFile.getColoumCount(filePath, "sheet1");

		Object[][] dataArray = new Object[rowCount-1][colCount];

		for (int i = 1; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {

				dataArray[i - 1][j] = ReadExcelFile.getCellValue(filePath, "sheet1", i, j);
			}
		}
		return dataArray;
	}
	
	@Test(description = "Verifying Invalid Login in application with Data Driven Approach")
	public void verifyInvalidLoginByDataDriven() {
		Object[][] dataArray = getDataList();
		for (int i = 0; i < getDataList().length; i++) {
			Object[] singleSetData = dataArray[i];
			LoginPage login = new LoginPage(we);
			login.validLoginDataDriven(singleSetData);
			String expErrorMsg = "Epic sadface: Username and password do not match any user in this service";
			String actErrorMsg = login.getErrorMassage();
			Assert.assertEquals(expErrorMsg, actErrorMsg, "Error Msg is Not Matched ... ");
		}
	}
	
	@Test(description = "verifying Invalid login in application")
	public void verifyInvalidLogin() {
		LoginPage loginPage = new LoginPage(we);
		loginPage.invalidLogin();
		String expErrorMsg = "Epic sadface  Username and password do not match any user in this service";
		String actErrorMsg = loginPage.getErrorMassage();
		Assert.assertEquals(actErrorMsg, expErrorMsg, "Error Msg is Not Matched ... ");
		
	}
}

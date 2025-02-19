package com.automation.pages;

import com.automation.utils.WebUtil;

public class LoginPage extends LoginPage_Or{
	WebUtil we;
	
	public LoginPage(WebUtil we) {
		super(we);
		this.we = we;
	}
	
	public void validLogin() {
	 
	 we.sendKeys(getUserName(), "problem_user");
	 we.sendKeys(getPassword(), "secret_sauce");
	 we.click(getLoginButton());

	}
	
	public void validLoginDataDriven(Object[] dataArray) {
		 
		 we.clear(getUserName());
		 we.sendKeys(getUserName(), (String)dataArray[0]);
		 
		 we.clear(getPassword());
		 we.sendKeys(getPassword(), (String)dataArray[1]);
		 
		 we.click(getLoginButton());

		}
	
	public void invalidLogin() {
		we.sendKeys(getUserName(), "problem_user123");
		we.sendKeys(getPassword(), "secret_sauce12345");
		we.click(getLoginButton());
	}
	
	public String getErrorMassage(){
		
		return we.getText(getErrorMsg());
		
	}
}

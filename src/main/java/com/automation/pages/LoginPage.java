package com.automation.pages;

import com.automation.utils.WebUtil;

public class LoginPage extends LoginPage_Or{
	WebUtil we;
	
	public LoginPage(WebUtil we) {
		super(we);
		this.we = we;
	}
	
	public void validLogin() {
	 
	 we.sendkeys(getUserName(), "problem_user");
	 we.sendkeys(getPassword(), "secret_sauce");
	 we.click(getLoginButton());

	}
	
	public void validLoginDataDriven(Object[] dataArray) {
		 
		 we.clear(getUserName());
		 we.sendkeys(getUserName(), (String)dataArray[0]);
		 
		 we.clear(getPassword());
		 we.sendkeys(getPassword(), (String)dataArray[1]);
		 
		 we.click(getLoginButton());

		}
	
	public void invalidLogin() {
		we.sendkeys(getUserName(), "problem_user123");
		we.sendkeys(getPassword(), "secret_sauce12345");
		we.click(getLoginButton());
	}
	
	public String getErrorMassage(){
		
		return we.getText(getErrorMsg());
		
	}
}

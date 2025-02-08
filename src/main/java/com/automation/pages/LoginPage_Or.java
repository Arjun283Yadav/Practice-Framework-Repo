package com.automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.utils.WebUtil;

import lombok.Getter;

@Getter
public class LoginPage_Or {

	public LoginPage_Or(WebUtil we) {
		PageFactory.initElements(we.getDriver(), this);
	}
	
	@FindBy(xpath = "//input[@id='user-name']")
	private WebElement userName;

	@FindBy(xpath = "//input[@id='password']")
	private WebElement password;

	@FindBy(xpath = "//input[@id='login-button']")
	private WebElement loginButton;
	
	@FindBy(xpath = "//h3[@data-test='error']")
	private WebElement errorMsg;
}

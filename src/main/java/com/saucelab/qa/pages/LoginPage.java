package com.saucelab.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.saucelab.qa.base.BasePage;

public class LoginPage extends BasePage{

	// Page Objects
	private By username=By.id("user-name");
	private By password=By.id("password");
	private By loginBtn=By.id("login-button");
	
	// Page Constructor
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	
	// Page Actions 
	public String getLoginPageTitle() {
		return getPageTitle();
	}

	public ProductHomePage doLogin(String user,String pwd) {
		writeText(username, user);
		writeText(password, pwd);
		click(loginBtn);
		
		return new ProductHomePage(driver);
	}
}

package com.saucelab.qa.base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.saucelab.qa.util.TestUtil;

public abstract class Page {

	public WebDriver driver;
	public WebDriverWait wait;
	
	
	
	//Constructor -- driver initialization 
	public Page(WebDriver driver) {
		this.driver=driver;
		this.wait=new WebDriverWait(this.driver, Duration.ofSeconds(TestUtil.WEBDRIVER_WAIT));
		
	}
	
	//abstract Methods
	
	public abstract String getPageTitle();
	
	public abstract WebElement getElement(By locator);
	
	public abstract void waitForElementPresent(By locator);
	
	public abstract void waitForVisibility(By locator);
	
	public abstract void click(By locator);
	
	public abstract void writeText(By locator,String text);
	
	public abstract String readText(By locator);
}

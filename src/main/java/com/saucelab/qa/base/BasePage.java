package com.saucelab.qa.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasePage extends Page{

	public BasePage(WebDriver driver) {
		super(driver);
		
	}

	@Override
	public String getPageTitle() {
		return driver.getTitle();
	}

	@Override
	public WebElement getElement(By locator) {
		WebElement element=null;
		try {
			element=driver.findElement(locator);
		} catch (Exception e) {
			System.out.println("some error occured while finding element :"+locator.toString());
			e.printStackTrace();
		}
		return element;
	}

	@Override
	public void waitForElementPresent(By locator) {

		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch (Exception e) {
			System.out.println("some error occured while waiting for the element present : "+ locator.toString());
			e.printStackTrace();
		}
	}

	@Override
	public void waitForVisibility(By locator) {
		try {
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		} catch (Exception e) {
			System.out.println("some error occured while waiting for the element visible : "+ locator.toString());
			e.printStackTrace();
		}		
	}

	@Override
	public void click(By locator) {
		waitForVisibility(locator);
		getElement(locator).click();
	}

	@Override
	public void writeText(By locator, String text) {
		waitForVisibility(locator);
		getElement(locator).clear();
		getElement(locator).sendKeys(text);
	}

	@Override
	public String readText(By locator) {
		waitForVisibility(locator);
		return getElement(locator).getText();
	}

}

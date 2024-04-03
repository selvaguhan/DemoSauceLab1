package com.saucelab.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.saucelab.qa.base.BasePage;
import com.saucelab.qa.util.TestUtil;

public class CheckoutPage extends BasePage {

	// Page Objects
	private By pageHeader = By.xpath(TestUtil.SECONDARY_PAGE_HEADER);
	
	private By firstname = By.name("firstName");
	private By lastName = By.name("lastName");
	private By postalCode = By.name("postalCode");
	
	private By continueBtn = By.id("continue");

	// Page Constructor
	public CheckoutPage(WebDriver driver) {
		super(driver);
	}

	// Page Actions
	// Checkout: Your Information
	public String getPageHeader() {
		return readText(pageHeader);
	}

	public CheckoutOverviewPage enterCheckoutInfo(String fn,String ln, String code) {
		writeText(firstname, fn);
		writeText(lastName, ln);
		writeText(postalCode, code);
		click(continueBtn);
		
		return new CheckoutOverviewPage(driver);
	}
}

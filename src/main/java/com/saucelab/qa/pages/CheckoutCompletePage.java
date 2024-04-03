package com.saucelab.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.saucelab.qa.base.BasePage;
import com.saucelab.qa.util.TestUtil;

public class CheckoutCompletePage extends BasePage {

	// Page Objects
	private By pageHeader = By.xpath(TestUtil.SECONDARY_PAGE_HEADER);
	
	private By completeHeader=By.className("complete-header");
	private By completeText=By.className("complete-text");
	private By backHomeBtn=By.id("back-to-products");

	// Page Constructor
	public CheckoutCompletePage(WebDriver driver) {
		super(driver);
	}

	// Page Actions
	// Checkout: Complete!
	public String getPageHeader() {
		return readText(pageHeader);
	}

	public String getOrderSuccess() {
		printOrderSuccess();
		return readText(completeHeader);
	}
	
	public void printOrderSuccess() {
		System.out.println(readText(completeHeader));
		System.out.println(readText(completeText));
	}
	
	public ProductHomePage verifyBackHomePage() {
		click(backHomeBtn);
		return new ProductHomePage(driver);
	}
}

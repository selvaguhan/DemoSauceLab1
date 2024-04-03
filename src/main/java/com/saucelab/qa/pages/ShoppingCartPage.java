package com.saucelab.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.saucelab.qa.base.BasePage;
import com.saucelab.qa.util.TestUtil;

public class ShoppingCartPage extends BasePage {

	// Page Objects
	private By pageHeader = By.xpath(TestUtil.SECONDARY_PAGE_HEADER);
	
	private By cartList = By.xpath("//div[@class='cart_item']");
	private By continueShopping=By.id("continue-shopping");
	private By checkout=By.id("checkout");

	// Page Constructor
	public ShoppingCartPage(WebDriver driver) {
		super(driver);
	}

	// Page Actions
	// Your Cart
	public String getPageHeader() {
		return readText(pageHeader);
	}
	
	public int getCartListSize() {
		List<WebElement> itemList = driver.findElements(cartList);
		return itemList.size();
	}
	
	public CheckoutPage selectCheckout() {
		click(checkout);
		return new CheckoutPage(driver);
	}
	
}

package com.saucelab.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.saucelab.qa.base.BasePage;
import com.saucelab.qa.util.TestUtil;

public class CheckoutOverviewPage extends BasePage {

	// Page Objects
	private By pageHeader = By.xpath(TestUtil.SECONDARY_PAGE_HEADER);

	private By summaryInfo = By.xpath("//div[@class='summary_info']/div[contains(@class,'summary')]");
	private By finishBtn = By.id("finish");
	
	// Page Constructor
	public CheckoutOverviewPage(WebDriver driver) {
		super(driver);
	}

	// Page Actions
	// Checkout: Overview
	public String getPageHeader() {
		return readText(pageHeader);
	}
	
	public void printSummaryInfo() {
		List<WebElement> summaryInformation=driver.findElements(summaryInfo);
		for (WebElement info : summaryInformation) {
			System.out.println(info.getText());
		}
	}
	
	public CheckoutCompletePage selectFinish() {
		click(finishBtn);
		return new CheckoutCompletePage(driver);
	}
}

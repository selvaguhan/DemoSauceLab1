package com.saucelab.qa.testscenarios;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.saucelab.qa.base.BaseTest;
import com.saucelab.qa.pages.CheckoutCompletePage;
import com.saucelab.qa.pages.CheckoutOverviewPage;
import com.saucelab.qa.pages.CheckoutPage;
import com.saucelab.qa.pages.LoginPage;
import com.saucelab.qa.pages.ProductHomePage;
import com.saucelab.qa.pages.ShoppingCartPage;

public class OrderProductTestScenario1 extends BaseTest{
	
	LoginPage loginPage;
	ProductHomePage productPage;
	ShoppingCartPage cartPage;
	CheckoutPage infoPage;
	CheckoutOverviewPage overviewPage;
	CheckoutCompletePage successPage;
	ExtentTest test;
	
	public OrderProductTestScenario1() {
		super();
	}

	public void doLogin1() {
		loginPage = new LoginPage(driver);
		productPage = loginPage.doLogin(props.getProperty("username"), props.getProperty("password"));
		pauseRun(1000);
	}
	
	@Test
	public void doProductOrder() {
		test=extent.createTest("Product Order Test - Single").assignCategory("Functional Testing").assignDevice("Chrome");
		test.info("Test Started");
		
		doLogin1();
		test.log(Status.PASS, "Successfully Login to SauceLab");
		
		//Product Page Verification
		String prodheader=productPage.getPageHeader();
		Assert.assertEquals(prodheader, "Products");
		test.pass("Verified Product Home Page Header - "+prodheader);
		
		// Add to Cart
		LinkedHashMap<String, BigDecimal> itemList=productPage.getProductList();
		test.log(Status.INFO, "Retrieve Product List in display order");
		String product1=productPage.retrieveProductNameList(itemList).get(0);
		test.log(Status.INFO, "Fetch First Product -"+ product1);
		productPage.selectAddToCart(product1);
		test.pass("Product Added to cart");
		
		//Navigate Cart page
		cartPage=productPage.navigateToCartPage();
		pauseRun(1000);
		test.info("Navigate to Cart Page");
		
		//Cart Page Verification
		String cartHeader=cartPage.getPageHeader();
		Assert.assertEquals(cartHeader, "Your Cart");
		test.pass("Verified shopping Cart Page Header - "+cartHeader);
		
		//Cart Items Size verification
		int itemsize=cartPage.getCartListSize();
		Assert.assertEquals(itemsize, 1);
		test.pass("Verified shopping Cart Items Size - "+itemsize);
		
		//Navigate Checkout page
		test.info("Select Checkout");
		infoPage=cartPage.selectCheckout();
		pauseRun(1000);
		test.pass("Navigate to Checkout Page");
		
		//Checkout Info Page verification
		String infoHeader=infoPage.getPageHeader();
		Assert.assertEquals(infoHeader, "Checkout: Your Information");
		test.pass("Verified Checkout Page Header - "+infoHeader);
		
		// Enter checkout info & navigate Overview
		test.info("Entering Checkout Information and select Continue");
		overviewPage=infoPage.enterCheckoutInfo("John", "snow", "638004");
		pauseRun(1000);
		test.info("Navigate to Checkout Overview Page");
		
		// Checkout Overview page verification
		String overviewHeader=overviewPage.getPageHeader();
		Assert.assertEquals(overviewHeader, "Checkout: Overview");
		test.pass("Verified Checkout Overview Page Header - "+overviewHeader);
		
		// Print Checkout payment Summary Info
		test.info("Started Printing Checkout Payment summary Information");
		overviewPage.printSummaryInfo();
		test.pass("Information printed/displayed");
		
		//Navigate Order Completion Page
		test.info("Click Finish");
		successPage=overviewPage.selectFinish();
		pauseRun(1000);
		test.pass("Navigate to Checkout Completion Page");
		
		// Checkout Completion Page verification
		String successHeader=successPage.getPageHeader();
		Assert.assertEquals(successHeader, "Checkout: Complete!");
		test.pass("Verified Order Completion Page Header - "+successHeader);
		
		// Verify Order Completion Message
		String successMsg=successPage.getOrderSuccess();
		Assert.assertEquals(successMsg, "Thank you for your order!");
		test.pass("Verified Order Completion Success Message - "+successMsg);
		
		// Navigate Back to Product Home page
		test.info("Click BackHome");
		productPage=successPage.verifyBackHomePage();
		pauseRun(1000);
		test.pass("Navigate Back to Product Home Page");
		
		//Logout
		test.info("Select Menu bar and Logout");
		productPage.Logout();
		test.pass("Logout Successfull");
		test.info("Test Completed");
	}
}

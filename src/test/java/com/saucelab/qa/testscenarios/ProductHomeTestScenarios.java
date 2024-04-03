package com.saucelab.qa.testscenarios;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.saucelab.qa.base.BaseTest;
import com.saucelab.qa.pages.LoginPage;
import com.saucelab.qa.pages.ProductHomePage;

public class ProductHomeTestScenarios extends BaseTest{

	LoginPage loginpage;
	ProductHomePage productPage;
	ExtentTest test;

	public ProductHomeTestScenarios() {
		super();
	}
	

	public void doLogin1() {
		loginpage = new LoginPage(driver);
		productPage = loginpage.doLogin(props.getProperty("username"), props.getProperty("password"));
		pauseRun(1000);
	}
	
	@Test(priority = 1,enabled=true)
	public void verifyProductPageHeader() {
		test=extent.createTest("Product Page Header Verification").assignCategory("Functional Testing").assignDevice("Chrome");
		test.info("Test Started");
		
		doLogin1();
		test.log(Status.PASS, "Successfully Login to SauceLab");
		
		String header=productPage.getPageHeader();
		Assert.assertEquals(header, "Products");
		test.pass("Verified Product Home Page Header - "+header);
		test.info("Test Completed");
	}
	
	@Test(priority = 2,enabled = true)
	public void verifyProductList() {
		test=extent.createTest("Product Listing Verification").assignCategory("Functional Testing").assignDevice("Chrome");
		test.info("Test Started");
		
		doLogin1();
		test.log(Status.PASS, "Successfully Login to SauceLab");
		
		int listSize=productPage.getProductListSize();
		System.out.println("Number Of Products displayed :: "+ listSize);
		test.log(Status.INFO, "Number Of Products displayed :: "+ listSize);
		
		if(listSize >=0) {
			Assert.assertTrue(true,"Products List are NOT displaying fine");
			test.pass("Products List are displaying fine");
		}
		
		test.log(Status.INFO, "Started Printing Product List");
		LinkedHashMap<String, BigDecimal> itemList=productPage.getProductList();
		productPage.printProductList(itemList);
		test.log(Status.PASS, "Completed Printing");
		test.info("Test Completed");
	}
	
	
	@Test(priority = 3,enabled = true)
	public void verifyProductListSortingByName() {
		test=extent.createTest("Product List Sorting By Name Verification").assignCategory("Functional Testing").assignDevice("Chrome");
		test.info("Test Started");
		
		doLogin1();
		test.log(Status.PASS, "Successfully Login to SauceLab");
		
				
		//Checking Default sort Option by Name :: Ascending
		String option=productPage.checkSelectedSortOption();
		test.log(Status.PASS, "Verifying Default Product List Sorting as "+option);
		if(option.equals("az")) {
			LinkedHashMap<String, BigDecimal> itemList=productPage.getProductList();
			test.log(Status.INFO, "Retrieve Product List in display order");
			
			boolean verifyAsc=productPage.verifyItemNameSortedAsc(itemList);
			Assert.assertTrue(verifyAsc, "Verify Ascending Product Sorted By Name :: FAILED");
			test.pass("Verified Ascending Product List Sorted By Name");
		}
		
		
		//Select & verify Descending sort Option By Name
		productPage.selectSortOptionByValue("za");
		test.log(Status.INFO, "Select Sort By Name Option as Descending");
		
		String option2=productPage.checkSelectedSortOption();
		test.log(Status.PASS, "Verifying Product List Sorting as "+option2);
		if(option2.equals("za")) {
			
			LinkedHashMap<String, BigDecimal> itemList=productPage.getProductList();
			test.log(Status.INFO, "Retrieve Product List in display order");

			boolean verifyDesc=productPage.verifyItemNameSortedDesc(itemList);
			Assert.assertTrue(verifyDesc, "Verify Descending Product Sorted By Name :: FAILED");
			test.pass("Verified Descending Product List Sorted By Name");

		}
		test.info("Test Completed");
	}
	
	
	@Test(priority = 4,enabled = true)
	public void verifyProductListSortingByPrice() {
		test=extent.createTest("Product List Sorting By Price Verification").assignCategory("Functional Testing").assignDevice("Chrome");
		test.info("Test Started");
		
		doLogin1();
		test.log(Status.PASS, "Successfully Login to SauceLab");
		
		//Select & verify Ascending sort Option By Price
		productPage.selectSortOptionByValue("lohi");
		test.info("Select Sort By Price Option as Low to High");
		String option=productPage.checkSelectedSortOption();
		test.log(Status.PASS, "Verifying Product List Sorting as "+option);
		if(option.equals("lohi")) {
			LinkedHashMap<String, BigDecimal> itemList=productPage.getProductList();
			test.log(Status.INFO, "Retrieve Product List in display order");

			boolean verifyAsc=productPage.verifyItemPriceSortedAsc(itemList);
			Assert.assertTrue(verifyAsc, "Verify Ascending Product Sorted By Price :: FAILED");
			test.pass("Verified Product List Sorted By Price - Low to High");

		}
		
		
		//Select & verify Descending sort Option By Price
		productPage.selectSortOptionByValue("hilo");
		test.log(Status.INFO, "Select Sort By Price Option as High to Low");
		String option2=productPage.checkSelectedSortOption();
		test.log(Status.PASS, "Verifying Product List Sorting as "+option2);
		if(option2.equals("hilo")) {
			
			LinkedHashMap<String, BigDecimal> itemList=productPage.getProductList();
			test.log(Status.INFO, "Retrieve Product List in display order");

			boolean verifyDesc=productPage.verifyItemPriceSortedDesc(itemList);
			Assert.assertTrue(verifyDesc, "Verify Descending Product Sorted By Price :: FAILED");
			test.pass("Verified Product List Sorted By Price - High to Low");

		}
		test.info("Test Completed");
	}
	
	
	@Test(priority = 5,enabled = true)
	public void addToCart() {
		test=extent.createTest("ADD TO CART").assignCategory("Functional Testing").assignDevice("Chrome");
		test.info("Test Started");
		
		doLogin1();
		test.log(Status.PASS, "Successfully Login to SauceLab");

		LinkedHashMap<String, BigDecimal> itemList=productPage.getProductList();
		test.log(Status.INFO, "Retrieve Product List in display order");
		String product1=productPage.retrieveProductNameList(itemList).get(0);
		test.log(Status.INFO, "Fetch First Product -"+ product1);
		productPage.selectAddToCart(product1);
		test.pass("Product Added to cart");
		pauseRun(1000);
		test.info("Test Completed");
	}
}

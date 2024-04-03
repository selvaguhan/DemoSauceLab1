package com.saucelab.qa.testscenarios;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.saucelab.qa.base.BaseTest;
import com.saucelab.qa.pages.LoginPage;
import com.saucelab.qa.pages.ProductHomePage;

public class LoginTestScenarios extends BaseTest {

	LoginPage loginpage;
	ProductHomePage productPage;
	ExtentTest test;

	public LoginTestScenarios() {
		super();
	}

	@Test(priority = 1)
	public void verifyLoginPageTitle() {
		test = extent.createTest("Verify Login Page title").assignAuthor("Tester1").assignCategory("Functional Testing")
				.assignDevice("Chrome");
		test.info("Test Started");

		loginpage = new LoginPage(driver);
		test.log(Status.PASS, "Launch SauceLab");

		String title = loginpage.getLoginPageTitle();
		Assert.assertEquals(title, "Swag Labs");
		test.pass("Verify Login Page Title - " + title);
		test.info("Test Completed");

	}

	@Test(priority = 2)
	public void loginTC() {
		test = extent.createTest("Login to SauceLab website").assignAuthor("Tester1")
				.assignCategory("Functional Testing").assignDevice("Chrome");
		test.info("Test Started");

		loginpage = new LoginPage(driver);
		test.log(Status.PASS, "Launch SauceLab");

		productPage = loginpage.doLogin(props.getProperty("username"), props.getProperty("password"));
		pauseRun(1000);
		test.pass("Login successfull to SauceLab");
		test.info("Test Completed");
	}

}

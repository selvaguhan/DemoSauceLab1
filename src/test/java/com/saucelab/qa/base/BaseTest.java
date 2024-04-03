package com.saucelab.qa.base;

import java.io.FileReader;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.saucelab.qa.util.TestUtil;

public class BaseTest {

	public WebDriver driver;
	public Properties props;
	public Page page;
	
	public ExtentReports extent;
	public ExtentSparkReporter spark;
	


	public BaseTest() {
		// Reading Property File
		try {
			props = new Properties();
			FileReader reader = new FileReader(TestUtil.CONFIG_PATH);
			props.load(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeTest
	public void extentInit() {
		extent=new ExtentReports();
		spark=new ExtentSparkReporter(props.getProperty("reportPath"));
		spark.config().setDocumentTitle("Demo SauceLab Automation");
		extent.attachReporter(spark);
	}
	
	@BeforeMethod
	public void setup() {
		
		// Launching Browser
		if (props.getProperty("browser").equals("chrome")) {
			driver = new ChromeDriver();
		} else if (props.getProperty("browser").equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			System.out.println("No Browser is defined in config file..");
		}

		// Setup Browser defaults
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
		pauseRun(1000);
		// Launch the application URL
		driver.get(props.getProperty("url"));

		// Runtime inheritance 
		page = new BasePage(driver);
	}

	@AfterMethod
	public void teardown() {
		driver.close();
		driver.quit();
	}
	
	@AfterTest
	public void clear() {
		extent.flush();
	}
	
	public void pauseRun(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

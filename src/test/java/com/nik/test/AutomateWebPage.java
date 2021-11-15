package com.nik.test;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.nik.pages.LoginPage;

public class AutomateWebPage {
		
		WebDriver driver;
		SetConnection sc;
		ExtentReports extent;
		
		@BeforeTest
		public void setUp() throws SQLException {
		String path=System.getProperty("user.dir")+"\\ExtentReport\\index.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);
		reporter.config().setReportName("Data Paramaterisation using JDBC");
		reporter.config().setDocumentTitle("Automation Test Results");
		extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Nikunj Thakur");
		System.setProperty("webdriver.chrome.driver", "E:\\Selenium from Scratch\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		}
		
		@Test
		public void login() throws Exception {
		ExtentTest test=extent.createTest("login");
		LoginPage lp=new LoginPage(driver);
		driver.get("https://www.stealmylogin.com/demo.html");
		sc=new SetConnection();
		sc.connecttoDB();
		lp.getUsername().sendKeys(sc.getFirstName());
		lp.getPassword().sendKeys(sc.getLastName());
		String screenshotPath = Utils.getScreenshot(driver, "login");
		test.addScreenCaptureFromPath(screenshotPath);
		lp.loginButton().click();
		Thread.sleep(2000);	
		String screenshotPath2 = Utils.getScreenshot(driver, "login");
		test.addScreenCaptureFromPath(screenshotPath2);
	}
		
		@AfterTest
		public void tearDown() {
			driver.manage().deleteAllCookies();
			driver.quit();	
			extent.flush();
		}

		
}


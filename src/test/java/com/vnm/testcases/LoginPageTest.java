package com.vnm.testcases;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.vnm.base.TestBase;
import com.vnm.pages.Dashboard;
import com.vnm.pages.LoginPage;
import com.vnm.util.ExcelReader;

public class LoginPageTest extends TestBase {
	LoginPage lp;
	Dashboard dp;
	ExcelReader reader;

	@BeforeTest
	public void reportSetup() {
		extentReportSetUp();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		lp = new LoginPage();
	}

	@Test(enabled = false)
	public void validateTitleTest() {
		logger.info("Starting with validateTitleTest");
		String actualTitle = lp.validateTitle();
		AssertJUnit.assertEquals(actualTitle, "OrangeHRM");
		logger.info("Completed with validateTitleTest");
		ExtentTest test = extent.createTest("TC1: Validating title!");
		test.log(Status.PASS, "TC Passed");
	}

	@Test(enabled = false, dataProvider = "getData")
	public void validateLoginTestUsingExcel(String username, String password) {
		dp = lp.validateLogin(username, password);
	}
	
	@Test(enabled = false)
	public void validateLoginTest() {
		logger.info("Starting with validateLoginTest");
		dp = lp.validateLogin(prop.getProperty("username"), prop.getProperty("password"));
		boolean bool = dp.validateDashboardLinkVisible();
		AssertJUnit.assertTrue(bool);
		logger.info("Completed with validateLoginTest");
		ExtentTest test = extent.createTest("TC2: Validating Login!");
		test.log(Status.FAIL, "TC Failed");
	}

	@Test
	@Parameters({"username"})
	public void readFromTestNGXML(String username) {
		System.out.println(username);
	}
	

	@DataProvider
	public Object[][] getData() {
		String filePath = "./src/test/resources/TestData/Auto (1).xlsx";
		String sheetName = "data";
		reader = new ExcelReader(filePath, sheetName);
		return reader.getTestData();

	}

	@AfterMethod
	public void closeSetup() {
		tearDown();
	}

	@AfterTest
	public void generateReport() {
		closeExtentReportSetUp();
	}

}

package com.vnm.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.vnm.base.TestBase;
import com.vnm.pages.Dashboard;
import com.vnm.pages.LoginPage;

public class LoginPageTest extends TestBase {
	LoginPage lp;
	Dashboard dp;

	@BeforeMethod
	public void setUp() {
		initialization();
		lp = new LoginPage();
	}
	
	@Test
	public void validateTitleTest() {
		logger.info("Starting with validateTitleTest");
		String actualTitle = lp.validateTitle();
		Assert.assertEquals(actualTitle, "OrangeHRMIndia");
		logger.info("Completed with validateTitleTest");
	}

	@Test
	public void validateLoginTest() {
		logger.info("Starting with validateLoginTest");
		dp = lp.validateLogin(prop.getProperty("username"), prop.getProperty("password"));
		boolean bool = dp.validateDashboardLinkVisible();
		Assert.assertTrue(bool);
		logger.info("Completed with validateLoginTest");
	}
	
	@AfterMethod
	public void closeSetup() {
		tearDown();
	}

}

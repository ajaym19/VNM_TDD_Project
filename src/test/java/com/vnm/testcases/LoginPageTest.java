package com.vnm.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.vnm.base.TestBase;
import com.vnm.pages.LoginPage;

public class LoginPageTest extends TestBase {
	LoginPage lp;

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
	}

	public void validateLoginTest() {

	}
	
	@AfterMethod
	public void closeSetup() {
		tearDown();
	}

}

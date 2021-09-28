package com.vnm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vnm.base.TestBase;

public class Dashboard extends TestBase {

	@FindBy(xpath = "//b[text()='Dashboard']")
	WebElement dashboardMenu;

	public Dashboard() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean validateDashboardLinkVisible() {
		return dashboardMenu.isDisplayed();
	}
}
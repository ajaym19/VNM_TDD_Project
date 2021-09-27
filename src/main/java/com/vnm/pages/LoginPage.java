package com.vnm.pages;

import com.vnm.base.TestBase;

public class LoginPage extends TestBase {

	public String validateTitle() {
		return driver.getTitle();
	}
}

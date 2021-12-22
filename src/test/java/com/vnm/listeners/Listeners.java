package com.vnm.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {

	public void onTestStart(ITestResult result) {
		System.out.println("Starting with test");
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("Here because TC passed");
		System.out.println(result.getName());
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("Here because TC failed");
	}

}

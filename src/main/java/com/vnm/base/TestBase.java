package com.vnm.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static Logger logger;
	public static ExtentReports extent;
	public static ExtentSparkReporter reporter;

	public TestBase() {

		prop = new Properties();
		logger = Logger.getLogger(TestBase.class);
		String propertyFilePath = "./src/main/java/com/vnm/config/config.properties";
		try {
			logger.info("Trying to read Properties file!!!");
			FileInputStream fis = new FileInputStream(propertyFilePath);
			prop.load(fis);
			logger.info("Properties file reading Success!!!");
		} catch (FileNotFoundException e) {
			logger.error("Cannot find the property file!!!");
		} catch (IOException e) {
			logger.error("Unable to read the property file!!!");
		}
	}

	public static void initialization() {
		logger = Logger.getLogger(TestBase.class);
		logger.info("Trying to read browser property");
		String browser = prop.getProperty("browser");
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			logger.info(browser + " Browser launched successfully");
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			logger.info(browser + " Browser launched successfully");
		} else if (browser.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			logger.info(browser + " Browser launched successfully");
		} else {
			System.out.println("Browser name is Invalid");
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		logger.info("Application launched successfuly!!!");
	}

	// login method and PF

	public void extentReportSetUp() {
		String reportPath = System.getProperty("user.dir")+ "/Reports/vnm.html";
		reporter = new ExtentSparkReporter(reportPath);
		reporter.config().setDocumentTitle("VNM Test Results");
		reporter.config().setReportName("VNM Automation");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Project", "VNM Web Application");
		extent.setSystemInfo("QA Manager", "Ajay Mishra");
		extent.setSystemInfo("QA Lead", "Jyothi");
		extent.setSystemInfo("QA", "Iswarya");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Sprint", "Sprint 2");
		
		//configurations	
	}
	
	public void closeExtentReportSetUp() {
		extent.flush();
	}
	
	public static void tearDown() {
		driver.quit();
		logger.info("Chrome Browser closed successfully");
	}
}

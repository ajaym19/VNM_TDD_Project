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

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static Logger logger;

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
		System.out.println(1);
		System.out.println(logger);
		logger.info("Trying to read browser property");
		String browser = prop.getProperty("browser");
		if (browser.equalsIgnoreCase("chrome")) {
			logger.info("Identified the browser as " + browser + " and trying to launch the Browser!!!");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			logger.info("Browser launched successfully");
		} else if (browser.equalsIgnoreCase("firefox")) {
			logger.info("Identified the browser as " + browser + " and trying to launch the Browser!!!");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			logger.info("Browser launched successfully");
		} else if (browser.equalsIgnoreCase("ie")) {
			logger.info("Identified the browser as " + browser + " and trying to launch the Browser!!!");
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			logger.info("Browser launched successfully");
		} else {
			System.out.println("Browser name is Invalid");
		}

		// Initializing Logger
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		logger.info("Application launched successfuly!!!");
	}

	// login method and PF

	public static void tearDown() {
		driver.quit();
		logger.info("Chrome Browser closed successfully");
	}
}

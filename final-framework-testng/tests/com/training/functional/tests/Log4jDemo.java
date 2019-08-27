package com.training.functional.tests;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.training.pom.UniformHomePage;
import com.training.pom.UniformLoginPage;
import com.training.utility.Log4jLogger;

public class Log4jDemo{
	WebDriver driver;
	private UniformHomePage homePage;
	private UniformLoginPage loginPage;
	private Logger logging;
	
	@BeforeTest
	public void testSetup() {
		logging=Log4jLogger.getLog4jLog("Vinodh");
	}

	@BeforeMethod
	public void setUp() throws InterruptedException {	
        logging.debug("Hello this is a debug message...................");
        logging.info("Hello this is a info message....................");
	}
	
	@Test
	public void testcase1() throws InterruptedException {
        logging.debug("Hello this is a debug message...................");
        logging.info("Hello this is a info message....................");

	}
}
package com.training.functional.tests;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class MyTest {
	ExtentReports extentReport;
	ExtentTest extentTest;
	WebDriver driver;
	
	@BeforeSuite
	public void beforeSuite() {
		//In before suite we are creating HTML report template, adding basic information to it and load the extent-config.xml file
		extentReport = new ExtentReports(System.getProperty("user.dir") + "/test-output/SeleniumExtentReport.html",true);
		extentReport.addSystemInfo("Host Name", "JourneyofQuality").addSystemInfo("Environment", "Automation Testing").addSystemInfo("User Name", "Sanoj");
		extentReport.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
	}
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver","" );
		driver = new ChromeDriver();
	}
	
	@BeforeMethod
	public void beforeMethod(Method method) {
		//In before method we are collecting the current running test case name
		String className = this.getClass().getSimpleName();
		extentTest = extentReport.startTest(className + "-" + method.getName());
	}
	
	@Test
	public void passTest() {
		Assert.assertTrue(true);
	}
	
	@Test
	public void failTest() {
		driver.get("http://google.com&#8221");
		Assert.assertEquals(driver.getTitle().toString(), "Journey");
	}
	
	@Test
	public void skipTest() {
		throw new SkipException("Skipping – This is not ready for testing ");
	}
	
	@AfterMethod
	public void getResult(ITestResult result, Method method) throws Exception {
		//In after method we are collecting the test execution status and based on that the information writing to HTML report
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
			String screenshotPath = MyTest.capture(driver, result.getName());
			extentTest.log(LogStatus.FAIL, "Error Details :- \n" + result.getThrowable().getMessage() + extentTest.addScreenCapture(screenshotPath));
		}
		if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
		}
		if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test Case Passed is " + result.getName());
		}
	}
	
	@AfterSuite
	public void endReport() {
		//In after suite stopping the object of ExtentReports and ExtentTest
		extentReport.endTest(extentTest);
		extentReport.flush();
		driver.quit();
	}
	
	/**
	* To Capture the Screenshot and return the file path to extent report fail
	* cases
	*
	* @param driver
	* @param screenShotName
	* @return
	* @throws IOException
	*/
	private static String capture(WebDriver driver, String screenShotName) throws IOException {
		String dest = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy h-m-s");
			Date date = new Date();
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			dest = System.getProperty("user.dir") + "\\ErrorScreenshots\\" + screenShotName + dateFormat.format(date)+ ".png";
			File destination = new File(dest);
			FileUtils.copyFile(source, destination);
		} catch (Exception e) {
			e.getMessage();
			System.out.println(e.getMessage());
		}
		return dest;
	}
}


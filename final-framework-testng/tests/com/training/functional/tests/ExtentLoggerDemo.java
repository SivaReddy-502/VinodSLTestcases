package com.training.functional.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.training.pom.UniformHomePage;
import com.training.pom.UniformLoginPage;
import com.training.utility.ExtentLogger;
public class ExtentLoggerDemo {
	private static UniformHomePage homePage;
	private static UniformLoginPage loginPage;
	
	public static void main(String[] args) throws InterruptedException {
		ExtentLogger el = new ExtentLogger();
		ExtentTest logger = el.startLogging("Extent LoggerDemo");
		logger.log(LogStatus.INFO, "Executing Testcase1");
		System.setProperty("webdriver.chrome.driver", "C:\\Official\\MyProjects\\ExternalLibraries\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://uniform.upskills.in");
		homePage = new UniformHomePage(driver, logger);
		loginPage=new UniformLoginPage(driver, logger);
		String title=driver.getTitle();
		logger.log(LogStatus.PASS, "Launch Application");
		homePage.clickAccount();
		Thread.sleep(2000);
		homePage.clicklogin();
		loginPage.typeUser("email03@gmail.com");
		loginPage.typePassword("Name1");
		Thread.sleep(5000);
		driver.close();
		logger.log(LogStatus.PASS, "Logout Application");
		el.endLogging(logger);
		el.generateReport();
	}
}

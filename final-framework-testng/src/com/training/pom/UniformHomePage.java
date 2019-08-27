package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.training.utility.ExtentLogger;
import com.trianing.waits.WaitTypes;

public class UniformHomePage {

	private WebDriver driver; 
	private String title="Uniform Store";
	ExtentTest logger;
		
	public UniformHomePage(WebDriver driver, ExtentTest logger) {
		this.driver = driver; 
		this.logger=logger;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@title='My Account']")
	private WebElement account; 

	@FindBy(xpath="//a[text()='Register']")
	private WebElement register; 
	
	@FindBy(xpath="//a[text()='Login']")
	private WebElement login; 	

	public void clickAccount() {
		this.account.click();
		logger.log(LogStatus.PASS, "Click Account");
	}	
	
	public void clickregister() {
		this.register.click(); 
		logger.log(LogStatus.PASS, "Click Register");
	}	

	public void clicklogin() {
		this.login.click(); 
		logger.log(LogStatus.PASS, "Click Login Link");
	}	

	public void verifyHomePageLaunched() throws Exception {
		WaitTypes wt = new WaitTypes(this.driver);
		WebElement ele = wt.elementToBeClickable(account, 5);
		boolean expected=true;
		boolean actual=ele.isDisplayed();
		try{
			Assert.assertEquals(expected, actual, "Verify Homepage is Launched");
			logger.log(LogStatus.PASS, "Verify Homepage is Launched");
		}catch(Exception e) {
			logger.log(LogStatus.FAIL, "Verify Homepage is Launched");
			throw new Exception(e);
		}
	}	
}
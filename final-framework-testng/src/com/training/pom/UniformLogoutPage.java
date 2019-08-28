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
import com.trianing.waits.WaitTypes;

public class UniformLogoutPage {
	private WebDriver driver; 
	private WaitTypes wt;
	private WebDriverWait wait;
	private String logoffMessage="You have been logged off your account. It is now safe to leave the computer.";
	ExtentTest logger;
	
	public UniformLogoutPage(WebDriver driver, ExtentTest logger) {
		this.driver = driver; 
		this.logger=logger;
		this.wait= new WebDriverWait(driver, 5);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//p[text()='You have been logged off your account. It is now safe to leave the computer.']")	
	private WebElement logoutMessage; 
	
	@FindBy(xpath="//a[@title='My Account']")
	private WebElement account; 
	
	@FindBy(xpath="//*[text()='Logout']")
	private WebElement logout; 	
	
	public void clickAccount() {
		this.account.click(); 
		logger.log(LogStatus.PASS, "Click Account");
	}	
	
	public void clickLogout() {
		this.account.click(); 
		wt = new WaitTypes(this.driver);
		WebElement ele = wt.elementToBeClickable(logout, 5);		
		ele.click(); 
		logger.log(LogStatus.PASS, "Click Logout");
	}	
		
	public void verifyLogoutSuccessful() throws Exception {
		
		String actual;
		String expected=logoffMessage;
		try{
			wait.until(ExpectedConditions.visibilityOf(logoutMessage));
			actual=logoutMessage.getText();
			Assert.assertEquals(actual, expected, "Logout is Successful");
			logger.log(LogStatus.PASS, "Verify Logout is Successful");
		}catch(Exception e) {
			logger.log(LogStatus.FAIL, "Verify Logout is Successful");
			throw new Exception(e);
		}
	}		
}

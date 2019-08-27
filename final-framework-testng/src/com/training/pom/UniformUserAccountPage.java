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

public class UniformUserAccountPage {

	private WebDriver driver; 
	private WebDriverWait wait;
	private String title="My Account";
	ExtentTest logger;
		
	public UniformUserAccountPage(WebDriver driver, ExtentTest logger) {
		this.driver = driver; 
		this.logger=logger;
		this.wait= new WebDriverWait(driver, 5);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[text()='Change your password']")	
	private WebElement changePassword; 
	
	@FindBy(xpath="//a[text()='Edit your account information']")
	private WebElement editAccount; 	
	
	@FindBy(xpath="//*[text()=' Success: Your password has been successfully updated.']")
	private WebElement passwordSuccessMessage; 
	
	public void clickChangePassword() {
		this.changePassword.click(); 
		logger.log(LogStatus.PASS, "Click Change Password Link");
	}		

	public void verifyLoginIsSuccessful() throws Exception {
		wait.until(ExpectedConditions.titleIs(title));
		String actual =driver.getTitle();
		String expected=title;
		try{
			Assert.assertEquals(expected, actual, "Verify User is Logged in Successfully");
			logger.log(LogStatus.PASS, "Verify User is Logged in Successfully");
		}catch(Exception e) {
			logger.log(LogStatus.FAIL, "Verify User is Logged in Successfully");
			throw new Exception(e);
		}
	}

	public void verifyPasswordChangedSuccessful() throws Exception {
		wait.until(ExpectedConditions.visibilityOf(changePassword));
		String actual =driver.getTitle();
		String expected=title;
		try{
			Assert.assertEquals(expected, actual, "Verify Password is Changed Successfully");
			logger.log(LogStatus.PASS, "Verify Password is Changed Successfully");
		}catch(Exception e) {
			logger.log(LogStatus.FAIL, "Verify Password is Changed Successfully");
			throw new Exception(e);
		}
	}		
}

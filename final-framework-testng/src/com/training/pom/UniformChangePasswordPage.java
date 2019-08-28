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

public class UniformChangePasswordPage {
	
	private WebDriver driver; 
	private WebDriverWait wait;
	private String title="Change Password";
	ExtentTest logger;

	public UniformChangePasswordPage(WebDriver driver, ExtentTest logger) {
		this.driver = driver; 
		this.logger=logger;
		this.wait= new WebDriverWait(driver, 5);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//*[@name='password']")
	private WebElement password; 

	@FindBy(xpath="//*[@name='confirm']")
	private WebElement confirmPassword; 

	@FindBy(xpath="//*[@type='submit' and @value='Continue']")
	private WebElement contine; 

	public void typePassword(String pass) {
		this.password.clear();
		this.password.sendKeys(pass);
		logger.log(LogStatus.PASS, "Type New Password");
	}
	
	public void typeConfirmPassword(String pass) {
		this.confirmPassword.clear();
		this.confirmPassword.sendKeys(pass);
		logger.log(LogStatus.PASS, "Confirm New Password");
	}	

	public void clickContinue() {
		this.contine.click(); 
		logger.log(LogStatus.PASS, "Click Continue  button");
	}

	public void verifyPasswordPageLaunched() throws Exception {
		String actual;
		String expected=title;
		try{
			wait.until(ExpectedConditions.elementToBeSelected(password));
			actual=driver.getTitle();
			Assert.assertEquals(expected, actual, "Verify Change Password Form is Launched");
			logger.log(LogStatus.PASS, "Verify Change Password Form is Launched");
		}catch(Exception e) {
			logger.log(LogStatus.FAIL, "Verify Change Password Form is Launched");
			throw new Exception(e);
		}
	}
}
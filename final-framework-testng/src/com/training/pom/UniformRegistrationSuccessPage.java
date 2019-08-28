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

public class UniformRegistrationSuccessPage {
	
	private WebDriver driver; 
	private WebDriverWait wait;
	private String expectedMessage="Congratulations! Your new account has been successfully created!";
	ExtentTest logger;
	
	public UniformRegistrationSuccessPage(WebDriver driver, ExtentTest logger) {
		this.driver = driver; 
		this.logger=logger;
		this.wait= new WebDriverWait(driver, 5);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//p[text()='Congratulations! Your new account has been successfully created!']")	
	private WebElement successMessage; 
	
	@FindBy(xpath="//*[text()='Continue']")	
	private WebElement continueBtn; 			
	
	public void verifyRegistrationSuccessful() throws Exception {
		String actual;
		String expected = expectedMessage;
		try{
			wait.until(ExpectedConditions.visibilityOf(successMessage));
			actual =successMessage.getText();
			Assert.assertEquals(actual, expected, "Verify User Registion is Successful");
			logger.log(LogStatus.PASS, "Verify User Registion is Successful");
		}catch(Exception e) {
			logger.log(LogStatus.FAIL, "Verify User Registion is Successful");
			throw new Exception(e);
		}		
	}		
}

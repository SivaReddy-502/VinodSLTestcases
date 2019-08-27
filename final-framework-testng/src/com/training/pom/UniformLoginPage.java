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

public class UniformLoginPage {

	private WebDriver driver; 
	private WebDriverWait wait;
	private String title="Account Login";
	ExtentTest logger;
	
	public UniformLoginPage(WebDriver driver, ExtentTest logger) {
		this.driver = driver; 
		this.logger=logger;
		this.wait= new WebDriverWait(driver, 5);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@name='email']")
	private WebElement userName; 
	
	@FindBy(xpath="//*[@name='password']")
	private WebElement password; 
	
	@FindBy(xpath="//input[@value='Login' and @type ='submit']")
	private WebElement login; 
	
	public WebElement getUser() {
		return userName;
	}
	
	public void typeUser(String user) {
		this.userName.clear();
		this.userName.sendKeys(user);
		logger.log(LogStatus.PASS, "Type User : " + user);
	}		

	public void typePassword(String pass) {
		this.password.clear();
		this.password.sendKeys(pass);
		logger.log(LogStatus.PASS, "Type Password : " + pass);
	}	

	public void clickLogin() {
		this.login.click(); 
		logger.log(LogStatus.PASS, "Click Login Button");
	}	
	
	public void verifyEnteredUser() {
		String enteredUser=this.userName.getAttribute("value");
		logger.log(LogStatus.PASS, "Entered User is : " + enteredUser);
	}
	
	public void verifyEnteredPassword() {
		String enteredPassword=this.password.getAttribute("value");
		logger.log(LogStatus.PASS, "Entered Password is : " + enteredPassword);
	}		

	public void verifyLoginPageLaunched() throws Exception {
		wait.until(ExpectedConditions.visibilityOf(userName));
		boolean expected=true;
		boolean actual=userName.isDisplayed();
		try{
			Assert.assertEquals(expected, actual, "Login Form is Displayed Successfully");
			logger.log(LogStatus.PASS, "Verify Login Form is Displayed");
		}catch(Exception e) {
			logger.log(LogStatus.FAIL, "Verify Login Form is Displayed");
			throw new Exception(e);
		}			
	}
}

package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class UniformRegistrationPage {

	private WebDriver driver; 
	private WebDriverWait wait;
	private String title="Register Account";
	ExtentTest logger;
		
	public UniformRegistrationPage(WebDriver driver, ExtentTest logger) {
		this.driver = driver; 
		this.logger=logger;
		this.wait= new WebDriverWait(driver, 5);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="firstname")	
	private WebElement firstName; 

	@FindBy(name="lastname")	
	private WebElement lastName; 
	
	@FindBy(name="email")	
	private WebElement email; 
	
	@FindBy(name="telephone")	
	private WebElement telephone; 
	
	@FindBy(name="fax")	
	private WebElement fax; 
	
	@FindBy(name="company")	
	private WebElement company; 

	@FindBy(name="address_1")	
	private WebElement address1; 
	
	@FindBy(name="address_2")	
	private WebElement address2; 
	
	@FindBy(name="city")	
	private WebElement city; 
	
	@FindBy(name="postcode")	
	private WebElement postcode; 
	
	@FindBy(name="country_id")	
	private WebElement country; 

	@FindBy(name="zone_id")	
	private WebElement zone; 
	
	@FindBy(name="password")	
	private WebElement password; 
	
	@FindBy(name="confirm")	
	private WebElement confirmPassword; 
	
	@FindBy(xpath="//*[@name='newsletter' and @value='1']")	
	private WebElement subscribeNewsletter;	
	
	@FindBy(xpath="//*[@name='newsletter' and @value='0']")	
	private WebElement notSubscribeNewsletter; 	
	
	@FindBy(name="agree")	
	private WebElement agree; 
	
	@FindBy(xpath="//*[@type='submit' and @value='Continue']")	
	private WebElement submitBtn; 
	
	public void typeFirstName(String firstName) {
		this.firstName.clear();
		this.firstName.sendKeys(firstName);
		logger.log(LogStatus.PASS, "Enter First Name");
	}

	public void typeLastName(String lastName) {
		this.lastName.clear();
		this.lastName.sendKeys(lastName);
		logger.log(LogStatus.PASS, "Enter Last Name");
	}

	public void typeEmail(String emailId) {
		this.email.clear();
		this.email.sendKeys(emailId);
		logger.log(LogStatus.PASS, "Enter EmailID");
	}

	public void typeTelephone(String phoneNumber) {
		this.telephone.clear();
		this.telephone.sendKeys(phoneNumber);
		logger.log(LogStatus.PASS, "Enter Phone Number");
	}

	public void typeFax(String faxNumber) {
		this.fax.clear();
		this.fax.sendKeys(faxNumber);
		logger.log(LogStatus.PASS, "Enter Fax Number");
	}		
	
	public void typeCompany(String companyName) {
		this.company.clear();
		this.company.sendKeys(companyName);
		logger.log(LogStatus.PASS, "Enter Company Name");
	}

	public void typeAddress1(String address1) {
		this.address1.clear();
		this.address1.sendKeys(address1);
		logger.log(LogStatus.PASS, "Enter Address1");
	}
	
	public void typeAddress2(String address2) {
		this.address2.clear();
		this.address2.sendKeys(address2);
		logger.log(LogStatus.PASS, "Enter Address2");
	}	
	
	public void typeCity(String cityName) {
		this.city.clear();
		this.city.sendKeys(cityName);
		logger.log(LogStatus.PASS, "Enter City");
	}

	public void typePostcode(String postcodeNumber) {
		this.postcode.clear();
		this.postcode.sendKeys(postcodeNumber);
		logger.log(LogStatus.PASS, "Enter postal code");
	}

	public void selectCountry(String countryName) {
		Select country = new Select(this.country);
		country.selectByVisibleText(countryName);
		logger.log(LogStatus.PASS, "Select Country");
	}
	
	public void selctZone(String zoneName) {
		Select zone = new Select(this.zone);
		zone.selectByVisibleText(zoneName);
		logger.log(LogStatus.PASS, "Select Zone Name");
	}
	
	public void typePassword(String password) {
		this.password.clear();
		this.password.sendKeys(password);
		logger.log(LogStatus.PASS, "Enter Password");
	}		
	
	public void typeConfirmPassword(String cpassword) {
		this.confirmPassword.clear();
		this.confirmPassword.sendKeys(cpassword);
		logger.log(LogStatus.PASS, "Enter Confirm Password");
	}
	
	public void subscribeNewsletter(String newsletter) {
		if (newsletter=="Yes") {
			this.subscribeNewsletter.click();
			logger.log(LogStatus.PASS, "Subscribe to Newsletter");
		}else {
			this.notSubscribeNewsletter.click();
			logger.log(LogStatus.PASS, "Do not subscribe to Newsletter");
		}
	}	
	
	public void checkAgree() {
		this.agree.click();
		logger.log(LogStatus.PASS, "Click Agree");
	}	

	public void clickContinue() throws InterruptedException {
		Thread.sleep(1000);
		this.submitBtn.click(); 
		logger.log(LogStatus.PASS, "Click Continue");
	}
	
	public void verifyEnteredFirstName() {
		String enteredName=this.firstName.getAttribute("value");
		logger.log(LogStatus.PASS, "Entered User is : " + enteredName);
	}
	
	public void verifyEnteredEmail() {
		String enteredEmail=this.email.getAttribute("value");
		logger.log(LogStatus.PASS, "Entered Password is : " + enteredEmail);
	}		
	
	public void verifyEnteredPhoneNumber() throws Exception {
		String enteredPhone=this.telephone.getAttribute("value");
		logger.log(LogStatus.PASS, "Entered Phone Number is : " + enteredPhone);
	}		
	
	public void verifyRegistrationPageLaunched() throws Exception {
		String actual;
		String expected=title;
		try{
			wait.until(ExpectedConditions.visibilityOf(firstName));
			actual =driver.getTitle();
			Assert.assertEquals(expected, actual, "Verify Registion Form is Launched");
			logger.log(LogStatus.PASS, "Verify Registion Form is Launched");
		}catch(Exception e) {
			logger.log(LogStatus.FAIL, "Verify Registion Form is Launched");
			throw new Exception(e);
		}	
	}	
}
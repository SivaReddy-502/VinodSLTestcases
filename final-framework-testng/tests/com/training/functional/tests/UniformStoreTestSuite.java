package com.training.functional.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.training.generics.ScreenShot;
import com.training.pom.UniformChangePasswordPage;
import com.training.pom.UniformHomePage;
import com.training.pom.UniformLoginPage;
import com.training.pom.UniformLogoutPage;
import com.training.pom.UniformRegistrationPage;
import com.training.pom.UniformRegistrationSuccessPage;
import com.training.pom.UniformUserAccountPage;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;
import com.training.utility.ExtentLogger;

public class UniformStoreTestSuite {
	private WebDriver driver;
	private static String baseUrl;
	private static String timeOut;
	private UniformHomePage homePage;
	private UniformRegistrationPage registrationPage;	
	private UniformRegistrationSuccessPage registrationSuccessPage;
	private UniformLogoutPage logoutpage;
	private UniformLoginPage loginPage;
	private UniformUserAccountPage accountPage;
	private UniformChangePasswordPage changePasswordPage;
	private static Properties properties;
	private ScreenShot screenShot;
	private ExtentLogger eLog= new ExtentLogger();
	private ExtentTest logger;
	
	//==============change the below inputs for each run======================
	private String firstName="First38";
	private String lastName="Last38";
	private String emailId="email38@gmail.com";
	private String phoneNumber="9890989738";	
	private String password="Pass38";
	private String cpassword="Pass38";
	private String oldPassword="Pass38";
	private String newPassword="Pass39";
	//==============Inputs Ends Here======================	
	
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {	
		//Loading the Properties file
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		
		//Get the base url from the property file
		baseUrl = properties.getProperty("baseURL");
		timeOut=properties.getProperty("implicitWait");		
	}

	@BeforeMethod
	public void setUp(Method method) throws Exception {	
		
		//Get Testcase Description
		String testDesc = properties.getProperty(method.getName());
		
		//Starting the Extent Reporter
		logger =eLog.startLogging(method.getName());
		logger.log(LogStatus.INFO, method.getName() + " : Executing " + testDesc);
		
		//Initializing the Driver
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		driver.manage().timeouts().implicitlyWait((long)Integer.parseInt(timeOut), TimeUnit.SECONDS);
		
		//Initialise Screenshot
		screenShot = new ScreenShot(driver);
		
		//Initializing the Pages
		homePage = new UniformHomePage(driver, logger);
		registrationPage = new UniformRegistrationPage(driver, logger);
		registrationSuccessPage = new UniformRegistrationSuccessPage(driver, logger);
		accountPage=new UniformUserAccountPage(driver, logger);
		logoutpage = new UniformLogoutPage(driver, logger);
		loginPage=new UniformLoginPage(driver, logger);
		changePasswordPage=new UniformChangePasswordPage(driver, logger);
		
		//Launch the Application
		logger.log(LogStatus.PASS, "Launch Application URL " + baseUrl);
		driver.get(baseUrl);
	}
	
	@Test(priority=1 , enabled=true)
	public void UFM_001(Method method) throws Exception {	
		//Testcase to Verify Successful New User Registration
		//Initialise the variables
		String faxNumber="9890989771";
		String companyName="IBM";
		String address1="DLF";
		String address2="Ramapuram";
		String cityName="Chennai";
		String postcodeNumber="600015";
		String countryName="India";
		String zoneName="Tamil Nadu";
		String subscribe="No";
		
		//Verify Uniform Store homepage is launched and exit test if not opened
		homePage.verifyHomePageLaunched();
		homePage.clickAccount();
		homePage.clickregister();
		
		//Verify Uniform Store Registration Page is launched before Registration and exit if not
		registrationPage.verifyRegistrationPageLaunched();
	
		//Fill up the Registration Form with various inputs
		registrationPage.typeFirstName(firstName);
		registrationPage.typeLastName(lastName);
		registrationPage.typeEmail(emailId);
		registrationPage.typeTelephone(phoneNumber);
		registrationPage.typeFax(faxNumber);
		registrationPage.typeCompany(companyName);
		registrationPage.typeAddress1(address1);
		registrationPage.typeAddress2(address2);
		registrationPage.typeCity(cityName);
		registrationPage.typePostcode(postcodeNumber);
		registrationPage.selectCountry(countryName);
		registrationPage.selctZone(zoneName);
		registrationPage.typePassword(password);
		registrationPage.typeConfirmPassword(cpassword);
		registrationPage.subscribeNewsletter(subscribe);
		registrationPage.checkAgree();

		//Verify Entered User and Password and Proceed
		registrationPage.verifyEnteredFirstName();
		registrationPage.verifyEnteredEmail();	
		registrationPage.verifyEnteredPhoneNumber();
		registrationPage.clickContinue();		
		
		//Verify Registration is Successful...
		registrationSuccessPage.verifyRegistrationSuccessful();
		
		//Take Screenshot of success Registration
		screenShot.captureScreenShot(method.getName());
		logger.log(LogStatus.PASS, "Take Screen Shot of Successful Registration");
	}
	
	@Test(priority=2, enabled=true)
	public void UFM_002(Method method) throws Exception {		
		//Testcase to Verify Successful User Login
		//Verify Uniform Store homepage is launched and exit test if not opened
		homePage.verifyHomePageLaunched();
		homePage.clickAccount();
		homePage.clicklogin();	
		
		//Verify login Page is launched
		loginPage.verifyLoginPageLaunched();
		
		//Verify Fill up Login Form and login
		loginPage.typeUser(emailId);
		loginPage.typePassword(password);
		
		//Verify Entered User and Password and click Login
		loginPage.verifyEnteredUser();	
		loginPage.verifyEnteredPassword();
		loginPage.clickLogin();
		
		//Verify login is Successful
		accountPage.verifyLoginIsSuccessful();
		
		//Take Screenshot of success Registration
		screenShot.captureScreenShot(method.getName());
		logger.log(LogStatus.PASS, "Take Screen Shot of Successful Login");		
	}
	
	@Test(priority=3, enabled=true)
	public void UFM_003(Method method) throws Exception {		
		//Testcase to Verify Successful Password Change
		//Verify Uniform Store homepage is launched and exit test if not opened
		homePage.verifyHomePageLaunched();
		homePage.clickAccount();
		homePage.clicklogin();	

		//Verify login Page is launched
		loginPage.verifyLoginPageLaunched();
		
		//Verify Fill up Login Form and login
		loginPage.typeUser(emailId);
		loginPage.typePassword(oldPassword);
		
		//Verify Entered User and Password and click Login
		loginPage.verifyEnteredUser();	
		loginPage.verifyEnteredPassword();		
		loginPage.clickLogin();
		
		//Verify if Login is Successful and change the password
		accountPage.verifyLoginIsSuccessful();
		accountPage.clickChangePassword();
		changePasswordPage.typePassword(newPassword);
		changePasswordPage.typeConfirmPassword(newPassword);
		changePasswordPage.clickContinue();
		
		//Verify if password is changed successfully
		accountPage.verifyPasswordChangedSuccessful();

		//Take Screenshot of success password change
		screenShot.captureScreenShot(method.getName());
		logger.log(LogStatus.PASS, "Take Screen Shot of password change");			
	}	
	
	@AfterMethod
	public void logout() throws InterruptedException {
		//Log out of Application
		try {
			Thread.sleep(10000);
			logoutpage.clickLogout();
			Thread.sleep(3000);
			logoutpage.verifyLogoutSuccessful();
			driver.quit();
			logger.log(LogStatus.PASS, "Close Application");
		}catch(Exception e) {
			logger.log(LogStatus.FAIL, "Close Application");
			driver.quit();
		}finally {
			eLog.endLogging(logger);
		}
	}
	
	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
		eLog.generateReport();
	}
}

package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
//import org.testng.log4testng.Logger;

import pageobjects.AccountPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import resource.Base;

public class LoginTest extends Base {
	
	public WebDriver driver;
	Logger log;
	
	
	@BeforeMethod
	public void openbrowser() throws IOException {
		
		log=LogManager.getLogger(LoginTest.class.getName());
		driver=initializeBrowser();
		log.debug("Broser got launched");
		String URL = prop.getProperty("url");
		driver.get(URL);
		log.debug("Naviagte to application");
	
		
	}
	@Test(dataProvider="getDatalogin")
	public void login(String email, String password, String expectedresult) throws IOException, InterruptedException {
		
		
		

	LandingPage landingpage= new LandingPage(driver);
	landingpage.myAccount().click();
	landingpage.loginOption().click();
	log.debug("Choose the option");
		
	
	LoginPage loginpage=new LoginPage(driver);
	loginpage.email().sendKeys(email);
	loginpage.password().sendKeys(password);
	log.debug("Provided login credentials");
	loginpage.loginbutton().click();
	
	AccountPage accountpage=new AccountPage(driver);
	String actualresult=null;
	try {
	if(accountpage.edityourinfo().isDisplayed()) {
		actualresult = "success";
		log.debug("user got logged in");
	} }catch(Exception e){
		actualresult="failure";
		log.error("login was failed");
	}
	
	Assert.assertEquals(actualresult, expectedresult);
	log.info("your test was successful");
	
	}
	
	@DataProvider
	public Object[][] getDatalogin() {
		Object[][] data= {{"itsdeepthi24@gmail.com","Test@123","success"},{"dummy@test.com","123456","failure"}};
		return data;
	}
	
	@AfterMethod
	public void closure()
	{
		log.info("browser got closed");
		driver.close();
		
	}
	
}

package stepdefinitions;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.AccountPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import resource.Base;

public class Login extends Base {
	WebDriver driver;
	LandingPage landingpage;
	LoginPage loginpage;
	AccountPage accountpage;
	
	@Given("^Open any Browser$")
	public void Open_any_Browser() throws IOException {
		 driver=initializeBrowser();
	}
	
	@And("^Navigate_to_Login_page$")
	public void Navigate_to_Login_page() throws InterruptedException {
		
		prop=new Properties();
		driver.get(prop.getProperty("url"));
		landingpage= new LandingPage(driver);
		landingpage.myAccount().click();
		landingpage.loginOption().click();
	}
	
	@When("^User enters username as \"([^\"]*)\" and password as \"([^\"]*)\" into the fields$")
	public void User_enters_username_as_email_and_password_as_password_into_the_fields(String email, String password) {
		loginpage=new LoginPage(driver);
		loginpage.email().sendKeys(email);
		loginpage.password().sendKeys(password);
	}
	
	@And("^User clicks on Login button$")
	public void User_clicks_on_Login_button() {
		loginpage.loginbutton().click();
	}
	
	@Then("^Verify user is able to successfully login$")
	public void Verify_user_is_able_to_successfully_login() {
		accountpage=new AccountPage(driver);
		Assert.assertTrue(accountpage.edityourinfo().isDisplayed());
	}
}

package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import resource.Base;

public class TwoTest extends Base {
	public WebDriver driver;
	@Test
	public void testtwo() throws IOException {
		System.out.println("TestTwo gor executed");
		System.out.println("Change made by dummy tester");
	
		 driver=initializeBrowser();
		driver.get("https://tutorialsninja.com/demo/");
	
	}
	@AfterMethod
	public void clos() {
		driver.close();
	}
}

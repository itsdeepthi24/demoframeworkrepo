package tests;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import resource.Base;

public class ThreeTest extends Base {
	public WebDriver driver;
	@Test
	public void testthree() throws IOException, InterruptedException {
		
		driver=initializeBrowser();
		System.out.println("This change was made by RRONE");
		driver.get("https://omayo.blogspot.com/");
		System.out.println("TestThree got executed");
		
		Thread.sleep(5000);
	Assert.fail();
		
		
		
		
	}
@AfterMethod
	public void cl() {
		driver.close();
		
	}
}

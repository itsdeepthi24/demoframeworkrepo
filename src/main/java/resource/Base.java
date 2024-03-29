package resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	 public WebDriver driver=null;
	 public Properties prop;
	 File sourcefile;
	 String destinationpath;
	
	 public WebDriver initializeBrowser() throws IOException {
		 
		prop=new Properties();
		String path = System.getProperty("user.dir")+"\\src\\main\\java\\resource\\data.properties";
		FileInputStream fis=new FileInputStream(path);
		prop.load(fis);
		
		String browserName=prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")) {
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
		}
		else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver= new InternetExplorerDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		return driver;
	}
	 
	 public String takeScreenshot(String testName, WebDriver driver) throws IOException {
		 sourcefile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 destinationpath=System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";
		 FileUtils.copyFile(sourcefile, new File(destinationpath));
		return destinationpath;
		 
		 
		
	 }
}

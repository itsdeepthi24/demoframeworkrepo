package listener;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resource.Base;
import utilities.ExtentReporting;

public class Listeners extends Base implements ITestListener{
	 WebDriver driver=null;
	 ExtentReports extent = ExtentReporting.getExtentReports();
	 ExtentTest extentTest;
	 ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<ExtentTest>();
	 
	@Override
	public void onTestStart(ITestResult result) {
		 extentTest = extent.createTest(result.getName()+"execution started");
		 extentTestThread.set(extentTest);
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		//extentTest.log(Status.PASS, "test passed");
		extentTestThread.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		//  WebDriver driver=null;
		extentTest.log(Status.FAIL, "test failed");
		//extentTest.fail(result.getThrowable());
		extentTestThread.get().fail(result.getThrowable());
		
		String testMethodName=result.getName();
	
		
		this.driver = ((Base)result.getInstance()).driver;
		
		
		
			try {
				driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			} catch (IllegalAccessException | NoSuchFieldException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		try {
			String screenshotFilePath=takeScreenshot(testMethodName,driver);
			extentTestThread.get().addScreenCaptureFromPath(screenshotFilePath, testMethodName);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
		
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
	
	

}

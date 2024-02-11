package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporting {
	static ExtentReports extent;

	public  static ExtentReports getExtentReports() {
		String extentreportpath=System.getProperty("user.dir")+"\\reports\\rextentreport.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(extentreportpath);
		
		reporter.config().setReportName("Framework Design");
		reporter.config().setDocumentTitle("Framework Project");
		
		extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("OperatingSystem","Windows 11");
		extent.setSystemInfo("Tested By", "Deepthi");
		
		return extent;
	}
}

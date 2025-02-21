package utilities;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;
	
	public void onStart(ITestContext testContext)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //time stamp
		repName = "Test-Report-" + timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName); // specify location of the report
		
		sparkReporter.config().setDocumentTitle("GDT Automation Report"); // title of the report
		sparkReporter.config().setReportName("GDT Functional Testing"); // name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "GDT");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty())
		{
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // to display groups in report
		test.log(Status.PASS, result.getName() + "got successfully executed");
	}
	
	public void onTestFailure(ITestResult result)
	{
		System.out.println("onTestFailure triggered for test: " + result.getName());
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, result.getName() + "got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result) 
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + "got skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());	
	}

	public void onFinish(ITestContext context) {
	    System.out.println("onFinish() called, generating report...");
	    try {
	        extent.flush();  // Ensure the report is written
	        System.out.println("Report flushed successfully.");
	    } catch (Exception e) {
	        System.out.println("Error while flushing report: " + e.getMessage());
	        e.printStackTrace();
	    }

	    // Define full path for report
	    String reportPath = System.getProperty("user.dir") + "\\reports\\" + repName;
	    System.out.println("Generated report path: " + reportPath);

	    File reportFile = new File(reportPath);
	    try {
	        if (reportFile.exists()) {
	            Desktop.getDesktop().browse(reportFile.toURI());
	            System.out.println("Opening report...");
	        } else {
	            System.out.println("Report file does not exist at: " + reportPath);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}


}

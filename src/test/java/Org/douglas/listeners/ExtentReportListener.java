package Org.douglas.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportListener implements ITestListener {

    private static ExtentReports extent;
    private static ExtentTest test;

    static {
        // Initialize the HTML reporter
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-output/ExtentReport.html");
        // Initialize the ExtentReports object
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @Override
    public void onStart(ITestContext context) {
        // Add any setup code if needed before the tests start
    }

    @Override
    public void onFinish(ITestContext context) {
        // After all tests are done, flush the report to generate it
        extent.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Start a new test in the report
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Log the success result in the report
        test.pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        // Log the failure and exception in the report
        test.fail(result.getThrowable());

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Log the skipped result in the report
        test.skip("Test skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Log a special message if the test failed but within success percentage
        test.warning("Test failed but within success percentage");
    }
}

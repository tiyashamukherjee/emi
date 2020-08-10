package test;

import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import PageClassPackage.LandingPage;
import PageClassPackage.PageObjectModel;
import base.ExtentReporterManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class InvalidTest extends LandingPage{

	public ExtentReports report= ExtentReporterManager.getReportInstance();
	public ExtentTest test;
	public static String browsername;
	

	Logger log = Logger.getLogger(InvalidTest.class);

	@Parameters("gridbrowser")
	@BeforeTest(alwaysRun = true)
	public void beforeTest(String gridbrowser) throws Exception, MalformedURLException {
		log.info("***********INVOKING OF THE DESIRED DRIVER*************");
		setUpDriver("mode", gridbrowser);
		browsername = gridbrowser;
	}

	@Test(priority = 0, description = "Opening of the browser")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description:Opening of the browser")
	@Story("Story Name:To check the opening of the browser")
	public void Test0() throws Exception {
		log.info("***************OPENING OF THE BROWSER****************");
	if(browsername.equalsIgnoreCase("Firefox")) {
			 test=report.createTest("Mozilla- Invalid Test");
			 } 
		 else if(browsername.equalsIgnoreCase("chrome")) { 
			  test=report.createTest("Chrome- Invalid Test");
			  }
		test.log(Status.INFO, "Test0 has started.");
		openUrl("websiteURL");
		test.log(Status.PASS, "Browser has opened");
	}

	@Test(priority = 1, description = "Selection of car loan")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Case Description:Selection of car loan")
	@Story("Story Name:To select car loan")
	public void Test1() throws Exception {
		log.info("***********SELECTION OF CAR LOAN*************");
		test.log(Status.INFO, "Test2 has started.");
		Assert.assertTrue(driver.findElement(PageObjectModel.carloan).isEnabled(), "Car loan not Selected");
		elementClick();
		test.log(Status.PASS, "Car loan Selected");
	}

	@Test(priority = 2, description = "Providing amount to the input")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description:Providing amount to input")
	@Story("Story Name:To provide the amount to input")
	public void Test2() throws Exception {
		log.info("***********PROVIDING THE AMOUNT******************");
	    test.log(Status.INFO, "Test3 has started.");
		Assert.assertTrue(driver.findElement(PageObjectModel.carloanamount).isEnabled(),
				"Amount text field not enabled.");
		putAmount();
		test.log(Status.PASS, "Correct amount are provided and accepted");
		Thread.sleep(2000);
	}

	@Test(priority = 3, description = "Providing interest to the input")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description:Providing interest to input")
	@Story("Story Name:To provide interest to input")
	public void Test3() throws Exception {
		log.info("***********PROVIDING THE INTEREST*************");
		test.log(Status.INFO, "Test3 has started.");
		Assert.assertTrue(driver.findElement(PageObjectModel.interest).isEnabled(), "Interest text field not enabled.");
		putInterestForNegative();
		String pic=takeScreenshots();
		test.pass("Screenshot Provided", MediaEntityBuilder.createScreenCaptureFromPath(pic).build());
		test.log(Status.PASS, "Correct values are provided");
		Thread.sleep(2000);
		String value=error();
		Assert.assertTrue(value.contains("0"),"Doesnt contain 0");
		
	}

	@Test(priority = 4, description = "Providing tenure to the input")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description:Providing tenure to input")
	@Story("Story Name:To provide tenure to input")
	public void Test4() throws Exception {
		log.info("***********PROVIDING THE TENURE*************");
	test.log(Status.INFO, "Test3 has started.");
		Assert.assertTrue(driver.findElement(PageObjectModel.tenure).isEnabled(), "Tenure text field not enabled.");
		putTenureForNegative();
		String pic=takeScreenshots();
		test.pass("Screenshot Provided", MediaEntityBuilder.createScreenCaptureFromPath(pic).build());
		test.log(Status.PASS, "Correct values are provided");
		Thread.sleep(2000);
		String value=error1();
		Assert.assertTrue(value.contains("0"),"Doesnt contain 0");
	}
	@AfterMethod(alwaysRun = true)
	public void flushAndEndTestMethod(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "The test case " + result.getName() + " is failed");
			test.log(Status.FAIL, "The test case is failing because of " + result.getThrowable());	   
			String path=takeScreenshots();
			test.fail("Screenshot Provided", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, "The test case " + result.getName() + " is skipped");
		}

	}

	@AfterTest(alwaysRun = true)
	public void close() {
		log.info("***********CLOSING OF THE  BROWSER*************");
		report.flush();
		closeBrowser();
	}
}

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

public class NewTest extends LandingPage {

	public ExtentReports report= ExtentReporterManager.getReportInstance();
	public ExtentTest test;
	public static String browsername;
	
	Logger log = Logger.getLogger(NewTest.class);

	@Parameters("gridbrowser")
	@BeforeTest(alwaysRun = true)
	public void beforeTest(String gridbrowser) throws Exception, MalformedURLException {
		log.info("***********INVOKING OF THE DESIRED DRIVER*************");
		setUpDriver("mode", gridbrowser);
		browsername = gridbrowser;
	}

	@Test(priority = 0, groups = {"Smoke Test","Regression Test"}, description = "Opening of the browser")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description:Opening of the browser")
	@Story("Story Name:To check the opening of the browser")
	public void Test0() throws Exception {
		log.info("***************OPENING OF THE BROWSER****************");
	if(browsername.equalsIgnoreCase("Firefox")) {
			 test=report.createTest("Mozilla-Test");
			 } 
		 else if(browsername.equalsIgnoreCase("chrome")) { 
			  test=report.createTest("Chrome-Test");
			  }
		test.log(Status.INFO, "Test0 has started.");
		openUrl("websiteURL");
		test.log(Status.PASS, "Browser has opened");
	}

	
	@Test(priority = 1, groups = "Regression Test", description = "Selection of car loan")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Case Description:Selection of car loan")
	@Story("Story Name:To select car loan")
	public void Test1() throws Exception {
		log.info("***********SELECTION OF CAR LOAN*************");
		test.log(Status.INFO, "Test1 has started.");
		Assert.assertTrue(driver.findElement(PageObjectModel.carloan).isEnabled(), "Car loan not Selected");
		elementClick();
		test.log(Status.PASS, "Car loan Selected");
	}

	
	
	@Test(priority = 2, groups = "Regression Test", description = "Providing amount to the input")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description:Providing amount to input")
	@Story("Story Name:To provide the amount to input")
	public void Test2() throws Exception {
		log.info("***********PROVIDING THE AMOUNT******************");
	    test.log(Status.INFO, "Test2 has started.");
		Assert.assertTrue(driver.findElement(PageObjectModel.carloanamount).isEnabled(),
				"Amount text field not enabled.");
		putAmount();
		test.log(Status.PASS, "Correct amount are provided and accepted");
		Thread.sleep(2000);
	}

	@Test(priority = 3, groups = "Regression Test", description = "Providing interest to the input")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description:Providing interest to input")
	@Story("Story Name:To provide interest to input")
	public void Test3() throws Exception {
		log.info("***********PROVIDING THE INTEREST*************");
		test.log(Status.INFO, "Test3 has started.");
		Assert.assertTrue(driver.findElement(PageObjectModel.interest).isEnabled(), "Interest text field not enabled.");
		putInterest();
		test.log(Status.PASS, "Correct values are provided and accepted");
		Thread.sleep(2000);
	}

	@Test(priority = 4, groups = "Regression Test", description = "Providing tenure to the input")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description:Providing tenure to input")
	@Story("Story Name:To provide tenure to input")
	public void Test4() throws Exception {
		log.info("***********PROVIDING THE TENURE*************");
	test.log(Status.INFO, "Test4 has started.");
		Assert.assertTrue(driver.findElement(PageObjectModel.tenure).isEnabled(), "Tenure text field not enabled.");
		putTenure();
		test.log(Status.PASS, "Correct values are provided and accepted");
		Thread.sleep(2000);
	}

	@Test(priority = 5, groups = "Regression Test", description = "Generating the results")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description:Generating the results")
	@Story("Story Name:To generate the results")
	public void Test5() throws Exception {
		test.log(Status.INFO, "Test5 has started.");
		test.log(Status.PASS, "Results Generated");
		log.info("***********GENERATING RESULTS*************");
		generate();
	}

	@Test(priority = 6, groups = "Regression Test", description = "Scrolling the page")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description:Scrolling the page")
	@Story("Story Name:To scroll the page")
	public void Test6() throws Exception {
		test.log(Status.INFO, "Test6 has started.");
		test.log(Status.PASS, "Page scrolled till the correct point");
		scrollDown();
	}

	@Test(priority = 7, groups = "Regression Test", description = "Getting the value of principal and interest")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description:Getting the value of principal and interest")
	@Story("Story Name:To get the values of principal and interest of desired month")
	public void Test7() throws Exception {
		log.info("***********GETTING THE PRINCIPAL AND INTEREST OF DESIRED MONTH*************");
				test.log(Status.INFO, "Test7 has started.");
		Assert.assertTrue(driver.findElement(PageObjectModel.principaltable).isDisplayed(),
				"Prinicipal value not displayed.");
		Assert.assertTrue(driver.findElement(PageObjectModel.interesttable).isDisplayed(),
				"Interest value not displayed");
		principalAndInterest();

		test.log(Status.PASS, "Principal and interest value obtained of desired month");
	}

	@Test(priority = 8, groups = "Regression Test", description = "Taking screenshots")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description:Taking screenshot of the page")
	@Story("Story Name:To check taking of screenshot")
	public void Test8() throws Exception {
		log.info("***********TAKING SCREENSHOTS*************");
	     test.log(Status.INFO, "Test8 has started.");
		Thread.sleep(3000);
		String path = takeScreenshots();
		test.log(Status.PASS, "Screenshot taken");
		test.pass("Screenshot Provided", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
	}
	
	@Test(priority=1,groups ="Smoke Test")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: Checking if car loan button is enabled")
	@Story("Story Name: To check car loan button")
	
	public void smoke1() throws InterruptedException {
		log.info("============== Checking car loan button ==================");
		test.log(Status.INFO, "Whether car is enabled or not");
		Assert.assertTrue(driver.findElement(PageObjectModel.carloan).isEnabled(),"The car loan button is not enabled");
		System.out.println("Is car loan tab enabled? "+driver.findElement(PageObjectModel.carloan).isEnabled());
		System.out.println();
		driver.findElement(PageObjectModel.carloan).click();
		Thread.sleep(3000);
		test.log(Status.PASS, "The car loan button is verified");
	}
	
	@Test(priority=2,groups ="Smoke Test")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: Checking if car loan amount textbox is enabled")
	@Story("Story Name: To check car loan amount textbox")
	
	public void smoke2() {
		log.info("============== Checking the loan amount textbox ==================");
		test.log(Status.INFO, "Whether carloan amount textbox is enabled or not");
		Assert.assertTrue(driver.findElement(PageObjectModel.carloanamount).isEnabled(),"The loan amount textbox is not enabled");
		System.out.println();
		System.out.println("Is car loan amount textbox enabled? "+driver.findElement(PageObjectModel.carloanamount).isEnabled());
		System.out.println();
		test.log(Status.PASS, "The car loan amount textbox is enabled");
	}
	
	@Test(priority=3,groups ="Smoke Test")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: Checking if car loan interest rate textbox is enabled")
	@Story("Story Name: To check car loan interest rate textbox")
	
	public void smoke3() {
		log.info("============== Checking the interest rate textbox ==================");
		test.log(Status.INFO, "Whether interest textbox is enabled or not");
		Assert.assertTrue(driver.findElement(PageObjectModel.interest).isEnabled(),"The loan interest textbox is not enabled");
		System.out.println();
		System.out.println("Is car loan interest rate textbox enabled? "+driver.findElement(PageObjectModel.interest).isEnabled());
		System.out.println();
		
		test.log(Status.PASS, "The car loan interest rate textbox is enabled");
	}
	
	@Test(priority=4,groups ="Smoke Test")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: Checking if car loan tenure textbox is enabled")
	@Story("Story Name: To check car loan tenure textbox")
	
	public void smoke4() {
		log.info("============== Checking the tenure textbox ==================");
		test.log(Status.INFO, "Whether tenure textbox is enabled or not");
		Assert.assertTrue(driver.findElement(PageObjectModel.tenure).isEnabled(),"The loan tenure textbox is not enabled");
		System.out.println();
		System.out.println("Is car loan tenure textbox enabled? "+driver.findElement(PageObjectModel.tenure).isEnabled());
		System.out.println();
		test.log(Status.PASS, "The car loan tenure textbox is enabled");
	}

	@AfterMethod(alwaysRun = true)
	public void flushAndEndTestMethod(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "The test case " + result.getName() + " is failed");
			test.log(Status.FAIL, "The test case " + result.getThrowable() + " is failed");
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

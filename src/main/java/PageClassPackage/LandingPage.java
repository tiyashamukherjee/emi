package PageClassPackage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import base.Base;
import base.ReadExcel;
import base.WriteExcel;
import io.qameta.allure.Step;

public class LandingPage extends Base {
	

	
	@Step("Setting up of the driver")
	public void setUpDriver(String mode,String gridbrowser) throws MalformedURLException {
		invokeDriver( mode, gridbrowser);
	}
	
	@Step("Opening of the URL")
	public void openUrl(String websiteurlkey) throws InterruptedException {
		String expected="EMI Calculator for Home Loan, Car Loan & Personal Loan in India";
		openBrowser(websiteurlkey);
		Thread.sleep(2000);
		String actual=driver.getTitle();
		Assert.assertEquals(actual, expected,"Not on the correct webpage"); 
	}
	
	@Step("Selecting the car loan.........")
	public void elementClick() throws InterruptedException {
			driver.findElement(PageObjectModel.carloan).click();
					Thread.sleep(1000);
	}
	
	@Step("Putting the principal in the field.....")
	public void putAmount()throws IOException, InterruptedException{
		String amount=ReadExcel.readExcel(1, 0);  
		driver.findElement(PageObjectModel.carloanamount).sendKeys(Keys.CONTROL,"a", Keys.CLEAR);
		Thread.sleep(3000);
		driver.findElement(PageObjectModel.carloanamount).sendKeys(amount);			
	}
	
	@Step("Putting the interest in the value")
	public void putInterest() throws IOException {
		String interest=ReadExcel.readExcel(1,1);
		driver.findElement(PageObjectModel.interest).sendKeys(Keys.CONTROL,"a", Keys.CLEAR);
		driver.findElement(PageObjectModel.interest).sendKeys(interest);
	}
	
	@Step("Putting the interest in the value")
	public void putInterestForNegative() throws IOException {
		String interest=ReadExcel.readExcel(1,3);
		driver.findElement(PageObjectModel.interest).sendKeys(Keys.CONTROL,"a", Keys.CLEAR);
		driver.findElement(PageObjectModel.interest).sendKeys(interest);
	}
	
	@Step("Returning the default value.....")
	public String error() {
		driver.findElement(PageObjectModel.tenure).click();
		String error=driver.findElement(PageObjectModel.interest).getAttribute("value");
		return error;
	}
	
	@Step("Putting the tenure in the field.....")     
	public void putTenure() throws IOException {
		String tenure=ReadExcel.readExcel(1, 2);
		driver.findElement(PageObjectModel.tenure).sendKeys(Keys.CONTROL,"a", Keys.CLEAR);
		driver.findElement(PageObjectModel.tenure).sendKeys(tenure);	
	}
	
	@Step("Putting the tenure in the field.....")     
	public void putTenureForNegative() throws IOException {
		String tenure=ReadExcel.readExcel(1, 4);
		driver.findElement(PageObjectModel.tenure).sendKeys(Keys.CONTROL,"a", Keys.CLEAR);
		driver.findElement(PageObjectModel.tenure).sendKeys(tenure);	
	}
	
	@Step("Returning the default value.....")
	public String error1() {
		driver.findElement(PageObjectModel.year).click();
		String error1=driver.findElement(PageObjectModel.tenure).getAttribute("value");
		return error1;
	}
	
	@Step("Generating the results.........")
	public void generate() {
		driver.findElement(PageObjectModel.year).click();
	}
	
	@Step("Scrolling the page.........")
	public void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(PageObjectModel.graph);
		js.executeScript("arguments[0].scrollIntoView()", element);
		driver.findElement(PageObjectModel.expand).click();
	}
	
	@Step("Getting the principal and interest value of desired month.........")
	public void principalAndInterest() throws IOException {
		String TotalPrincipal=driver.findElement(PageObjectModel.principaltable).getText();
		System.out.println("Total Principal of first month is:"+TotalPrincipal);
		String TotalInterest=driver.findElement(PageObjectModel.interesttable).getText();
		System.out.println("Total Interest of first month is:"+TotalInterest);		
		WriteExcel write=new WriteExcel();
		write.writeExcelResults(TotalPrincipal,TotalInterest);
	}
	
	@Step("Taking screenshot of the page.........")
	public String takeScreenshots() {
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		TakesScreenshot takescreenshot=(TakesScreenshot)driver;
		File sourceFile=takescreenshot.getScreenshotAs(OutputType.FILE);
		File destFile=new File("C:\\Users\\ARUP\\eclipse-workspace\\Emicalculator\\screenshot\\"+timeStamp+".png");
		String imagepath="C:\\Users\\ARUP\\eclipse-workspace\\Emicalculator\\screenshot\\"+timeStamp+".png";
	  try {
		FileUtils.copyFile(sourceFile, destFile);
	} catch (IOException e) {
		e.printStackTrace();
	}
	  return imagepath;
	}
	
	
	@Step("Closing of the browser.........")
   public void closeBrowser() {
   	driver.quit();
   }
}



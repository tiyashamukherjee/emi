package base;


import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.qameta.allure.Step;


public class Base  {

	 public static WebDriver driver;
	 public static Properties prop;
	 public static String huburl="http://localhost:4444/wd/hub";
	 
	 @Step("Invoking of the browser.........")
	public  void invokeDriver(String mode,String gridbrowser) throws MalformedURLException {
		
		try {
				prop = new Properties();
				FileInputStream file = new FileInputStream(
							"C:\\Users\\ARUP\\eclipse-workspace\\Emicalculator\\config.properties");
				prop.load(file);
				file.close();
			} catch (Exception e) {
					// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		 
		if(prop.getProperty(mode).equalsIgnoreCase("local")) 
	     {		 
	 if (gridbrowser.equalsIgnoreCase("Chrome")) {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir")+ "\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	else if (gridbrowser.equalsIgnoreCase("Firefox")) {
		System.setProperty("webdriver.gecko.driver",
				System.getProperty("user.dir")+ "\\drivers\\geckodriver.exe");
		DesiredCapabilities cap = DesiredCapabilities.firefox();
		cap.setCapability("marionette", true);	
		driver = new FirefoxDriver();
	} 
	
	else if (gridbrowser.equalsIgnoreCase("Internet Explorer"))  {
		System.setProperty("webdriver.ie.driver",
				System.getProperty("user.dir")+ "\\drivers\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		
	}
	else {
		System.out.println("Give the correct supporting browser");
	}
	     }
	else if(prop.getProperty(mode).equalsIgnoreCase("grid"))
	{
		if((gridbrowser).equalsIgnoreCase("Chrome")) {
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName("chrome");
			cap.setPlatform(Platform.WINDOWS);
			
			ChromeOptions op = new ChromeOptions();
			op.merge(cap);
			
			driver = new RemoteWebDriver(new URL(huburl),op);
		}
		else if((gridbrowser).equalsIgnoreCase("Firefox")) {
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("marionette", true);
			cap.setBrowserName("firefox");
			cap.setPlatform(Platform.WINDOWS);
			driver = new RemoteWebDriver(new URL(huburl),cap);
		}
		else if((gridbrowser).equalsIgnoreCase("Internet Explorer")) {
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName("internet explorer");
			cap.setPlatform(Platform.WINDOWS);
						
			driver = new RemoteWebDriver(new URL(huburl),cap);
		}
	}
	else {
		System.out.println("pass the correct mode");
	}
			driver.manage().window().maximize();
}
	 
	 
	 
	 
	@Step("Opening of the browser.........")
	public  void openBrowser(String websiteurlKey) {
	    driver.get(prop.getProperty(websiteurlKey));
		
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
	}
	
}
	
	
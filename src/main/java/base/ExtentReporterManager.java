package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReporterManager {

	
	public static ExtentReports report;

	
	public static ExtentReports getReportInstance() {

		if (report == null) {

			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\test-output\\testreport.html");
			report = new ExtentReports();
			report.attachReporter(htmlReporter);

			
			htmlReporter.config().setTimeStampFormat("dd-MMMM-yyyy HH-mm-ss");
		}
		return report;
	}

}

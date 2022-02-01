package com.org.test;

import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.org.utils.Constants;

public class BaseTest {

	public static WebDriver driver;
	public ExtentHtmlReporter htmlReporter;
	public static ExtentReports extentReports;
	public static ExtentTest logger;

	@BeforeTest
	public void beforeTest() {
		htmlReporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + File.separator + "reports" + File.separator + "AutomationReport.html");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Automation Test Result");
		htmlReporter.config().setTheme(Theme.DARK);
		extentReports = new ExtentReports();
		extentReports.setSystemInfo("Automation Tester", "Aarti Joshi");
		extentReports.attachReporter(htmlReporter);

	}

	@AfterTest(alwaysRun = true)
	public void afterTest() {
		extentReports.flush();
	}

	@BeforeMethod
	@Parameters(value = { "browserName" })
	public void beforeMethodTest(String browserName, Method testMethod) {
		logger = extentReports.createTest(testMethod.getName());
		setupDriver(browserName);
		driver.manage().window().maximize();
		driver.get(Constants.url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void afterMethodTest(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			String methodName = result.getMethod().getMethodName();
			String logText = "Test case: " + methodName + "Passed";
			Markup markup = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
			logger.log(Status.PASS, markup);
		} else if (result.getStatus() == ITestResult.FAILURE) {
			String methodName = result.getMethod().getMethodName();
			String logText = "Test case: " + methodName + "Failed";
			Markup markup = MarkupHelper.createLabel(logText, ExtentColor.RED);
			logger.log(Status.FAIL, markup);
		}
		driver.quit();
	}

	public void setupDriver(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "geckodriver.exe");
			driver = new FirefoxDriver();
		} else {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "chromedriver.exe");
			driver = new ChromeDriver();
		}
	}

	public void reportLog(String message) {
		logger.info("Message: " + message);
		Reporter.log(message);
	}
}
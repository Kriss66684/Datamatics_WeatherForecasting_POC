package com.dm.bdd.integrations.report_utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.dm.bdd.integrations.common_utils.DriverFactory;

public class ReportManager {

	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static Map<Long, ExtentTest> testMap = new HashMap<>();

	public static void startReport() {

		if (htmlReporter == null) {
			String timeStamp = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());
			String dateStamp = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
			htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Execution_Reports/WEB_Reports/"
					+ dateStamp + "/" + "Weather-" + timeStamp + ".html");
			// htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +
			// "/Reports/WebReports/Decathlon.html");
			// Create an object of Extent Reports
			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
			extent.setSystemInfo("Host Name", "Weather");
			extent.setSystemInfo("Environment", "Production");
			extent.setSystemInfo("User Name", "Tester 1");
			System.out.println();
			htmlReporter.config().setDocumentTitle("Weather Application");
			// Name of the report
			htmlReporter.config().setReportName("Weather Application");
			htmlReporter.config().enableTimeline(true);
			// htmlReporter.config().setAutoCreateRelativePathMedia(true);
			// Dark Theme
			htmlReporter.config().setTheme(Theme.STANDARD);

		}

	}

	public static void startTest(String testName, String categories) {

		ExtentTest test = extent.createTest(testName);
		test.assignCategory(categories);
		testMap.put(Thread.currentThread().getId(), test);

	}

	/**
	 * =============================================================================
	 * Method: logScreenShot | Author: EN | Date:30 Jan 2020 |
	 * Description: This method log take screenshot | Parameters: message | Return:
	 * none
	 * =============================================================================
	 * 
	 * @param driver
	 * @throws IOException
	 */
	public static void logScreenshot() throws IOException {
		// getCurrentTest().addScreenCaptureFromBase64String(ScreenshotUtil.takeScreenshot(DriverFactory.getInstance().getWebDriver()));
		MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromBase64String(
				ScreenshotUtil.takeScreenshot(DriverFactory.getInstance().getWebDriver())).build();
		getCurrentTest().fail("", mediaModel);

	}

	public static void logPass(String message) {
		getCurrentTest().log(Status.PASS, message);

	}

	public static void logFail(String message) {
		getCurrentTest().log(Status.FAIL, message);

	}

	public static void logInfo(String message) {
		getCurrentTest().log(Status.INFO, message);

	}

	public static void endCurrentTest() {
		getCurrentTest().getExtent().flush();

		testMap.remove(Thread.currentThread().getId());
	}

	public static ExtentTest getCurrentTest() {
		return testMap.get(Thread.currentThread().getId());

	}

	public static void endReport() {

		extent.flush();
	}

	

}

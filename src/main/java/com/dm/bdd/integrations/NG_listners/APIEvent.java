package com.dm.bdd.integrations.NG_listners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.dm.bdd.integrations.common_utils.ConfigReader;
import com.dm.bdd.integrations.common_utils.DriverFactory;

public class APIEvent implements ITestListener {
	// private String str_BrowserType = System.getProperty("BROWSER");
	private String str_BrowserType = ConfigReader.getValue("Browser");
	DriverFactory driverFactory = DriverFactory.getInstance();
	public static String strBrowser;

	@Override
	public void onTestStart(ITestResult arg0) {
		System.out.println("+++++++++++++++++++++onTestStart++++++++++++++++++++");

	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		// ReportManager.endCurrentAPITest();
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		// .endCurrentAPITest();
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext arg0) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

}

package com.dm.bdd.integrations.NG_listners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.dm.bdd.integrations.common_utils.ConfigReader;
import com.dm.bdd.integrations.common_utils.DriverFactory;
import com.dm.bdd.integrations.report_utils.ReportManager;

public class WebEvent implements ITestListener {
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
		
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		
		/*System.out.println("+++++++++++++++++++++onTestFailure++++++++++++++++++++");
		
		ReportManager.logFail(iTestResult.getThrowable().toString());
	
		try {
			ReportManager.logScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ReportManager.endCurrentTest();
		//driverFactory.getWebDriver().quit();
*/	}

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

		strBrowser = arg0.getCurrentXmlTest().getParameter("browser");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

}

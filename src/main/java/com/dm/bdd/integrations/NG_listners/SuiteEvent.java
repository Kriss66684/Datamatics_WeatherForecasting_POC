package com.dm.bdd.integrations.NG_listners;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.IExecutionListener;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.TestListenerAdapter;
import org.testng.xml.XmlSuite;

import com.dm.bdd.integrations.common_utils.ConfigReader;
import com.dm.bdd.integrations.common_utils.DriverFactory;
import com.dm.bdd.integrations.report_utils.ReportManager;
import com.opencsv.CSVWriter;

public class SuiteEvent extends TestListenerAdapter implements ISuiteListener, IExecutionListener, IReporter {
	CSVWriter writer;
	public List<String[]> data = new ArrayList<String[]>();
	// private String str_Execution_TYPE =
	// System.getProperty("Execution_Test_Category");
	private static String str_Execution_TYPE = ConfigReader.getValue("Execution_type");
	// private static String Execution_Mobile =
	// ConfigReader.getValue("Execution_Mobile");
	public static String strBrowser;
	DriverFactory driverFactory = DriverFactory.getInstance();

	@Override
	public void onFinish(ISuite arg0) {
		// driverFactory.getWebDriver().quit();
	}

	@Override
	public void onStart(ISuite arg0) {

	}

	
	public void onExecutionStart()  {
		//DeleteVideoPath.deleteVideo();
		//ScreenRecoderClass.startRecording("Recording");
		if (str_Execution_TYPE.equalsIgnoreCase("Web_UI")) {
			ReportManager.startReport();
		} else {
			System.out.println("[-] Please set exection type[Web_UI or Mobile_UI or API] value in cucumberhooks");
		}
	}

	@Override
	public void onExecutionFinish() {
		if (str_Execution_TYPE.equalsIgnoreCase("Web_UI")) {
			ReportManager.extent.setSystemInfo("Browser Name", SuiteEvent.getBrowser());
			ReportManager.extent.setSystemInfo("Browser Version", SuiteEvent.getVersion());
			ReportManager.endReport();
			//SlackNotification.sendSlackNotification("Web_UI");
		} else {
			System.out.println("[-] Please set exection type[Web_UI or Mobile_UI or API] value in cucumberhooks");
		}
	}

	@Override
	public void generateReport(List<XmlSuite> arg0, List<ISuite> arg1, String outputDirectory) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String csv = "./test.csv";

		try {
			writer = new CSVWriter(new FileWriter(csv));
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (ISuite iSuite : arg1) {
			Map<String, ISuiteResult> results = iSuite.getResults();
			Set<String> keys = results.keySet();
			for (String key : keys) {
				ITestContext context = results.get(key).getTestContext();
				IResultMap resultMap = context.getFailedTests();

				// -------------------------FAILED TEST CASE-----------------------------
				Collection<ITestNGMethod> failedMethods = resultMap.getAllMethods();
				for (ITestNGMethod iTestNGMethod : failedMethods) {
					Date DateTime = new Date(iTestNGMethod.getDate());
					String dateDate = dateFormat.format(DateTime);
					String[] array_DataTime = dateDate.split(" ");
					data.add(new String[] { array_DataTime[0], array_DataTime[1], iTestNGMethod.getMethodName(),
							iTestNGMethod.getDescription(), "FAIL" });
				}

				// -------------------------PASSED TEST CASE-----------------------------

				IResultMap resultMapPass = context.getPassedTests();
				Collection<ITestNGMethod> passMethods = resultMapPass.getAllMethods();
				for (ITestNGMethod iTestNGMethod : passMethods) {
					Date DateTime = new Date(iTestNGMethod.getDate());
					String dateDate = dateFormat.format(DateTime);
					String[] array_DataTime = dateDate.split(" ");
					data.add(new String[] { array_DataTime[0], array_DataTime[1], iTestNGMethod.getMethodName(),
							iTestNGMethod.getDescription(), "PASS" });

				}

			}

		}
		String[] header = { "Date", "Time", "Test Case ID", "Description", "Result" };
		writer.writeNext(header);
		writer.writeAll(data);
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getBrowser() {
		String browserName = null;
		if (str_Execution_TYPE.equalsIgnoreCase("Web_UI")) {
			Capabilities cap = ((RemoteWebDriver) DriverFactory.getInstance().getWebDriver()).getCapabilities();
			browserName = cap.getBrowserName().toLowerCase();

		}
		return browserName;

	}

	public static String getVersion() {
		String v = null;
		if (str_Execution_TYPE.equalsIgnoreCase("Web_UI")) {
			Capabilities cap = ((RemoteWebDriver) DriverFactory.getInstance().getWebDriver()).getCapabilities();
			v = cap.getVersion().toString();

		}
		return v;

	}

}
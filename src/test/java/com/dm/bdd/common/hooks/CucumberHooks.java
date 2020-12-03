package com.dm.bdd.common.hooks;

import java.util.ArrayList;

import com.dm.bdd.integrations.common_utils.ConfigReader;
import com.dm.bdd.integrations.common_utils.DriverFactory;
import com.dm.bdd.integrations.report_utils.ReportManager;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class CucumberHooks {
	public static ArrayList<String> passedTests = new ArrayList<String>();
	public static ArrayList<String> failedTests = new ArrayList<String>();
	public static ArrayList<String> totalTestCases = new ArrayList<String>();

	private static String str_Execution_TYPE = ConfigReader.getValue("Execution_type");
	public String str_BrowserType = ConfigReader.getValue("Browser");
	DriverFactory driverFactory = DriverFactory.getInstance();
	public static String strBrowser = "chrome";
	public static String featureFileName;

	@Before
	public void before(Scenario scenario) throws Exception {
		System.out.println("+++++++++++++++++++before hooks++++++++++++++++++");

		if (str_Execution_TYPE.equalsIgnoreCase("Web_UI")) {
			ReportManager.startTest(scenario.getName(), "SMOKE");
			System.out.println("Execution started @ " + strBrowser + " browser & for type : Web UI");
			try {
				driverFactory.setWebDriver(strBrowser.trim());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			throw new Exception(
					"[-] Please set exection type[Web_UI ] value in cucumberhooks class line number 28");
		}

		/**
		 * ReportManager.createAPI_Node(scenario.getName()); String str_Features =
		 * scenario.getId().split(";")[0]; String[] str_arryFeature =
		 * str_Features.split("features/"); System.out.println(str_arryFeature);
		 * String[] str_Feature = str_arryFeature[1].split(".feature"); featureFileName
		 * = str_Feature[0]; System.out.println(str_Feature[0]);
		 **/
	}

	@After
	public void after(Scenario scenario) throws Exception {
		System.out.println("+++++++++++++++++++after hooks+++++++++++++++++++");

		if (str_Execution_TYPE.equalsIgnoreCase("Web_UI")) {
			System.out.println("+++++++++++++++++++Web_UI+++++++++++++++++++");
			totalTestCases.add(scenario.getName());
			if (scenario.isFailed()) {
				failedTests.add(scenario.getName());
				ReportManager.logFail(scenario.getName() + " Test case Fail");
				System.out.println("Test Fail: " + scenario.getName());
			} else {
				System.out.println("+++++++++++++++++++++onTestSuccess++++++++++++++++++++");
				System.out.println("Test Success: " + scenario.getName());
				ReportManager.logPass(scenario.getName() + " Test case passed");
				ReportManager.endCurrentTest();
				driverFactory.getWebDriver().quit();
				passedTests.add(scenario.getName());
			}

		}else {
			throw new Exception(
					"[-] Please set exection type[Web_UI or Mobile_UI or API] value in cucumberhooks class line number 21");
		}

	}

}

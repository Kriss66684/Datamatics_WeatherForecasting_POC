package com.dm.bdd.common.runner_files;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.dm.bdd.integrations.NG_listners.SuiteEvent;
import com.dm.bdd.integrations.NG_listners.WebEvent;

import io.cucumber.testng.CucumberFeatureWrapper;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.PickleEventWrapper;
import io.cucumber.testng.TestNGCucumberRunner;

@Listeners({ SuiteEvent.class, WebEvent.class })
@CucumberOptions(features = { "src/test/java/com/dm/bdd/web/features" }, glue = { "com/dm/bdd/web/step_definitions",
		"com/dm/bdd/common/hooks" }, tags = { "@SmokeTest" }, plugin = { "pretty",
				"html:target/site/cucumber-pretty",
				"json:target/cucumber/cucumber.json" }, monochrome = true, strict = true, dryRun = false)
public class Web_Runner {

	private TestNGCucumberRunner testNGCucumberRunner;

	@BeforeClass(alwaysRun = true)
	public void setUpClass() throws Exception {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
		System.out.println("@Before class");
	}

	@Test(groups = "cucumber", description = "Run Cucumber Scenario", dataProvider = "scenarios")
	public void scenario(PickleEventWrapper pickleEventWrapper, CucumberFeatureWrapper cucumberFeatureWrapper)
			throws Throwable {
		testNGCucumberRunner.runScenario(pickleEventWrapper.getPickleEvent());
	}

	@DataProvider(parallel = false)
	public Object[][] scenarios() {
		System.out.println("@Data provider");
		return testNGCucumberRunner.provideScenarios();
	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass() {
		System.out.println("@Afterclass");
		testNGCucumberRunner.finish();
	}
}
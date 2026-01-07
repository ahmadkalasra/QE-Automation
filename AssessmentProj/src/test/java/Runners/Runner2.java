package Runners;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import org.testng.annotations.*;
import test.seleniumWrapper.ConfigHelper;
import test.seleniumWrapper.TestConstants;
import test.utilities.CustomizedTestNGCucumberRunner;

@SuppressWarnings("NewClassNamingConvention")
@CucumberOptions(
        features = "src/test/resources/TestFeatures",
        tags = "@Test2",
        glue = {"StepDefinitions", "test/hooks/webHooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-html-report2.html",
                "json:target/cucumber-reports/cucumber2.json", // Unique JSON name for Runner2
                "rerun:target/cucumber-reports/rerun2.txt",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        }
)
public final class Runner2 extends CustomizedTestNGCucumberRunner {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @Override
    // REMOVED invocationCount=5 unless you intentionally want to run every test 5 times
    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        super.runScenario(pickleWrapper, featureWrapper);
    }

    @Parameters("BrowserType")
    @BeforeTest
    public void beforeTest(@Optional("CHROME") String browser) {
        // Use the passed parameter, or default to CHROME if running via IDE/direct command
        ConfigHelper.setConfigValue(TestConstants.ConfigTypesKey.BROWSER, browser);
    }

    @AfterTest
    public void afterTest() {
        ConfigHelper.setConfigValue(TestConstants.ConfigTypesKey.BROWSER, "");
    }
}
package Runners;

import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.*;
import test.seleniumWrapper.ConfigHelper;
import test.seleniumWrapper.TestConstants;
import test.utilities.CustomizedTestNGCucumberRunner;

@SuppressWarnings("NewClassNamingConvention")
@CucumberOptions(
        features = "src/test/resources/TestFeatures",
        tags = "@Test3",
        glue = {"StepDefinitions", "test/hooks/webHooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-html-report3.html",
                "json:target/cucumber-reports/cucumber3.json", // Unique JSON for Runner3
                "rerun:target/cucumber-reports/rerun3.txt",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        }
)
public final class Runner3 extends CustomizedTestNGCucumberRunner {

        @Override
        @DataProvider(parallel = true)
        public Object[][] scenarios() {
                return super.scenarios();
        }

        @Parameters("BrowserType")
        @BeforeTest
        public void beforeTest(@Optional("CHROME") String browser) {
                // Sets the browser for the current test context
                ConfigHelper.setConfigValue(TestConstants.ConfigTypesKey.BROWSER, browser);
        }

        @AfterTest
        public void afterTest() {
                // Clean up the browser setting after test execution
                ConfigHelper.setConfigValue(TestConstants.ConfigTypesKey.BROWSER, "");
        }
}
package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import test.seleniumWrapper.ConfigHelper;
import test.seleniumWrapper.TestConstants;

@CucumberOptions(
        features = "src/test/resources/TestFeatures",
        glue = {"StepDefinitions", "test/hooks/webHooks"},
        plugin = {
                "pretty",
                "json:target/cucumber-reports/cucumber.json",
                "html:target/cucumber-reports/cucumber-html-report.html",
                "rerun:target/cucumber-reports/rerun.txt",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        }
)
public final class TestRunner extends AbstractTestNGCucumberTests {

    /**
     * Enable parallel execution at SCENARIO level.
     * This is the ONLY safe parallel layer with Cucumber + TestNG.
     */
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @BeforeTest(alwaysRun = true)
    public void beforeTest() {
        ConfigHelper.setConfigValue(
                TestConstants.ConfigTypesKey.BROWSER,
                System.getProperty("Browser", "CHROME")
        );

        ConfigHelper.setConfigValue(
                TestConstants.ConfigTypesKey.HEADLESS,
                System.getProperty("Headless", "true")
        );
    }
}
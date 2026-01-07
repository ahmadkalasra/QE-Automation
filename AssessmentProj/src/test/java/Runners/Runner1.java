package Runners;

import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.*;
import test.seleniumWrapper.ConfigHelper;
import test.seleniumWrapper.TestConstants;
import test.utilities.CustomizedTestNGCucumberRunner;

@SuppressWarnings("NewClassNamingConvention")
@CucumberOptions(
        features = "src/test/resources/TestFeatures",
        // Note: Keeping @Test1 here will override command line tags 
        // unless you use -Dcucumber.filter.tags
        tags = "@Test1", 
        glue = {"StepDefinitions", "test/hooks/webHooks"},
        plugin = {"pretty",
                "html:target/cucumber-reports/cucumber-html-report.html",
                "json:target/cucumber-reports/cucumber.json", // Path synced with reporting plugin
                "rerun:target/cucumber-reports/rerun.txt",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        }
)
public final class Runner1 extends CustomizedTestNGCucumberRunner {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @Parameters("BrowserType")
    @BeforeTest
    public void beforeTest(@Optional("CHROME") String browser) {
        // @Optional ensures that if you run without testng.xml, it doesn't fail
        // It prioritizes the TestNG XML parameter if present
        ConfigHelper.setConfigValue(TestConstants.ConfigTypesKey.BROWSER, browser);
    }

    @AfterTest
    public void afterTest() {
        ConfigHelper.setConfigValue(TestConstants.ConfigTypesKey.BROWSER, "");
    }
}
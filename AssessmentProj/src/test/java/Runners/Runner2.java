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
        plugin = {"pretty:target/cucumber-pretty.txt",
                "html:target/cucumber-html-report.html",
                "json:target/cucumber.json",
                "rerun:target/rerun.txt",
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
        @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios", invocationCount = 5)
        public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
                super.runScenario(pickleWrapper, featureWrapper);
        }

        @Parameters("BrowserType")
        @BeforeTest
        public void beforeTest(String browser) {
                ConfigHelper.setConfigValue(TestConstants.ConfigTypesKey.BROWSER,browser);
        }

        @AfterTest
        public void afterTest()
        {
                ConfigHelper.setConfigValue(TestConstants.ConfigTypesKey.BROWSER,"");
        }
}

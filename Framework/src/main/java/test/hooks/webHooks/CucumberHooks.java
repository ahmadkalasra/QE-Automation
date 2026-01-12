package test.hooks.webHooks;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.aventstack.extentreports.service.ExtentService;
import io.cucumber.java.*;
import lombok.SneakyThrows;
import org.apache.commons.lang3.EnumUtils;
import org.apache.logging.log4j.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import test.driverHelper.*;
import java.util.HashMap;
import test.seleniumWrapper.*;
import test.seleniumWrapper.TestConstants.Browser_Type;
import static test.seleniumWrapper.TestConstants.ConfigTypesKey.*;

public class CucumberHooks
{
    private static final Logger Log = LogManager.getLogger(CucumberHooks.class.getName());

    @SuppressWarnings("FieldMayBeFinal")
    private TestContext context;

    public CucumberHooks(TestContext testContext)
    {
        this.context = testContext;
    }

    @SuppressWarnings("unused")
    @BeforeStep
    public void beforeStep()
    {
        if (Log.isDebugEnabled()) {
            Log.info("Before Step %s".formatted(ExtentCucumberAdapter.getCurrentStep().toString()));
        }
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        try {
            WebDriver driver = DriverFactory.getDriver();
            if (driver == null) return;

            if (scenario.isFailed()) {
                scenario.attach(
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES),
                    "image/png",
                    scenario.getName()
                );
            }
        } catch (Exception e) {
            Log.warn("Skipping screenshot capture: {}", e.getMessage());
        }
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        context.setTestContext(scenario, null);
        
        // 1. Get value from Command Line (-DBrowserType)
        String browserStr = System.getProperty("BrowserType");

        // 2. If Command Line is empty, get from Config File (Browser=)
        if (browserStr == null || browserStr.isEmpty()) {
            browserStr = ConfigHelper.getConfigValue(BROWSER);
        }

        // 3. Hard fallback if both are empty to prevent NullPointerException
        if (browserStr == null || browserStr.isEmpty()) {
            Log.warn("No browser specified in CMD or Config. Defaulting to CHROME.");
            browserStr = "CHROME";
        }

        Log.info("Final Browser Decision: " + browserStr);

        var driverHelper = new DriverHelper();
        
        // Use getEnumIgnoreCase to be safe with "CHROME" vs "Chrome"
        Browser_Type type = EnumUtils.getEnumIgnoreCase(Browser_Type.class, browserStr);
        
        if (type == null) {
            throw new RuntimeException("Invalid Browser Type: [" + browserStr + "]. Please check your spelling.");
        }

        WebDriver driver = driverHelper.InvokeDriverInstance(type);
        DriverFactory.setDriver(driver);
        context.setTestContext(scenario, DriverFactory.getDriver());
    }

    @SneakyThrows
    @After
    public void afterScenario(Scenario scenario) {
        DriverFactory.QuitDriverInstance();
    }

    @SuppressWarnings("unused")
    @AfterAll
    public static void after_all() {
        var sysInfo = new HashMap<String, String>();
        // Pull from the system property first for the report
        String reportBrowser = System.getProperty("BrowserType", ConfigHelper.getConfigValue(BROWSER));
        sysInfo.put("Browser Name", reportBrowser);
        sysInfo.put("Browser Version", DriverFactory.getBrowserVersion());
        
        sysInfo.forEach((k, v) -> {
            if (v != null) ExtentService.getInstance().setSystemInfo(k, v);
        });
    }
}

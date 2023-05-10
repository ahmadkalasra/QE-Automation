package test.hooks.webHooks;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.aventstack.extentreports.service.ExtentService;
import io.cucumber.java.*;
import lombok.SneakyThrows;
import org.apache.commons.lang3.EnumUtils;
import org.apache.logging.log4j.*;
import test.driverHelper.*;
import java.util.HashMap;
import test.seleniumWrapper.*;
import static test.seleniumWrapper.TestConstants.ConfigTypesKey.*;
import static test.seleniumWrapper.TestConstants.*;

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
        Log.info("Before Step %s".formatted(ExtentCucumberAdapter.getCurrentStep().toString()));
    }

    @SuppressWarnings("unused")
    @AfterStep
    public void AfterStep(Scenario scenario) {
        scenario.attach(DriverFactory.GetScreenShot(),"image/png", scenario.getName());
    }


    @Before
    public void beforeScenario(Scenario scenario)
    {
        context.setTestContext(scenario, null);
        var driverHelper = new DriverHelper();
        var driver = driverHelper.InvokeDriverInstance(EnumUtils.getEnumIgnoreCase(Browser_Type.class, ConfigHelper.getConfigValue(BROWSER)));
        DriverFactory.setDriver(driver);
        context.setTestContext(scenario, DriverFactory.getDriver());
    }

    @SneakyThrows
    @After
    public synchronized void afterScenario(Scenario scenario) {
        DriverFactory.QuitDriverInstance();
    }

    @SuppressWarnings("unused")
    @AfterAll
    public static void after_all()
    {
        var sysInfo = new HashMap<String,String>();
        sysInfo.put("Browser Name", ConfigHelper.getConfigValue(BROWSER));
        sysInfo.put("Browser Version", DriverFactory.getBrowserVersion());
        sysInfo.forEach((k,v) -> ExtentService.getInstance().setSystemInfo(k, v));
    }
}

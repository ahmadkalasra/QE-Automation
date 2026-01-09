package test.driverHelper;

import java.time.Duration;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import test.seleniumWrapper.ConfigHelper;
import test.seleniumWrapper.TestConstants;

public class DriverHelper
{
    private final ChromeDriverHelper chromeDriverHelper = new ChromeDriverHelper();

    private static final Logger Log = LogManager.getLogger(DriverHelper.class.getName());
    public WebDriver InvokeDriverInstance(TestConstants.Browser_Type browser) {
        try {
            var driver = switch (browser) {
                case CHROME -> chromeDriverHelper.CreateChromeDriver();
            };

            if (driver == null) throw new RuntimeException("Driver creation returned null.");

            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();

            // Safe parsing for timeouts with defaults
            long implicitWait = Long.parseLong(Optional.ofNullable(ConfigHelper.getConfigValue(TestConstants.ConfigTypesKey.IMPLICIT_WAIT_TIMEOUT)).orElse("20"));
            long pageLoad = Long.parseLong(Optional.ofNullable(ConfigHelper.getConfigValue(TestConstants.ConfigTypesKey.PAGE_LOAD_TIMEOUT)).orElse("50"));

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoad));
            
            return driver;
        } catch (Exception ex) {
            Log.error("WebDriver initialization failed: " + ex.getMessage());
            return null; // This triggers the "Fatal: WebDriver instance is null" in Factory
        }
    }
}

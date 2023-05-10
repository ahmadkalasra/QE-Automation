package test.driverHelper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import test.seleniumWrapper.ConfigHelper;
import test.seleniumWrapper.TestConstants;

import java.time.Duration;
import java.util.Objects;


public class DriverHelper
{
    private final ChromeDriverHelper chromeDriverHelper = new ChromeDriverHelper();

    private static final Logger Log = LogManager.getLogger(DriverHelper.class.getName());
    public WebDriver InvokeDriverInstance(TestConstants.Browser_Type browser)
    {
        try
        {
            var driver = switch (browser)
            {
                case CHROME -> chromeDriverHelper.CreateChromeDriver();
            };
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(
                    Objects.requireNonNull(ConfigHelper.getConfigValue(TestConstants.ConfigTypesKey.IMPLICIT_WAIT_TIMEOUT)))));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Long.parseLong(
                    Objects.requireNonNull(ConfigHelper.getConfigValue(TestConstants.ConfigTypesKey.PAGE_LOAD_TIMEOUT)))));
            return driver;
        }
        catch (Exception ex)
        {
            Log.error("WebDriver object cant be initialised due to %s".formatted(ex.getMessage()));
            return null;
        }
    }
}

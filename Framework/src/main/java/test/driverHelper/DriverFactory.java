package test.driverHelper;

import org.apache.logging.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ThreadGuard;

public class DriverFactory
{
    private static String browserName;
    private static String browserVersion;
    private static final Logger Log = LogManager.getLogger(DriverFactory.class.getName());
    private DriverFactory(){}

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver()
    {
        return driver.get();
    }

    public static void setDriver(WebDriver driverType)
    {
        driver.set(ThreadGuard.protect(driverType));
        browserName = ((RemoteWebDriver)driverType).getCapabilities().getBrowserName();
        browserVersion = ((RemoteWebDriver) driverType).getCapabilities().getBrowserVersion();
        Log.info("%s WebDriver started successfully with browser version set as %s on Thread : %s".formatted(browserName, browserVersion, Thread.currentThread().threadId()));
    }

    public static String getBrowserVersion()
    {
        return browserVersion;
    }

    public static byte[] GetScreenShot()
    {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @SuppressWarnings("unused")
    public static String GetScreenShotAsBase64()
    {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
    }

    public static void QuitDriverInstance()
    {
        if(driver.get() == null)
        {
            Log.info("Driver Instance already Killed");
            return;
        }
        try
        {
            driver.get().quit();
            Log.info("Quit %s WebDriver successfully running on Thread : %s".formatted(browserName, Thread.currentThread().threadId()));
            driver.remove();
        }
        catch (Exception e)
        {
            Log.error("Unable to Quit %s WebDriver due to %s".formatted(browserName,e.getMessage()));
        }
    }
}

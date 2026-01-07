package test.driverHelper;

import org.apache.logging.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ThreadGuard;

public class DriverFactory {
    private static final ThreadLocal<String> browserName = new ThreadLocal<>();
    private static final ThreadLocal<String> browserVersion = new ThreadLocal<>();
    private static final Logger Log = LogManager.getLogger(DriverFactory.class);
    
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverFactory(){}

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driverType) {
        if (driverType == null) {
            throw new RuntimeException("Fatal: WebDriver instance is null! Check DriverHelper or your -D command line arguments.");
        }
        
        driver.set(ThreadGuard.protect(driverType));
        
        Capabilities caps = ((RemoteWebDriver) driverType).getCapabilities();
        browserName.set(caps.getBrowserName());
        browserVersion.set(caps.getBrowserVersion());
        
        Log.info("{} WebDriver started. Version: {} on Thread: {}", 
            browserName.get(), browserVersion.get(), Thread.currentThread().threadId());
    }

    public static String getBrowserVersion() {
        return browserVersion.get();
    }

    public static byte[] GetScreenShot() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public static void QuitDriverInstance() {
        if (driver.get() != null) {
            try {
                driver.get().quit();
                Log.info("Quit WebDriver successfully on Thread: {}", Thread.currentThread().threadId());
            } catch (Exception e) {
                Log.error("Unable to Quit WebDriver: {}", e.getMessage());
            } finally {
                driver.remove();
                browserName.remove();
                browserVersion.remove();
            }
        }
    }
}
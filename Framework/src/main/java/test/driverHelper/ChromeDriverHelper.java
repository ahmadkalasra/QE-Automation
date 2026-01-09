package test.driverHelper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import test.seleniumWrapper.ConfigHelper;
import test.seleniumWrapper.TestConstants;

public class ChromeDriverHelper
{
    public WebDriver CreateChromeDriver()
    {
        String headlessConfig = ConfigHelper.getConfigValue(TestConstants.ConfigTypesKey.HEADLESS);
        String chromeMode = (headlessConfig != null && headlessConfig.equalsIgnoreCase("true")) ? "headless" : "gui";
        return SetupChromeDriver(chromeMode);
    }

    public WebDriver CreateChromeHeadlessDriver()
    {
        return SetupChromeDriver("Headless");
    }

    public WebDriver CreateChromeIncognitoDriver()
    {
        return SetupChromeDriver("Incognito");
    }

    public WebDriver CreateChromeGalaxyS20Driver()
    {
        return SetupChromeDriver("Samsung Galaxy S20");
    }

    public WebDriver CreateChromeIPhone12ProDriver(){ return SetupChromeDriver("iPhone 12 Pro"); }

    public WebDriver CreateChromeIPadProDriver(){ return SetupChromeDriver("iPad Pro"); }

    private WebDriver SetupChromeDriver(String chromeType) {
        // Let Selenium Manager resolve and download the matching ChromeDriver automatically
        // (remove explicit WebDriverManager usage to avoid cache/version mismatches)
        
        ChromeOptions chromeOption = new ChromeOptions();
        chromeOption.addArguments("--remote-allow-origins=*");
        chromeOption.addArguments("--start-maximized");
        chromeOption.addArguments("--disable-notifications");
        chromeOption.addArguments("--disable-dev-shm-usage"); // Prevents crashes in high-concurrency
        chromeOption.addArguments("--no-sandbox"); 
        
        chromeType = chromeType.toLowerCase();

        if (chromeType.equals("headless")) {
            chromeOption.addArguments("--headless=new");
            chromeOption.addArguments("--window-size=1920,1080");
        }
        
        // Add logic for other types if necessary (Incognito, Mobile Emulation)
        if (chromeType.equalsIgnoreCase("incognito")) {
            chromeOption.addArguments("--incognito");
        }

        return new ChromeDriver(chromeOption);
    }
}

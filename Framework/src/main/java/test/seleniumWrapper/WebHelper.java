package test.seleniumWrapper;

import lombok.SneakyThrows;
import org.apache.logging.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class WebHelper {
    protected WebDriver driver;
    private TestConstants.Locator_Type elementLocatorType;
    private String elementLocatorInfo;
    private final JavascriptExecutor javascriptExecutor;

    private static final Logger Log = LogManager.getLogger(WebHelper.class.getName());

    public WebHelper(WebDriver driver) {
        this.driver = driver;
        this.javascriptExecutor = (JavascriptExecutor)this.driver;
    }

    public void Navigate(String url)
    {
        try {
            driver.navigate().to(url);
            Log.info("Driver successfully navigated to : %s".formatted(url));
        }
        catch (Exception ex)
        {
            Log.error("Driver failed to navigate to url due to %s".formatted(ex.getMessage()));
        }
    }

    @SneakyThrows
    public WebElement InitializeWebElement(TestConstants.Locator_Type locatorType, String elementLocatorInfo)
    {
        elementLocatorType = locatorType;
        this.elementLocatorInfo = elementLocatorInfo;
        var dWait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(
                Objects.requireNonNull(ConfigHelper.getConfigValue(TestConstants.ConfigTypesKey.OBJECT_IDENTIFICATION_TIMEOUT)))));
        try {
            WebElement element;
            switch (locatorType) {
                case XPATH -> element = dWait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementLocatorInfo)));
                case CSS_SELECTOR -> element =
                        dWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(elementLocatorInfo)));
                default -> throw new TypeNotPresentException(elementLocatorType.getClass().getName(),null);
            }
            Log.info("WebElement %s is identified successfully".formatted(element.getText()));
            return element;
        }
        catch (StaleElementReferenceException | ElementNotInteractableException | UnhandledAlertException ex)
        {
            Log.info("WebElement %s identification error due to %s handled".formatted(this.elementLocatorInfo, ex.getMessage()));
            return InitializeWebElement(elementLocatorType, this.elementLocatorInfo);
        }
        catch (Exception exception)
        {
            var strTemp = elementLocatorInfo + " - failed to identify.";
            Log.error("%s%s%s".formatted(strTemp, System.lineSeparator(), exception.getMessage()));
            return null;
        }
    }

    public void PerformAction(WebElement objWebElement, TestConstants.WebDriverAction webDriverAction, String actionData)
    {
        try
        {
            switch (webDriverAction) {
                case INPUT -> {
                    objWebElement.click();
                    objWebElement.sendKeys(actionData);
                    Log.info("Enter the text %s to Text Box %s".formatted(actionData, objWebElement.getText()));
                }
                case CLICK -> {
                    Objects.requireNonNull(objWebElement).click();
                    Log.info("Click button %s".formatted(objWebElement.getText()));
                }
                default -> {
                }
            }
        }
        catch (ElementNotInteractableException | StaleElementReferenceException exception){
            WebElement element = InitializeWebElement(elementLocatorType, elementLocatorInfo);
            PerformAction(element, webDriverAction, actionData);
        } 
        catch (Exception exception){
            Assert.fail("Failed to perform action - "+ exception.getMessage());
        }
    }

    public List<WebElement> InitialiseWebElementsList(TestConstants.Locator_Type locatorType, String locatorInfo)
    {
        elementLocatorType = locatorType;
        this.elementLocatorInfo = locatorInfo;
        return  switch (locatorType)
        {
            case CSS_SELECTOR -> driver.findElements(By.cssSelector(locatorInfo));
            case XPATH -> driver.findElements(By.xpath(locatorInfo));
        };
    }
    
    public void Get_PageReady()
    {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(
                    Objects.requireNonNull(ConfigHelper.getConfigValue(TestConstants.ConfigTypesKey.OBJECT_IDENTIFICATION_TIMEOUT))))).
                    until(d -> javascriptExecutor.executeScript("return document.readyState").equals("complete"));
        }
        catch (UnhandledAlertException e) {
            Get_PageReady();
        }
        catch (Exception ex)
        {
            Log.error("Unable to get Page Ready state due to %s".formatted(ex.getMessage()));
        }
    }

    public WebElement FindWebElementCSS_Selector_Without_Wait(String cssSelector) {
        Get_PageReady();
        elementLocatorInfo = cssSelector;
        try {
            var jScript = "return document.querySelector(\"" + cssSelector + "\")";
            var webElementFound = (WebElement) javascriptExecutor.executeScript(jScript);
            Log.info(
                    webElementFound != null
                            ? "The WebElement with CssSelector "+cssSelector+" is found on the DOM"
                            : "The WebElement with CssSelector "+cssSelector+" is not found on the DOM");
            return webElementFound;
        }
        catch (Exception ex)
        {
            Log.error("The WebElement with CssSelector as %s cant be found due to %s".formatted(cssSelector, ex.getMessage()));
            return null;
        }
    }

    public String GetElementText(WebElement element) {
        String elementText;
        try {
            elementText = element.getText();
        }
        catch (Exception ex) {
            Log.error("WebElement %s threw exception %s while fetching the visible text".formatted(element, ex.getMessage()));
            elementText = InitializeWebElement(elementLocatorType, elementLocatorInfo).getText();
        }
        Log.info("The visible text for the webElement is %s".formatted(elementText));
        return elementText;
    }

    public String GetElementAttribute(WebElement element, String attributeName) {
        String webAttributeValue;
        try {
            webAttributeValue = element.getAttribute(attributeName);
        }
        catch (Exception ex) {
            webAttributeValue = InitializeWebElement(elementLocatorType, elementLocatorInfo).getCssValue(attributeName);
        }
        Log.info("The Web Attribute Value for the Attribute %s of the webElement is %s".formatted(attributeName, webAttributeValue));
        return webAttributeValue;
    }
}
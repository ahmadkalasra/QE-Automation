package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.seleniumWrapper.WebHelper;
import static test.seleniumWrapper.TestConstants.Locator_Type.*;
import static test.seleniumWrapper.TestConstants.WebDriverAction.*;
import static ApplicationConstants.TestConstants.XPath.ShopPage.*;

public class ShopPage
{
    private final WebHelper _webHelper;

    protected WebElement LabelProductPrice;
    protected WebElement ButtonBuyProduct;

    public ShopPage(WebDriver driver) {
        _webHelper = new WebHelper(driver);
    }

    public void BuyItem(String productName) {
        _webHelper.PerformAction(getButtonBuyProduct(productName), CLICK, null);
    }

    public String GetProductPrice(String itemName) {
        return _webHelper.GetElementText(getLabelProductPrice(itemName));
    }

    private WebElement getButtonBuyProduct(String productName) {
        return ButtonBuyProduct =  _webHelper.InitializeWebElement(XPATH, String.join("",
                XPATH_PRE_BUTTON_PRODUCT_BUY, productName, XPATH_POST_BUTTON_PRODUCT_BUY));
    }

    private WebElement getLabelProductPrice(String productName) {
        return LabelProductPrice =  _webHelper.InitializeWebElement(XPATH, String.join("",
                XPATH_PRE_LABEL_PRODUCT_PRICE, productName, XPATH_POST_LABEL_PRODUCT_PRICE));
    }
}

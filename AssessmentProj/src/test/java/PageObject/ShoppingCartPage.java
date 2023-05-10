package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.seleniumWrapper.WebHelper;
import java.util.List;
import static test.seleniumWrapper.TestConstants.Locator_Type.*;
import static ApplicationConstants.TestConstants.XPath.ShopPage.*;

public class ShoppingCartPage {

    private final WebHelper _webHelper;

    protected List<WebElement> Label_Cart_ItemName;
    protected List<WebElement> Label_Cart_ItemPrice;
    protected List<WebElement> Label_Cart_ItemSubTotal;
    protected List<WebElement> TxtBox_Cart_ItemQuantity;
    protected WebElement Label_TotalAmount;

    public ShoppingCartPage(WebDriver driver)
    {
        _webHelper = new WebHelper(driver);
    }

    private List<WebElement> getLabel_Cart_ItemName() {
        return Label_Cart_ItemName =  _webHelper.InitialiseWebElementsList(XPATH, XPATH_LABEL_CART_ITEM_NAME);
    }

    private List<WebElement> getLabel_Cart_ItemPrice() {
        return Label_Cart_ItemPrice =  _webHelper.InitialiseWebElementsList(XPATH, XPATH_LABEL_CART_ITEM_PRICE);
    }

    private List<WebElement> getLabel_Cart_ItemSubTotal() {
        return Label_Cart_ItemSubTotal =  _webHelper.InitialiseWebElementsList(XPATH, XPATH_LABEL_CART_SUBTOTAL);
    }

    private List<WebElement> getTxtBox_Cart_ItemQuantity() {
        return TxtBox_Cart_ItemQuantity =  _webHelper.InitialiseWebElementsList(XPATH, XPATH_INPUT_CART_ITEM_QUANTITY);
    }

    private WebElement getLabel_TotalAmount() {
        return Label_TotalAmount =  _webHelper.InitializeWebElement(XPATH, XPATH_LABEL_TOTAL);
    }

    public String GetLabelValue(WebElement element) {
        return _webHelper.GetElementText(element);
    }

    public boolean IsPriceCorrectForItem(String itemName, String itemPrice) {
        var flag = false;
        for (int i = 0; i < getLabel_Cart_ItemName().size(); i++) {
            if (GetLabelValue(getLabel_Cart_ItemName().get(i)).contains(itemName)){
                if (GetLabelValue(getLabel_Cart_ItemPrice().get(i)).contains(itemPrice))
                    flag=true;
                break;
            }
        }
        return flag;
    }

    public boolean IsQuantityCorrectForItem(String itemName, String itemQuantity) {
        var flag = false;
        for (int i = 0; i < getLabel_Cart_ItemName().size(); i++) {
            if (GetLabelValue(getLabel_Cart_ItemName().get(i)).contains(itemName)){
                if (_webHelper.GetElementAttribute(getTxtBox_Cart_ItemQuantity().get(i),
                        "value").contains(itemQuantity))
                    flag=true;
                break;
            }
        }
        return flag;
    }

    public boolean IsSubTotalCorrectForItem(String itemName, String itemSubTotal) {
        var flag = false;
        for (int i = 0; i < getLabel_Cart_ItemName().size(); i++) {
            if (GetLabelValue(getLabel_Cart_ItemName().get(i)).contains(itemName)){
                if (GetLabelValue(getLabel_Cart_ItemSubTotal().get(i)).contains(itemSubTotal))
                    flag=true;
                break;
            }
        }
        return flag;
    }

    public String GetSumOfAllSubTotals() {
        var total = 0.00;
        for (WebElement element: getLabel_Cart_ItemSubTotal()) {
            total+=Double.parseDouble(GetLabelValue(element).replace("$",""));
        }
        return String.valueOf(total);
    }

    public String GetTotalAmount() {
        return _webHelper.GetElementText(getLabel_TotalAmount());
    }
}

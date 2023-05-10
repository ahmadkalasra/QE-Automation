package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.seleniumWrapper.WebHelper;
import static test.seleniumWrapper.TestConstants.Locator_Type.*;
import static test.seleniumWrapper.TestConstants.WebDriverAction.*;

public class HomePage {
    private final WebHelper _webHelper;
    protected WebElement BtnNavBar;
    protected WebElement BtnHome;
    protected WebElement BtnShop;
    protected WebElement BtnContact;
    protected WebElement BtnLogin;
    protected WebElement BtnCart;
    protected WebElement BtnStartShopping;
    protected WebElement LabelTitle;

    public HomePage(WebDriver driver) {
        _webHelper = new WebHelper(driver);
    }

    private WebElement getBtnNavBar() {
        return BtnNavBar = _webHelper.FindWebElementCSS_Selector_Without_Wait("[class$='navbar']");
    }

    public void GetPageReady() {
        _webHelper.Get_PageReady();
    }

    private WebElement getBtnHome() {
        return BtnHome = _webHelper.InitializeWebElement(CSS_SELECTOR,"#nav-home a");
    }

    private WebElement getBtnShop() {
        return  BtnShop = _webHelper.InitializeWebElement(CSS_SELECTOR,"#nav-shop a");
    }

    private WebElement getBtnContact() {
        return BtnContact = _webHelper.InitializeWebElement(CSS_SELECTOR,"#nav-contact a");
    }

    private WebElement getBtnLogin() {
        return BtnLogin = _webHelper.InitializeWebElement(CSS_SELECTOR,"#nav-login a");
    }

    private WebElement getBtnCart() {
        return  BtnCart = _webHelper.InitializeWebElement(CSS_SELECTOR,"#nav-cart a");
    }

    private WebElement getBtnStartShopping() {
        return BtnStartShopping = _webHelper.InitializeWebElement(CSS_SELECTOR,"#btn btn-success btn-large");
    }

    private WebElement getLabelTitle() {
        return LabelTitle = _webHelper.InitializeWebElement(CSS_SELECTOR,".hero-unit h1");
    }

    @SuppressWarnings("unused")
    public boolean IsHomePageDisplayed()
    {
        return _webHelper.GetElementText(getLabelTitle()).equals("Jupiter Toys");
    }

    @SuppressWarnings("unused")
    public void ClickHomeButton()
    {
        _webHelper.PerformAction(getBtnHome(), CLICK,null);
    }

    public void ClickShopButton() {
        _webHelper.PerformAction(getBtnShop(), CLICK,null);
    }

    public void ClickContactButton()
    {
        _webHelper.PerformAction(getBtnContact(), CLICK, null);
    }

    @SuppressWarnings("unused")
    public void ClickLoginButton()
    {
        _webHelper.PerformAction(getBtnLogin(), CLICK, null);
    }

    public void ClickCartButton() {
        _webHelper.PerformAction(getBtnCart(), CLICK, null);
    }

    @SuppressWarnings("unused")
    public void ClickStartShoppingButton()
    {
        _webHelper.PerformAction(getBtnStartShopping(), CLICK, null);
    }

    @SuppressWarnings("unused")
    public String GetCartItems()
    {
        return _webHelper.GetElementText(getBtnCart());
    }
}

package StepDefinitions;

import PageObject.*;
import io.cucumber.java.en.*;
import org.testng.Assert;
import test.seleniumWrapper.*;
import static ApplicationConstants.TestConstants.ScenarioContextKey.*;

public final class ShoppingTestSteps {

    private final HomePage homePage;
    private final ShopPage shopPage;
    private final ShoppingCartPage shoppingCartPage;
    private final ScenarioContext scenarioContext;

    public ShoppingTestSteps(TestContext testContext)
    {
        var driver = testContext.getDriver();
        homePage = new HomePage(driver);
        shopPage = new ShopPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
        scenarioContext = testContext.getScenarioContext();
    }

    @When("The user navigates to shop page")
    public void theUserNavigatesToShopPage() {
        homePage.ClickShopButton();
        homePage.GetPageReady();
    }

    @When("The user buys {string}")
    public void the_user_buys_products(String productsBought) {
        var itemsPurchased = 0;
        var products = productsBought.split(",");
        scenarioContext.setContext(SC_KEY_NO_OF_PRODUCTS.name(), products.length);
        for (var i=0; i<products.length; i++) {
            var productQuantity = Integer.parseInt(products[i].trim().split("-")[0]);
            for (int j=0; j<productQuantity; j++){
                shopPage.BuyItem(products[i].trim().split("-")[1]);
                itemsPurchased+=1;
            }
            scenarioContext.setContext(SC_KEY_TOTAL_NO_OF_ITEMS_PURCHASED.name()+(i+1), itemsPurchased);
            scenarioContext.setContext(SC_KEY_PRODUCT_NAME.name()+(i+1), products[i].trim().split("-")[1]);
            scenarioContext.setContext(SC_KEY_PRODUCT_PRICE.name()+(i+1), shopPage.GetProductPrice(products[i].trim().split("-")[1]));
            scenarioContext.setContext(SC_KEY_QUANTITY_PER_PRODUCT.name()+(i+1), productQuantity);
        }
    }

    @When("The user goes to the shopping cart")
    public void the_user_goes_to_the_shopping_cart() {
        homePage.ClickCartButton();
        homePage.GetPageReady();
    }

    @Then("The user verifies that subtotal for each product is correct")
    public void theUserVerifiesThatSubtotalForEachProductIsCorrect() {
        for (int i = 1; i <= Integer.parseInt(scenarioContext.getContext(SC_KEY_NO_OF_PRODUCTS.name()).toString()); i++) {
            var itemName = scenarioContext.getContext((SC_KEY_PRODUCT_NAME.name()+(i))).toString();
            var productQuantity = Integer.parseInt(scenarioContext.getContext(SC_KEY_QUANTITY_PER_PRODUCT.name()+(i)).toString().replace("$", ""));
            var productPrice = Double.parseDouble(scenarioContext.getContext(SC_KEY_PRODUCT_PRICE.name()+(i)).toString().replace("$", ""));
            Assert.assertTrue(shoppingCartPage.IsSubTotalCorrectForItem(itemName,
                    String.valueOf(productPrice*productQuantity)),
                    "Error - Subtotal is incorrect for item: "+itemName);
        }
    }

    @And("The user verifies the price for each product")
    public void theUserVerifiesThePriceForEachProduct() {
        for (int i = 1; i <= Integer.parseInt(scenarioContext.getContext(SC_KEY_NO_OF_PRODUCTS.name()).toString()); i++) {
            var itemName = scenarioContext.getContext((SC_KEY_PRODUCT_NAME.name()+(i))).toString();
            var itemPrice = scenarioContext.getContext(SC_KEY_PRODUCT_PRICE.name()+(i)).toString().replace("$", "");
            Assert.assertTrue(shoppingCartPage.IsPriceCorrectForItem(itemName, itemPrice),
                    "Error - Subtotal is incorrect for item: "+itemName);
        }
    }

    @And("The user verifies that total payable amount is equal to sum of all subtotals")
    public void theUserVerifiesThatTotalPayableAmountIsEqualToSumOfAllSubtotals() {
        Assert.assertEquals(shoppingCartPage.GetSumOfAllSubTotals(),
                shoppingCartPage.GetTotalAmount().split(" ")[1],
                "Error - Total Amount is incorrect!");
    }
}
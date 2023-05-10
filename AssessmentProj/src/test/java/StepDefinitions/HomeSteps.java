package StepDefinitions;

import PageObject.HomePage;
import SetUp.SetAppUrl;
import io.cucumber.java.en.*;
import test.seleniumWrapper.*;

public class HomeSteps {
    private final WebHelper webHelper;
    private final HomePage homePage;

    public HomeSteps(TestContext testContext) {
        var driver = testContext.getDriver();
        webHelper = new WebHelper(driver);
        homePage = new HomePage(driver);
    }

    @Given("The user navigates to Jupitor Toys application")
    public void navigates_to_jupitor_toys_application() {
        webHelper.Navigate(getUrl());
    }

    @Given("The user navigates to Jupiter Toys home page")
    public void theUserNavigatesToJupiterToysHomePage() {
        homePage.ClickHomeButton();
    }

    @When("The user navigates to contact page")
    public void theUserNavigatesToContactPage() {
        homePage.ClickContactButton();
    }

    private String getProtocol() {
        return ConfigHelper.getConfigValue(TestConstants.ConfigTypesKey.PROTOCOL);
    }

    private String getUrl() {
        return SetAppUrl.SetUrl(getProtocol());
    }
}

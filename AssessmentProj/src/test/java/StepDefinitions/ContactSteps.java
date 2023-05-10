package StepDefinitions;

import PageObject.ContactPage;
import io.cucumber.java.en.*;
import org.testng.Assert;
import test.seleniumWrapper.*;
import static ApplicationConstants.TestConstants.ScenarioContextKey.*;

public class ContactSteps {

    private final ContactPage contactPage;
    private final ScenarioContext scenarioContext;

    public ContactSteps(TestContext testContext) {
        var driver = testContext.getDriver();
        contactPage = new ContactPage(driver);
        scenarioContext = testContext.getScenarioContext();
    }

    @And("The user click submit button")
    public void theUserClickSubmitButton() {
        contactPage.ClickSubmitButton();
    }

    @Then("The user verifies error messages are displayed")
    public void theUserVerifiesErrorMessagesAreDisplayed() {
        Assert.assertEquals(contactPage.GetForeNameError(), "Forename is required", "Error - Forename error message is incorrect!");
        Assert.assertEquals(contactPage.GetEmailError(), "Email is required", "Error - Email error message is incorrect!");
        Assert.assertEquals(contactPage.GetMessageError(), "Message is required", "Error - Message text box - error message is incorrect!");
    }

    @And("The user populates mandatory fields")
    public void theUserPopulatesMandatoryFields() {
        var ForeName = "TestForeName";
        contactPage.EnterForeName(ForeName);
        scenarioContext.setContext(SC_KEY_FORE_NAME.name(), ForeName);
        contactPage.EnterSurName("testSurName");
        contactPage.EnterEmail("test@testing.com");
        contactPage.EnterTelephone("0404111222");
        contactPage.EnterMessage("Test Message");
    }

    @Then("The user validates errors messages are gone")
    public void theUserValidatesErrorsMessagesAreGone() {
        Assert.assertFalse(contactPage.IsForeNameErrorDisplayed(), "Error - ForeName Error is displayed!");
        Assert.assertFalse(contactPage.IsEmailErrorDisplayed(), "Error - Email Error is displayed!");
        Assert.assertFalse(contactPage.IsMessageErrorDisplayed(), "Error - Message Error is displayed!");
    }

    @Then("The user validates successful submission message")
    public void theUserValidatesSuccessfulSubmissionMessage() {
        Assert.assertEquals(contactPage.GetFeedbackSubmissionSuccessMessage(),
                "Thanks "+scenarioContext.getContext(SC_KEY_FORE_NAME.name()).toString()+
                        ", we appreciate your feedback."
                );
    }
}

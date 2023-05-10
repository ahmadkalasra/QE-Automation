package PageObject;

import org.openqa.selenium.*;
import test.seleniumWrapper.*;
import java.util.Objects;
import static ApplicationConstants.TestConstants.CSS_Selector.ContactPage.*;
import static test.seleniumWrapper.TestConstants.Locator_Type.*;
import static test.seleniumWrapper.TestConstants.WebDriverAction.*;

public class ContactPage
{
    private final WebHelper _webHelper;
    protected WebElement LabelFeedback;
    protected WebElement TxtBoxForeName;
    protected WebElement LabelForeNameError;
    protected WebElement TxtBoxSurname;
    protected WebElement TxtBoxEmail;
    protected WebElement LabelEmailError;
    protected WebElement TxtBoxTelephone;
    protected WebElement TxtBoxMessage;
    protected WebElement LabelMessageError;
    protected WebElement BtnSubmit;
    protected WebElement AlertFormSubmission;
    protected WebElement LabelFeedbackSubmissionSuccessMessage;

    public ContactPage(WebDriver driver) {
        _webHelper = new WebHelper(driver);
    } 

    public void EnterForeName(String foreName) {
        _webHelper.PerformAction(getTxtBoxForeName(), INPUT, foreName);
    }

    public void EnterSurName(String surName) {
        _webHelper.PerformAction(getTxtBoxSurname(), INPUT, surName);
    }

    public void EnterEmail(String eMail) {
        _webHelper.PerformAction(getTxtBoxEmail(), INPUT, eMail);
    }

    public void EnterTelephone(String telephone) {
        _webHelper.PerformAction(getTxtBoxTelephone(), INPUT, telephone);
    }

    public void EnterMessage(String message) {
        _webHelper.PerformAction(getTxtBoxMessage(), INPUT, message);
    }

    public void ClickSubmitButton()
    {
        _webHelper.PerformAction(getBtnSubmit(), CLICK, null);
    }

    public String GetForeNameError() {
        return _webHelper.GetElementText(getLabelForeNameError());
    }

    public boolean IsForeNameErrorDisplayed() {
        return getLabelForeNameError()!=null;
    }

    public String GetEmailError() {
        return _webHelper.GetElementText(getLabelEmailError());
    }

    public boolean IsEmailErrorDisplayed() {
        return getLabelEmailError()!=null;
    }

    public String GetMessageError() {
        return _webHelper.GetElementText(getLabelMessageError());
    }

    public boolean IsMessageErrorDisplayed() {
        return getLabelMessageError()!=null;
    }

    public boolean FeedbackSubmissionProgress() {
        return _webHelper.GetElementText(getAlertFormSubmission()).equals("Sending Feedback");
    }

    public String GetFeedbackSubmissionSuccessMessage() {
        return _webHelper.GetElementText(getLabelFeedbackSubmissionSuccessMessage());
    }

    private WebElement getLabelFeedback() {
        return LabelFeedback = _webHelper.InitializeWebElement(CSS_SELECTOR,"#header-message div");
    }

    private WebElement getTxtBoxForeName() {
        return TxtBoxForeName = _webHelper.InitializeWebElement(CSS_SELECTOR,CSS_TXT_BOX_FORENAME);
    }

    private WebElement getLabelForeNameError() {
        return LabelForeNameError = _webHelper.FindWebElementCSS_Selector_Without_Wait(CSS_LABEL_FORENAME_ERROR);
    }

    private WebElement getTxtBoxSurname() {
        return TxtBoxSurname = _webHelper.InitializeWebElement(CSS_SELECTOR,CSS_TXT_BOX_SURNAME);
    }

    private WebElement getTxtBoxEmail() {
        return TxtBoxEmail = _webHelper.InitializeWebElement(CSS_SELECTOR,CSS_TXT_BOX_EMAIL);
    }

    private WebElement getLabelEmailError() {
        return LabelEmailError = _webHelper.FindWebElementCSS_Selector_Without_Wait(CSS_LABEL_EMAIL_ERROR);
    }

    private WebElement getTxtBoxTelephone() {
        return TxtBoxTelephone = _webHelper.InitializeWebElement(CSS_SELECTOR,CSS_TXT_BOX_TELEPHONE);
    }

    private WebElement getTxtBoxMessage() {
        return TxtBoxMessage = _webHelper.InitializeWebElement(CSS_SELECTOR,CSS_TXT_BOX_MESSAGE);
    }

    private WebElement getLabelMessageError() {
        return LabelMessageError = _webHelper.FindWebElementCSS_Selector_Without_Wait(CSS_LABEL_MESSAGE_ERROR);
    }

    private WebElement getBtnSubmit() {
        return BtnSubmit = _webHelper.InitializeWebElement(CSS_SELECTOR,CSS_BTN_SUBMIT);
    }

    private WebElement getAlertFormSubmission() {
        return AlertFormSubmission = _webHelper.InitializeWebElement(CSS_SELECTOR,CSS_ALERT_FORM_SUBMISSION);
    }

    private WebElement getLabelFeedbackSubmissionSuccessMessage() {
        return LabelFeedbackSubmissionSuccessMessage = _webHelper.InitializeWebElement(CSS_SELECTOR, CSS_LABEL_FEEDBACK_SUBMISSION_SUCCESS_MESSAGE);
    }
}

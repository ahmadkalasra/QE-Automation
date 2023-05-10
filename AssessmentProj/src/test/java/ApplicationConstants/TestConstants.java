package ApplicationConstants;

public class TestConstants {

    public enum ScenarioContextKey {
        SC_KEY_FORE_NAME,
        SC_KEY_PRODUCT_NAME,
        SC_KEY_PRODUCT_PRICE,
        SC_KEY_NO_OF_PRODUCTS,
        SC_KEY_QUANTITY_PER_PRODUCT,
        SC_KEY_TOTAL_NO_OF_ITEMS_PURCHASED
    }

    public static class XPath {
        public static class ShopPage {
            public static final String XPATH_PRE_LABEL_PRODUCT_PRICE = "//h4[contains(text(),'";
            public static final String XPATH_POST_LABEL_PRODUCT_PRICE = "')]/following-sibling::p/span";
            public static final String XPATH_PRE_BUTTON_PRODUCT_BUY = "//h4[contains(text(),'";
            public static final String XPATH_POST_BUTTON_PRODUCT_BUY = "')]/following-sibling::p/a";
            public static final String XPATH_LABEL_CART_ITEM_NAME = "//tr[contains(@ng-repeat,'items')]/td[1]";
            public static final String XPATH_LABEL_CART_ITEM_PRICE = "//tr[contains(@ng-repeat,'items')]/td[2]";
            public static final String XPATH_LABEL_CART_SUBTOTAL = "//tr[contains(@ng-repeat,'items')]/td[4]";
            public static final String XPATH_INPUT_CART_ITEM_QUANTITY = "//tr[contains(@ng-repeat,'items')]/td[3]/input";
            public static final String XPATH_LABEL_TOTAL = "//strong[@class='total ng-binding']";
        }
    }

    public static class CSS_Selector {
        public static class ContactPage {
            public static final String CSS_TXT_BOX_FORENAME = "#forename";
            public static final String CSS_LABEL_FORENAME_ERROR = "#forename-err";
            public static final String CSS_TXT_BOX_SURNAME = "#surname";
            public static final String CSS_TXT_BOX_EMAIL = "#email";
            public static final String CSS_LABEL_EMAIL_ERROR = "#email-err";
            public static final String CSS_TXT_BOX_TELEPHONE = "#telephone";
            public static final String CSS_TXT_BOX_MESSAGE = "#message";
            public static final String CSS_LABEL_MESSAGE_ERROR = "#message-err";
            public static final String CSS_BTN_SUBMIT = "[class$='btn-primary']";
            public static final String CSS_ALERT_FORM_SUBMISSION = ".modal-header h1";
            public static final String CSS_LABEL_FEEDBACK_SUBMISSION_SUCCESS_MESSAGE = "[class*='alert-success']";
        }
    }
}
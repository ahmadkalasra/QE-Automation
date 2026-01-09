package test.seleniumWrapper;

import java.io.File;

public class TestConstants
{
    public static class ConfigTypesKey
    {
        public static final String BROWSER = "Browser";
        public static final String HEADLESS = "Headless";
        public static final String PAGE_LOAD_TIMEOUT = "PageLoadTimeOut";
        public static final String IMPLICIT_WAIT_TIMEOUT = "ImplicitWaitTimeout";
        public static final String OBJECT_IDENTIFICATION_TIMEOUT = "ObjectIdentificationTimeOut";
        public static final String PROTOCOL = "Protocol";
        public static final String APP_URL = "Url";
    }



    public static class PathVariables
    {
        public static final File BaseDirectory = new File(new File(System.getProperty("user.dir")).getParent());
        public static final String BASE_DIRECTORY_PATH = BaseDirectory.getPath();
        public static final String GET_BASE_DIRECTORY = System.getProperty("user.dir");
        public static final String FRAMEWORK_DIRECTORY = BASE_DIRECTORY_PATH + File.separator + "Framework";
        public static final String HTML_REPORT_FOLDER = BASE_DIRECTORY_PATH + File.separator +"Reports";
        public static final String EXTENT_REPORT_NAME = "ExtentReport";
        public static final String CONFIG_FILE_NAME = "configSettings.properties";
        @SuppressWarnings("unused")
        public static final String LOGGER_PROPERTIES_FILE_NAME = "Log4j2.properties";
        public static final String EXTENT_CONFIG_FILE_PATH = String.join(File.separator, "src", "main", "resources");
        public static final String EXTENT_CONFIG_XML = "ExtentConfig.xml";
        public static final String EXECUTION_REPORT_PATH = "ExecutionReportPath";
    }

    public enum WebDriverAction
    {
        CLICK,
        INPUT,
    }

    public enum Locator_Type {
        XPATH,
        CSS_SELECTOR,
    }

    public enum Browser_Type {
        CHROME
    }
}

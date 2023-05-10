package SetUp;

import org.apache.logging.log4j.*;
import test.seleniumWrapper.*;

public class SetAppUrl
{
    public static Logger Log = LogManager.getLogger(SetAppUrl.class.getName());

    public static String SetUrl(String protocol)
    {
        var url = ConfigHelper.getConfigValue(TestConstants.ConfigTypesKey.APP_URL);
        protocol = switch(protocol.toLowerCase())
        {
            case "http" -> "http://";
            case "https" -> "https://";
            default ->  protocol;
        };
        Log.info("The Application URl for the application is set as " + protocol+url);
        return protocol + url;
    }
}

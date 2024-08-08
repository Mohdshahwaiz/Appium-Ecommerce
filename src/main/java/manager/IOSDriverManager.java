package manager;

import static manager.DriverSession.setMyDriver;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.time.Duration;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IOSDriverManager implements IDriverManager {
    private static final Logger LOGGER = getLogger ();

    public static XCUITestOptions getIosOptions () {
        final String appPath = String.valueOf (
            Path.of (System.getProperty ("user.dir"), "src", "test", "resources", "wdio-demo" + ".app" + ".zip"));
        final XCUITestOptions options = new XCUITestOptions ();
        options.setAutomationName ("XCUITest");
        options.setPlatformName ("iOS");
        options.setPlatformVersion ("17.4");
        options.setDeviceName ("iPhone 15 Pro");
        options.setCapability ("noReset","false");
        options.setApp (appPath);
        //options.setCapability ("fullReset",true);
        return options;
    }

    @Override
    public void setupDriver () {
        final URL url;
        try {
            url = new URL ("http://127.0.0.1:4723/");
        } catch (final MalformedURLException e) {
            throw new RuntimeException (e);
        }
        var driver = new IOSDriver (url, getIosOptions ());
        setMyDriver (driver);
    }
}

package manager;

import static manager.DriverSession.setMyDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class AndroidDriverManager implements IDriverManager {

    public static UiAutomator2Options getOptions () {
        final String appPath = String.valueOf (
            Path.of (System.getProperty ("user.dir"), "src", "test", "resources", "General-Store" + ".apk"));
        final UiAutomator2Options options = new UiAutomator2Options ();
        options.setAutomationName ("UiAutomator2");
        options.setPlatformName ("Android");
        options.setPlatformVersion ("14");
        options.setDeviceName ("emulator-5554");
        options.setApp (appPath);
        options.setCapability ("noReset","true");
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
        var driver = new AndroidDriver (url, getOptions ());
        setMyDriver (driver);
    }
}

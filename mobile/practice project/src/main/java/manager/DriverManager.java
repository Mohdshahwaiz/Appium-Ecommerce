package manager;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import lombok.Data;
import org.openqa.selenium.WebDriver;

@Data
public final class DriverManager {
    private       WebDriver     driver;
    public static DriverManager driverManager;

    private DriverManager () {
    }

    public static DriverManager driverManager () {
        if (driverManager == null) {
            driverManager = new DriverManager ();
        }
        return driverManager;
    }

    public static UiAutomator2Options getOptions () {
        final UiAutomator2Options options = new UiAutomator2Options ();
        options.setAutomationName ("UiAutomator2");
        options.setPlatformName ("Android");
        options.setPlatformVersion ("14");
        options.setDeviceName ("emulator-5554");
        options.setApp (
            "/Users/appinventiv/Downloads/practiceWorkspace/mobile/practice project/src/test/resources/General-Store.apk");
        return options;
    }

    public void createDriver () {
        final URL url;
        try {
            url = new URL ("http://127.0.0.1:4723/");
        } catch (final MalformedURLException e) {
            throw new RuntimeException (e);
        }
        driver = new AndroidDriver (url, getOptions ());
    }

    public void quit () {
        driver.quit ();
    }
}

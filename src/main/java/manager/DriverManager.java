package manager;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.time.Duration;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

@Data
public final class DriverManager {
    private       WebDriver     driver;
    public static DriverManager driverManager;
    private       WebDriverWait wait;

    private DriverManager () {
    }

    public static DriverManager driverManager () {
        if (driverManager == null) {
            driverManager = new DriverManager ();
        }
        return driverManager;
    }

    public static UiAutomator2Options getOptions () {
        final String appPath = String.valueOf (
            Path.of (System.getProperty ("user.dir"), "src", "test", "resources", "General-Store" + ".apk"));
        final UiAutomator2Options options = new UiAutomator2Options ();
        options.setAutomationName ("UiAutomator2");
        options.setPlatformName ("Android");
        options.setPlatformVersion ("14");
        options.setDeviceName ("emulator-5554");
        options.setApp (appPath);
        return options;
    }

    public void createDriver () {
        final URL url;
        try {
            url = new URL ("http://127.0.0.1:4723/");
        } catch (final MalformedURLException e) {
            throw new RuntimeException (e);
        }
        this.driver = new AndroidDriver (url, getOptions ());
        wait = new WebDriverWait (this.driver, Duration.ofSeconds (60));
    }

    public void quit () {
        this.driver.quit ();
    }
}

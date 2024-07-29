package manager;

import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.Map;

import enums.PlatformType;
import lombok.Data;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

@Data
public class DriverSession<D extends WebDriver> {
    private              PlatformType        platformType;
    private static final Logger              LOGGER  = getLogger ();
    private              D                   driver;
    private              Map<String, Object> sharedData;
    private              WebDriverWait       wait;
    private static       DriverSession       session = new DriverSession ();

    public static synchronized DriverSession getSession () {
        return session;
    }

    /**
     * Create a new Session.
     *
     * @param platformType Target Platform Type
     */
    public static synchronized void createSession (final PlatformType platformType) {
        final var currentSession = getSession ();
        currentSession.setPlatformType (platformType);
        if (platformType != PlatformType.API) {
            final var instance = new DriverManager ();
            instance.setupDriver ();
        }
    }

    /**
     * Sets current session in thread.
     *
     * @param driver the driver instance
     * @param <D> the generic WebDriver type
     */
    static <D extends WebDriver> void setMyDriver (final D driver) {
        final var session = getSession ();
        LOGGER.traceEntry ("Application Type: {}, Driver: {}", session.getPlatformType (), driver);
        session.setDriver (driver);
        LOGGER.traceExit ();
    }
}

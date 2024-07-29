package manager;

import static java.time.Duration.ofSeconds;
import static manager.DriverSession.getSession;
import static org.apache.logging.log4j.LogManager.getLogger;

import enums.PlatformType;
import lombok.Data;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

@Data
public class DriverManager {
    private              WebDriverWait wait;
    private static final Logger        LOGGER = getLogger ();

    private final PlatformType platformType;

    public DriverManager () {
        this.platformType = getSession ().getPlatformType ();
    }

    public synchronized void setupDriver () {
        LOGGER.traceEntry ();
        final IDriverManager driverManager = switch (this.platformType) {
            case ANDROID -> new AndroidDriverManager ();
            default -> new IOSDriverManager ();
        };
        driverManager.setupDriver ();
        setDriverWaits();
        LOGGER.traceExit ();
    }

    private void setDriverWaits () {
        LOGGER.traceEntry ();
        final var session = getSession ();
        final var driver = session.getDriver ();
        final var timeouts = driver.manage ()
            .timeouts ();
        timeouts.implicitlyWait (ofSeconds (10));
        if (this.platformType == PlatformType.WEB) {
            timeouts.pageLoadTimeout (ofSeconds (10));
            timeouts.scriptTimeout (ofSeconds (10));
        }
        session.setWait (new WebDriverWait (driver, ofSeconds (60)));
        LOGGER.traceExit ();
    }
}

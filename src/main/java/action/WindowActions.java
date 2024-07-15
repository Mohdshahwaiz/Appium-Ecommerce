package action;

import static manager.DriverManager.driverManager;
import static org.apache.logging.log4j.LogManager.getLogger;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;

public class WindowActions {
    private static final Logger LOGGER = getLogger ();

    public static WindowActions onWindow () {
        return new WindowActions ();
    }

    public Dimension viewportSize () {
        LOGGER.info ("Getting Viewport of the screen...");
        return driverManager ().getDriver ()
            .manage ()
            .window ()
            .getSize ();
    }
}

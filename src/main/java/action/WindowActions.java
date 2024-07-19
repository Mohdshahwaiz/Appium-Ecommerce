package action;

import static action.CommonActions.getDriverAttribute;
import static java.util.Optional.ofNullable;
import static manager.DriverManager.driverManager;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.Set;

import io.appium.java_client.remote.SupportsContextSwitching;
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

    public String currentContext () {
        LOGGER.traceEntry ();
        LOGGER.info ("Getting current context name...");
        return getDriverAttribute(SupportsContextSwitching::getContext, null);
    }

    public  String getContextHandles () {
        LOGGER.info ("Getting Handles of the screen...");
        return getDriverAttribute (SupportsContextSwitching::getContextHandles,null).toString ();
    }
}

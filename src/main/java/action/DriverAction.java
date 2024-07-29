package action;

import static manager.DriverSession.getSession;
import static org.apache.logging.log4j.LogManager.getLogger;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;

public class DriverAction {
    private static final DriverAction DRIVER_ACTIONS = new DriverAction ();
    private static final Logger       LOGGER         = getLogger ();

    public static DriverAction withDriver () {
        return DRIVER_ACTIONS;
    }

    public void executeScript (final String script, final Object... args) {
        LOGGER.traceEntry ();
        LOGGER.info ("Executing script");
        ((JavascriptExecutor) getSession ().getDriver ()).executeScript (script, args);
        LOGGER.traceExit ();
    }
}

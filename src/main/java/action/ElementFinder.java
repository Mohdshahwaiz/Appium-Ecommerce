package action;

import static enums.WaitStrategy.CLICKABLE;
import static manager.DriverManager.driverManager;
import static org.apache.logging.log4j.LogManager.getLogger;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import java.util.List;

import enums.WaitStrategy;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class ElementFinder {
    private static final Logger LOGGER = getLogger ();

    public static WebElement find (final By locator, final WaitStrategy waitStrategy) {
        LOGGER.traceEntry ();
        waitForElement (locator, waitStrategy);
        return LOGGER.traceExit (driverManager ().getDriver ()
            .findElement (locator));
    }

    public static List<WebElement> finds (final By locator, final WaitStrategy waitStrategy) {
        LOGGER.traceEntry ();
        waitForElement (locator, waitStrategy);
        return LOGGER.traceExit (driverManager ().getDriver ()
            .findElements (locator));
    }

    public static void waitForElement (final By locator, final WaitStrategy waitStrategy) {
        LOGGER.traceEntry ();
        final var wait = driverManager ().getWait ();
        if (waitStrategy == CLICKABLE)
            wait.until (elementToBeClickable (locator));
        else
            wait.until (visibilityOfElementLocated (locator));
        LOGGER.traceExit ();
    }

    private ElementFinder () {
        // Blocking Instantiation
    }
}

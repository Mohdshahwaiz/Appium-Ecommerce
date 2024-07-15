package action;

import static enums.Message.ELEMENT_NOT_FOUND;
import static enums.WaitStrategy.CLICKABLE;
import static java.text.MessageFormat.format;
import static java.util.Objects.isNull;
import static manager.DriverManager.driverManager;
import static org.apache.logging.log4j.LogManager.getLogger;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static utils.ErrorHandler.throwError;

import java.util.List;

import enums.Message;
import enums.WaitStrategy;
import exception.FrameworkError;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class ElementFinder {
    private static final Logger LOGGER = getLogger ();

    public static WebElement find (final By locator, final WaitStrategy waitStrategy) {
        LOGGER.traceEntry ();
        final var elements = finds (locator, waitStrategy);
        if (elements.isEmpty ()) {
            throwError (ELEMENT_NOT_FOUND, locator);
        }
        return elements.get (0);
    }

    public static List<WebElement> finds (final By locator, final WaitStrategy waitStrategy) {
        LOGGER.traceEntry ();
        final var driver = driverManager ().getDriver ();
        final List<WebElement> elements;
        waitForElement (locator, waitStrategy);
        elements = finds (driver, locator);
        return LOGGER.traceExit (elements);
    }

    public static List<WebElement> finds (final By locator) {
        LOGGER.traceEntry ();
        final var driver = driverManager ().getDriver ();
        final List<WebElement> elements;
        elements = finds (driver, locator);
        return LOGGER.traceExit (elements);
    }

    public static WebElement find (final By locator) {
        LOGGER.traceEntry ();
        final var elements = finds (locator);
        if (elements.isEmpty ()) {
            throwError (ELEMENT_NOT_FOUND, locator);
        }
        return elements.get (0);
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

    private static <D extends WebDriver> List<WebElement> finds (final D driver, final By locator) {
        LOGGER.traceEntry ();
        return LOGGER.traceExit (driver.findElements (locator));
    }

    private ElementFinder () {
        // Blocking Instantiation
    }
}

package action;

import static action.ElementFinder.find;
import static org.apache.logging.log4j.LogManager.getLogger;

import enums.WaitStrategy;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ClickableActions extends ElementActions {
    private static final Logger LOGGER = getLogger ();

    ClickableActions (final By locator) {
        super (locator);
    }
    ClickableActions (final WebElement ele) {
        super (ele);
    }

    public static ClickableActions withMouse (final By locator) {
        return new ClickableActions (locator);
    }
    public static ClickableActions withMouse (final WebElement ele) {
        return new ClickableActions (ele);
    }


    public static void click (final By locator) {
        LOGGER.traceEntry ();
        find (locator, WaitStrategy.CLICKABLE).click ();
        LOGGER.traceExit ();
    }
        public static void click (final WebElement ele) {
            LOGGER.traceEntry ();
            ele.click ();
            LOGGER.traceExit ();
        }
    }


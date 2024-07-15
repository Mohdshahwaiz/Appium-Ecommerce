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

    public void click () {
        LOGGER.traceEntry ();
        find (this.locator, WaitStrategy.VISIBLE).click ();
        LOGGER.traceExit ();
    }

    public void clickElement () {
        LOGGER.traceEntry ();
        this.ele.click ();
        LOGGER.traceExit ();
    }
}


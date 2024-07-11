package action;

import static action.ElementFinder.find;
import static org.apache.logging.log4j.LogManager.getLogger;

import enums.WaitStrategy;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class ClickableActions {
    private static final Logger           LOGGER            = getLogger ();
    private static final ClickableActions CLICKABLE_ACTIONS = new ClickableActions ();

    public ClickableActions withMouse () {
        return CLICKABLE_ACTIONS;
    }

    public static void click (final By locator) {
        LOGGER.traceEntry ();
        find (locator, WaitStrategy.CLICKABLE).click ();
        LOGGER.traceExit ();
    }

    private ClickableActions () {
        // Block instantiation
    }
}

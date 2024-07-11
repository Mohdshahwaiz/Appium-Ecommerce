package action;

import static action.ElementFinder.find;
import static org.apache.logging.log4j.LogManager.getLogger;

import enums.WaitStrategy;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class TextBoxActions {
    private static final Logger         LOGGER           = getLogger ();
    private static final TextBoxActions TEXT_BOX_ACTIONS = new TextBoxActions ();

    public TextBoxActions onTextBox () {
        return TEXT_BOX_ACTIONS;
    }

    public static void enterText (final By locator, final String keys) {
        LOGGER.traceEntry ();
        LOGGER.info ("Entering text to element {}", keys);
        find (locator, WaitStrategy.VISIBLE).sendKeys (keys);
        LOGGER.traceExit ();
    }
    public void alternateEnterText(final By locator,final String keys){
        LOGGER.traceEntry();
        LOGGER.info ("Entering text to element {}", keys);


    }
}

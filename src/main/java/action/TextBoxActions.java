package action;

import static action.ElementFinder.find;
import static manager.DriverSession.getSession;
import static org.apache.logging.log4j.LogManager.getLogger;

import enums.WaitStrategy;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TextBoxActions extends ClickableActions {
    private static final Logger LOGGER = getLogger ();
    //    private  final  WebElement ele;

    public static TextBoxActions onTextBox (By locator) {
        return new TextBoxActions (locator);
    }

    TextBoxActions (final By locator) {
        super (locator);
    }

    TextBoxActions (final WebElement ele) {
        super (ele);
    }

    public void enterText (final String keys) {
        LOGGER.traceEntry ();
        LOGGER.info ("Entering text to element {}", keys);
        find (this.locator, WaitStrategy.CLICKABLE).sendKeys (keys);
        LOGGER.traceExit ();
    }

    public void alternateEnterText (final String keys) {
        LOGGER.traceEntry ();
        LOGGER.info ("Entering text alternately to element {}", keys);
    }
}

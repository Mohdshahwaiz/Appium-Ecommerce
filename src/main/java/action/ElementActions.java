package action;

import static action.ElementFinder.find;
import static com.google.common.truth.Truth.assertThat;
import static org.apache.logging.log4j.LogManager.getLogger;

import com.google.common.truth.BooleanSubject;
import com.google.common.truth.StringSubject;
import enums.WaitStrategy;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.xml.sax.Locator;

public class ElementActions {
    private static final Logger LOGGER = getLogger ();
    protected       By     locator;
    protected WebElement ele;

    ElementActions (final By locator) {
        this.locator = locator;
    }
    ElementActions (final WebElement ele) {
        this.ele = ele;
    }

    public static ElementActions onElement (final By locator) {
        return new ElementActions (locator);
    }
    public static ElementActions onElement (final WebElement ele) {
        return new ElementActions (ele);
    }

    public Boolean isDisplayed () {
        LOGGER.traceEntry ();
        return LOGGER.traceExit (find (this.locator, WaitStrategy.VISIBLE).isDisplayed ());
    }
    public BooleanSubject verifiedisDisplayed () {
        LOGGER.traceEntry ();
        return LOGGER.traceExit (assertThat (find (this.locator, WaitStrategy.VISIBLE).isDisplayed ()));
    }
    public String getText () {
        LOGGER.traceEntry ();
     return LOGGER.traceExit (find (this.locator, WaitStrategy.VISIBLE).getText ());
    }

    public String getText (WebElement ele) {
        LOGGER.traceEntry ();
        return LOGGER.traceExit (this.ele.getText ());
    }




}

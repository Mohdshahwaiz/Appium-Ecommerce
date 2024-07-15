package action;

import static action.DriverAction.withDriver;
import static action.ElementFinder.find;
import static com.google.common.truth.Truth.assertThat;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.security.Key;

import com.google.common.truth.BooleanSubject;
import com.google.common.truth.StringSubject;
import enums.WaitStrategy;
import exception.FrameworkError;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.xml.sax.Locator;

public class ElementActions {
    private static final Logger     LOGGER = getLogger ();
    protected            By         locator;
    protected            WebElement ele;

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

    public Boolean isDisplayedWithoutWait () {
        LOGGER.traceEntry ();
        boolean isDisplayed = false;
        try {
            isDisplayed = find (this.locator).isDisplayed ();
        } catch (final FrameworkError e) {
            LOGGER.info ("Element {} not found.", this.locator);
        }
        return LOGGER.traceExit (isDisplayed);
    }

    public BooleanSubject verifyIsDisplayed () {
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

    public void scrollIntoView () {
        LOGGER.traceEntry ();
        withDriver ().executeScript ("arguments[0].scrollIntoView(true);", find (this.locator, WaitStrategy.VISIBLE));
        LOGGER.traceExit ();
    }
}

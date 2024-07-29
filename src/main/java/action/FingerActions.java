package action;

import static enums.Message.ELEMENT_NOT_FOUND;
import static java.util.Collections.singletonList;
import static java.util.Optional.ofNullable;
import static manager.DriverSession.getSession;
import static org.apache.logging.log4j.LogManager.getLogger;
import static utils.ErrorHandler.throwError;

import java.util.Collections;

import config.SwipeSetting;
import enums.SwipeDirection;
import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class FingerActions extends ElementActions {
    private static final Logger LOGGER = getLogger ();

    FingerActions (final By locator) {
        super (locator);
    }

    public static FingerActions withFinger (final By locator) {
        return new FingerActions (locator);
    }

    public void swipe (final SwipeDirection direction) {
        LOGGER.traceEntry ();
        LOGGER.info ("Swiping {} on Mobile devices.", direction);
        final var swipeSequence = FingerGestureBuilder.init ()
            .direction (direction)
            .sourceElement (this.locator)
            .build ()
            .swipe ();
        ((AppiumDriver) getSession ().getDriver ()).perform (singletonList (swipeSequence));
    }

    public void swipeTill (final SwipeDirection direction) {
        LOGGER.traceEntry ();
        LOGGER.info ("Swiping till the element in the [{}] direction...", direction);
        // ofNullable (this.listener).ifPresent (l -> l.onSwipeTill (this.locator, direction));
        final var maxSwipe = SwipeSetting.swipeSetting ()
            .getMaxSwipeUntilFound ();
        var swipeCounts = 0;
        final var element = onElement (this.locator);
        final var finger = withFinger (this.locator);
        while (!element.isDisplayedWithoutWait () && swipeCounts++ < maxSwipe) {
            finger.swipe (direction);
        }
        LOGGER.traceExit ();
    }
}

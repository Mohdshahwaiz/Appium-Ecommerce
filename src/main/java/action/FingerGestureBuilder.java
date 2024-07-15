package action;

import static action.ElementFinder.find;
import static action.WindowActions.onWindow;
import static enums.SwipeDirection.DOWN;
import static enums.SwipeDirection.RIGHT;
import static enums.SwipeDirection.UP;
import static enums.WaitStrategy.CLICKABLE;
import static java.time.Duration.ZERO;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;
import static java.util.Objects.isNull;
import static org.apache.logging.log4j.LogManager.getLogger;
import static org.openqa.selenium.interactions.PointerInput.Kind.TOUCH;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;
import static utils.Validator.validateDelay;

import java.time.Duration;
import java.util.function.BiFunction;

import config.SwipeSetting;
import config.ui.DelaySetting;
import config.ui.UISettings;
import enums.SwipeDirection;
import lombok.Builder;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

@Builder (builderMethodName = "init")
final class FingerGestureBuilder {
    private static final Logger    LOGGER      = getLogger ();
    private static final Dimension SCREEN_SIZE = onWindow ().viewportSize ();

    private       SwipeDirection      direction;
    private       int                 initialIndex;
    @Builder.Default
    private       String              name     = "Finger 1";
    private       int                 offset;
    @Builder.Default
    private       Duration            pause    = ZERO;
    private       boolean             reverse;
    private       By                  sourceElement;
    @Builder.Default
    private       Duration            speed    = ZERO;
    private final PointerInput.Origin viewport = viewport ();
    private final DelaySetting        delay    = new DelaySetting ();
    private final UISettings          ui       = new UISettings ();

    Sequence dragTo (final By targetElement) {
        final var start = getElementCenter (this.sourceElement);
        final var end = getElementCenter (targetElement);
        checkCoordinateBounds (start, this.sourceElement);
        checkCoordinateBounds (end, targetElement);
        return singleFingerGesture (start, end);
    }

    Sequence swipe () {
        final var startPosition = getOffSetPosition (getScreenCenter ());
        final var endPosition = getSwipeEndPosition (startPosition);
        //checkCoordinateBounds (endPosition, this.sourceElement);
        if (this.reverse) {
            return singleFingerGesture (endPosition, startPosition);
        }
        return singleFingerGesture (startPosition, endPosition);
    }

    Sequence tapAndHold () {
        final var start = getElementCenter (this.sourceElement);
        this.pause = ofSeconds (1);
        return singleFingerGesture (start, null);
    }

    Sequence tapOn () {
        final var start = getElementCenter (this.sourceElement);
        return singleFingerGesture (start, null);
    }

    private void checkCoordinateBounds (final Point point, final By locator) {
        final var x = point.getX ();
        final var y = point.getY ();
        var w = SCREEN_SIZE.getWidth ();
        var h = SCREEN_SIZE.getHeight ();
        if (!isNull (locator)) {
            final var element = find (locator, CLICKABLE);
            final var size = element.getSize ();
            final var location = element.getLocation ();
            w = size.getWidth () + location.getX ();
            h = size.getHeight () + location.getY ();
        }
        if (x >= w || y >= h || x < 0 || y < 0) {
            LOGGER.info ("FINGER_OUT_OF_BOUND", point, new Dimension (w, h));
        }
    }

    private Sequence composeSequence (final BiFunction<PointerInput, Sequence, Sequence> steps) {
        final var finger = new PointerInput (TOUCH, this.name);
        final var sequence = new Sequence (finger, this.initialIndex);
        return steps.apply (finger, sequence);
    }

    private int getDistance () {
        final var distance = 75;
        if (distance <= 0 || distance >= 100) {
            LOGGER.info ("Distance incorrect");
        }
        return distance;
    }

    private Point getElementCenter (final By element) {
        final var webElement = find (element, CLICKABLE);
        final var location = webElement.getLocation ();
        final var size = webElement.getSize ();
        final var x = location.getX () + (size.getWidth () / 2);
        final var y = location.getY () + (size.getHeight () / 2);
        return new Point (x, y);
    }

    private Point getOffSetPosition (final Point position) {
        var result = position;
        if (this.offset > 0) {
            final var offSetX = this.direction == SwipeDirection.LEFT || this.direction == RIGHT ? -this.offset : 0;
            final var offSetY = this.direction == UP || this.direction == DOWN ? this.offset : 0;
            result = new Point (result.getX () + offSetX, result.getY () + offSetY);
        }
        return result;
    }

    private Point getScreenCenter () {
        final var x = SCREEN_SIZE.getWidth () / 2;
        final var y = SCREEN_SIZE.getHeight () / 2;
        return new Point (x, y);
    }

    private Point getSwipeEndPosition (final Point start) {
        final var distance = getDistance ();
        final var x = start.getX () + ((start.getX () * this.direction.getX () * distance) / 100);
        final var y = start.getY () + ((start.getY () * this.direction.getY () * distance) / 100);
        return new Point (x, y);
    }

    private Point getSwipeStartPosition () {
        if (!isNull (this.sourceElement)) {
            return getElementCenter (this.sourceElement);
        }
        return getScreenCenter ();
    }

    private Sequence singleFingerGesture (final Point start, final Point end) {
        final var distance = SwipeSetting.swipeSetting ()
            .getDistance ();
        final var delaySetting = this.ui.getDelay ();
        validateDelay (SwipeSetting.swipeSetting ()
            .getSpeed ()
            .getDelay ());
        validateDelay (delaySetting.getBeforeSwipe ());
        this.speed = this.speed != ZERO
                     ? this.speed
                     : ofMillis (SwipeSetting.swipeSetting ()
                         .getSpeed ()
                         .getDelay ());
        this.pause = this.pause != ZERO ? this.pause : ofMillis (delaySetting.getBeforeSwipe ());
        return composeSequence ((finger, steps) -> {
            steps.addAction (finger.createPointerMove (ZERO, this.viewport, start.getX (), start.getY ()));
            steps.addAction (finger.createPointerDown (PointerInput.MouseButton.LEFT.asArg ()));
            steps.addAction (new Pause (finger, this.pause));
            if (!isNull (end)) {
                steps.addAction (finger.createPointerMove (this.speed, this.viewport, end.getX (), end.getY ()));
            }
            steps.addAction (finger.createPointerUp (PointerInput.MouseButton.LEFT.asArg ()));
            return steps;
        });
    }
}

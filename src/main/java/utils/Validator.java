

package utils;

import static enums.Message.DELAY_OVERFLOW;
import static enums.Message.INDEX_OUT_OF_BOUNDS;
import static java.util.Objects.isNull;
import static java.util.Optional.of;
import static org.apache.logging.log4j.LogManager.getLogger;
import static org.apache.logging.log4j.core.util.Assert.isEmpty;
import static utils.ErrorHandler.throwError;

import java.util.function.Consumer;

import enums.Message;
import org.apache.logging.log4j.Logger;

public final class Validator {
    private static final Logger LOGGER = getLogger ();
    /**
     * Checks if the index is in bounds.
     *
     * @param index Index number
     * @param size Size of the list
     */
    public static void checkIndex (final int index, final int size) {
        if (index < 0 || index >= size) {
            LOGGER.error (String.valueOf (INDEX_OUT_OF_BOUNDS), index);

        }
    }

    /**
     * Checks if the String value is not null.
     *
     * @param value Value to check
     * @param message Message to throw when the value is empty.
     * @param args Args for the message.
     *
     * @return Value if it is not empty.
     */
    public static String requireNonEmpty (final String value, final Message message, final Object... args) {
        if (isEmpty (value)) {
            throwError (message, args);
        }
        return value;
    }

    /**
     * Checks if the object is null or not, if null, a framework error will be thrown with provided message
     *
     * @param obj Test object
     * @param message Validation message
     * @param args message args
     * @param <T> Type of object
     *
     * @return Non null object
     */
    public static <T> T requireNonNull (final T obj, final Message message, final Object... args) {
        if (isNull (obj)) {
            throwError (message, args);
        }
        return obj;
    }

    /**
     * Checks if the value is not null or zero and perform action on it.
     *
     * @param value Value to check
     * @param action Action to perform if value present
     * @param <T> Type of value
     */
    public static <T> void setOptionIfPresent (final T value, final Consumer<T> action) {
        if (!isNull (value)) {
            if (value instanceof Integer) {
                of (value).filter (i -> (Integer) i > 0)
                    .ifPresent (action);
            } else {
                of (value).ifPresent (action);
            }
        }
    }

    /**
     * Validate delay that it should be greater than 0 and less than or equal to 1s
     *
     * @param delay Delay to validate
     *
     * @return delay after validation
     */
    public static int validateDelay (final int delay) {
        if (delay < 0 || delay > 1000) {
            throwError (DELAY_OVERFLOW);
        }
        return delay;
    }

    private Validator () {
        // Utility class.
    }
}

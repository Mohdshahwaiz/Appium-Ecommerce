package action;

import static java.util.Objects.isNull;
import static manager.DriverManager.driverManager;

import java.util.function.Function;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Getter
public class CommonActions {
    protected By         locator;
    protected WebElement ele;

    CommonActions (final By locator) {
        this.locator = locator;
    }

    CommonActions (final WebElement ele) {
        this.ele = ele;
    }

    public static Double getFormattedAmount (String amount) {
        Double price = Double.parseDouble (amount.substring (1));
        return price;
    }

    public static <D extends WebDriver, E> E getDriverAttribute (final Function<D, E> action, final E defaultValue) {
        try {
            return action.apply ((D) driverManager ().getDriver ());
        } catch (Exception e) {
            if (!isNull (defaultValue))
                return defaultValue;
            else
                throw e;
        }
    }
}

package pages;

import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class HomePage {

    public static final HomePage HOME_PAGE = new HomePage ();

    public static HomePage getHomePage () {
        return HOME_PAGE;
    }

    private final By home        = By.xpath ("//XCUIElementTypeButton[@name='Login']");
    private final By formsButton = By.id ("Forms");
    private final By loginButton = By.id ("Login");
}

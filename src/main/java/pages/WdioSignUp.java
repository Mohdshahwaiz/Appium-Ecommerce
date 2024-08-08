package pages;

import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class WdioSignUp {

    public static final WdioSignUp WDIO_SIGN_UP = new WdioSignUp ();

    public static WdioSignUp getWdioSignUp () {
        return WDIO_SIGN_UP;
    }

    private final By signUpButton  = By.xpath ("//XCUIElementTypeStaticText[@name='Sign up']");
    private final By emailField    = By.id ("input-email");
    private final By passwordField = By.id ("input-password");
    private final By confirmPassword=By.xpath ("//XCUIElementTypeSecureTextField[@name='input-repeat-password']");
    private final By signUpFormButton=By.xpath ("(//XCUIElementTypeOther[@name='SIGN UP'])[2]");
    private final By errorMessage=By.xpath ("//XCUIElementTypeStaticText[@name='Please enter at least 8 characters']");
    private final By successfullyMessage=By.id ("You successfully signed up!");


}

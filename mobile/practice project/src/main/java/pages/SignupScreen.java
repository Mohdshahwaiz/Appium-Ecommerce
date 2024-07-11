package pages;

import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class SignupScreen {
    private static final SignupScreen SIGNUP_SCREEN = new SignupScreen ();

    public static SignupScreen getSignupScreen () {
        return SIGNUP_SCREEN;
    }

    private By name = By.id ("com.androidsample.generalstore:id/nameField");
}

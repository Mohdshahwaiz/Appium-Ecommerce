package pages;

import java.util.List;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Getter
public class SignupScreen {
    private static final SignupScreen SIGNUP_SCREEN = new SignupScreen ();

    public static SignupScreen signupPage () {
        return SIGNUP_SCREEN;
    }

    private final By name     = By.id ("com.androidsample.generalstore:id/nameField");
    private final By dropDown =By.id ("com.androidsample.generalstore:id/spinnerCountry");
    private final By ele      =By.xpath ("//android.widget.TextView[@resource-id='android:id/text1']");
    private final By country =By.xpath ("//android.widget.TextView[@resource-id='android:id/text1' and @text='Australia']");
    //android.widget.TextView[@resource-id="android:id/text1" and @text="Anguilla"]
    private final By countryName=By.xpath ("    //android.widget.TextView[@resource-id='android:id/text1' and @text='Anguilla']");
}

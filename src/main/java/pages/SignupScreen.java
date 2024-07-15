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
    private final By listOfCountries      =By.xpath ("//android.widget.TextView[@resource-id='android:id/text1']");
    private final By country =By.xpath ("//android.widget.TextView[@resource-id='android:id/text1' and @text='Australia']");
    //android.widget.TextView[@resource-id="android:id/text1" and @text="Anguilla"]
    private final By countryName=By.xpath ("    //android.widget.TextView[@resource-id='android:id/text1' and @text='Anguilla']");
    private final  By femaleRadio=By.id ("com.androidsample.generalstore:id/radioFemale");
    private  final By letsShopButton=By.id ("com.androidsample.generalstore:id/btnLetsShop");
    private final By pg3Shoes=By.xpath ("//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productName' and @text='PG 3']");
    private final By cartButton=By.id ("com.androidsample.generalstore:id/appbar_btn_cart");
    private final By addToCart=By.xpath ("(//android.widget.TextView[@resource-id=\"com.androidsample" +
        ".generalstore:id/productAddCart\"])[2]");

}

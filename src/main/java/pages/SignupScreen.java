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

    private final By name                = By.id ("com.androidsample.generalstore:id/nameField");
    private final By dropDown            = By.id ("com.androidsample.generalstore:id/spinnerCountry");
    private final By listOfCountries     = By.xpath ("//android.widget.TextView[@resource-id='android:id/text1']");
    private final By country             = By.xpath (
        "//android.widget.TextView[@resource-id='android:id/text1' and @text='Australia']");
    //android.widget.TextView[@resource-id="android:id/text1" and @text="Anguilla"]
    private final By countryName         = By.xpath (
        "    //android.widget.TextView[@resource-id='android:id/text1' and @text='Anguilla']");
    private final By femaleRadio         = By.id ("com.androidsample.generalstore:id/radioFemale");
    private final By letsShopButton      = By.id ("com.androidsample.generalstore:id/btnLetsShop");
    private final By pg3Shoes            = By.xpath (
        "//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productName' and @text='PG 3']");
    private final By cartButton          = By.id ("com.androidsample.generalstore:id/appbar_btn_cart");
    //    private final By addToCart       = By.xpath ("(//android.widget.TextView[@text= 'ADD TO CART'])[2]");
    private final By addToCart           = By.xpath ("//android.widget.TextView[@text= 'ADD TO CART']");
    private final By toastMessage        = By.xpath ("//android.widget.Toast");
    private final By productName         = By.id ("com.androidsample.generalstore:id/productName");
    private final By addedProduct        = By.id ("com.androidsample.generalstore:id/productName");
    private final By cartPageTitle       = By.id ("com.androidsample.generalstore:id/toolbar_title");
    private final By productPrice        = By.id ("com.androidsample.generalstore:id/productPrice");
    private final By totalPurchaseAMount = By.id ("com.androidsample.generalstore:id/totalAmountLbl");
    private final By sendCheckBox        = By.xpath (
        "//android.widget.CheckBox[@text='Send me e-mails on discounts related to selected products in future']");
    private final By visitWebsiteButton=By.id ("com.androidsample.generalstore:id/btnProceed");
}

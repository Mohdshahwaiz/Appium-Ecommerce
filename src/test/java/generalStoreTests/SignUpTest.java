package generalStoreTests;

import static action.ElementActions.onElement;
import static pages.SignupScreen.signupPage;

import java.util.List;

import action.ClickableActions;
import action.ElementActions;
import action.ElementFinder;
import actions.SingUpAction;
import base.BaseTest;
import enums.WaitStrategy;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.SignupScreen;

public class SignUpTest extends BaseTest {
    SingUpAction singUpAction;



    @Test
    public static void clickOnDropdown () {
        ClickableActions.click (signupPage ().getDropDown ());
    }

    @Test
    public void getAllCountries () {
        List<WebElement> list = ElementFinder.finds (signupPage ().getEle (), WaitStrategy.VISIBLE);
        for(int i=0;i<list.size ();i++){
            ClickableActions.withMouse (signupPage ().getCountryName ());
            break;
           // String element=ElementFinder.find (signupPage ().getCountryName (),WaitStrategy.VISIBLE).getText ();
       // String country=  ElementActions.onElement (list.get (i)).getText ();
        }
        ClickableActions.click (signupPage ().getCountry ());

    }
    @Test
    public Boolean selectCountry () {
       return onElement (signupPage ().getName ()).isDisplayed ();
    }
}

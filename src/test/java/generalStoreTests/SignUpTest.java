package generalStoreTests;

import static action.ElementActions.onElement;
import static enums.SwipeDirection.UP;
import static pages.SignupScreen.signupPage;

import java.util.Comparator;
import java.util.List;

import action.ClickableActions;
import action.ElementActions;
import action.ElementFinder;
import action.FingerActions;
import action.TextBoxActions;
import actions.SingUpAction;
import base.BaseTest;
import enums.SwipeDirection;
import enums.WaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.SignupScreen;

public class SignUpTest extends BaseTest {
    SingUpAction singUpAction;



    @Test
    public static void clickOnDropdown () {
        ClickableActions.withMouse (signupPage ().getDropDown ()).clickElement ();
    }

    @Test
    public  void fillForm () throws InterruptedException {
        ClickableActions.withMouse (signupPage ().getDropDown ()).click ();
        final List<WebElement> list = ElementFinder.finds (signupPage ().getListOfCountries (), WaitStrategy.VISIBLE);
        for(int i=0;i<list.size ();i++){
            ClickableActions.withMouse (list.get (2)).clickElement ();
            break;
        }
        TextBoxActions.enterText (signupPage ().getName (),"shahwaiz");
        ClickableActions.withMouse (signupPage ().getFemaleRadio ()).click ();
        ClickableActions.withMouse (signupPage ().getLetsShopButton ()).click ();
        FingerActions.withFinger(signupPage ().getPg3Shoes ()).swipeTill (UP);
        ClickableActions.withMouse (signupPage ().getAddToCart ()).click ();
        Thread.sleep (1000);
        ClickableActions.withMouse (signupPage ().getCartButton ()).click ();

    }


}

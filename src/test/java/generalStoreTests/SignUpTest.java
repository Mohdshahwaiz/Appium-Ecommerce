package generalStoreTests;

import static enums.SwipeDirection.UP;
import static pages.SignupScreen.signupPage;

import java.util.List;

import action.ClickableActions;
import action.CommonActions;
import action.ElementActions;
import action.ElementFinder;
import action.FingerActions;
import action.TextBoxActions;
import action.WindowActions;
import actions.SingUpAction;
import base.BaseTest;
import enums.WaitStrategy;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUpTest extends BaseTest {
    SingUpAction singUpAction;

    @Test
    public static void clickOnDropdown () {
        ClickableActions.withMouse (signupPage ().getDropDown ())
            .clickElement ();
    }

    @Test
    public void fillForm () throws InterruptedException {
        ClickableActions.withMouse (signupPage ().getDropDown ())
            .click ();
        final List<WebElement> list = ElementFinder.finds (signupPage ().getListOfCountries (), WaitStrategy.VISIBLE);
        for (int i = 0; i < list.size (); i++) {
            ClickableActions.withMouse (list.get (2))
                .clickElement ();
            break;
        }
       // TextBoxActions.enterText (signupPage ().getName (), "shahwaiz");
        ClickableActions.withMouse (signupPage ().getFemaleRadio ())
            .click ();
        ClickableActions.withMouse (signupPage ().getLetsShopButton ())
            .click ();
    }

    @Test (dependsOnMethods = { "fillForm" })
    public void selectProduct () {
        FingerActions.withFinger (signupPage ().getPg3Shoes ())
            .swipeTill (UP);
        final List<WebElement> productsCount = ElementFinder.finds (signupPage ().getProductName (),
            WaitStrategy.VISIBLE);
        final int productsName = productsCount.size ();
        for (int i = 0; i < productsName; i++) {
            final String pName = productsCount.get (i)
                .getText ();
            if (pName.equalsIgnoreCase ("PG 3")) {
                ClickableActions.withMouse (signupPage ().getAddToCart ())
                    .click ();
                // ClickableActions.withMouse (productsCount.get (0)).clickElement ();
                break;
            }
        }
        ElementFinder.find (signupPage ().getPg3Shoes (), WaitStrategy.VISIBLE);
        ClickableActions.withMouse (signupPage ().getCartButton ())
            .click ();
        ElementFinder.find (signupPage ().getCartPageTitle (), "text", "Cart");
        final String lastPageProduct = ElementActions.onElement (signupPage ().getAddedProduct ())
            .getText ();
        Assert.assertEquals (lastPageProduct, "PG 3");
    }

    @Test (dependsOnMethods = { "fillForm" })
    public void getTotalPrice () {
        final List<WebElement> products = ElementFinder.finds (signupPage ().getAddToCart (), WaitStrategy.VISIBLE);
        for (int i = 0; i < products.size (); i++) {
            ClickableActions.withMouse (products.get (i))
                .clickElement ();
        }
        ClickableActions.withMouse (signupPage ().getCartButton ())
            .click ();
        final List<WebElement> productPrices = ElementFinder.finds (signupPage ().getProductPrice (),
            WaitStrategy.VISIBLE);
        final int count = productPrices.size ();
        double sum = 0;
        for (int i = 0; i < count; i++) {
            final String amountString = productPrices.get (i)
                .getText ();
            final double price = CommonActions.getFormattedAmount (amountString);
            sum = sum + price;
        }
        final String expAmount = ElementActions.onElement (signupPage ().getTotalPurchaseAMount ())
            .getText ();
        Assert.assertEquals ("$" + " " + sum, expAmount);
        ClickableActions.withMouse (signupPage ().getSendCheckBox ())
            .click ();
        ClickableActions.withMouse (signupPage ().getVisitWebsiteButton ())
            .click ();
        final String contexts = WindowActions.onWindow ()
            .getContextHandles ();
        System.out.println (contexts);
    }
}


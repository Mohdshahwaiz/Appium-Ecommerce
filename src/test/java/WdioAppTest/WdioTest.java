package WdioAppTest;

import static action.ClickableActions.withMouse;
import static action.ElementActions.onElement;
import static action.TextBoxActions.onTextBox;
import static pages.WdioSignUp.getWdioSignUp;
import static pages.HomePage.getHomePage;

import action.ClickableActions;
import action.ElementActions;
import action.TextBoxActions;
import base.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.WdioSignUp;

public class WdioTest extends BaseTest {

    @Test
    public void signUp () {
        withMouse (getHomePage ().getHome ()).click ();
        withMouse (getWdioSignUp ().getSignUpButton ()).click ();
        onTextBox (getWdioSignUp ().getEmailField ()).enterText ("Shahwaiz123@gmail.com");
        onTextBox (getWdioSignUp ().getPasswordField ()).enterText ("12345678");
        onTextBox (getWdioSignUp ().getConfirmPassword ()).enterText ("12345678");
        ClickableActions.withMouse (getWdioSignUp ().getSignUpFormButton ())
            .click ();
    }

    @Test
    public void verifyErrorMessage () {
        withMouse (getHomePage ().getHome ()).click ();
        withMouse (getWdioSignUp ().getSignUpButton ()).click ();
        onTextBox (getWdioSignUp ().getEmailField ()).enterText ("Shahwaiz123@gmail.com");
        onTextBox (getWdioSignUp ().getPasswordField ()).enterText ("123456");
        withMouse (getWdioSignUp ().getSignUpFormButton ()).click ();
        onElement (getWdioSignUp ().getErrorMessage ()).verifyText ()
            .isEqualTo ("Please enter at least 8 characters");
    }
}
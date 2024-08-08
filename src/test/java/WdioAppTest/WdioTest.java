package WdioAppTest;

import static action.ClickableActions.withMouse;
import static action.ElementActions.onElement;
import static action.TextBoxActions.onTextBox;
import static pages.WdioSignUp.getWdioSignUp;
import static pages.HomePage.getHomePage;

import action.ClickableActions;
import action.ElementActions;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FormPage;
import pages.LoginPage;
import pages.WdioSignUp;

public class WdioTest extends BaseTest {

    @Test (priority = 1)
    public void signUp () {
        withMouse (getHomePage ().getHome ()).click ();
        withMouse (getWdioSignUp ().getSignUpButton ()).click ();
        onTextBox (getWdioSignUp ().getEmailField ()).enterText ("Shahwaiz123@gmail.com");
        onTextBox (getWdioSignUp ().getPasswordField ()).enterText ("12345678");
        onTextBox (getWdioSignUp ().getConfirmPassword ()).enterText ("12345678");
        ClickableActions.withMouse (getWdioSignUp ().getSignUpFormButton ())
            .click ();
        String actMessage = ElementActions.onElement (WdioSignUp.getWdioSignUp ()
                .getSuccessfullyMessage ())
            .getText ();
        String expMessage = "You successfully signed up!";
        Assert.assertEquals (expMessage, actMessage);
    }

    @Test (priority = 2)
    public void verifyErrorMessage () {
        withMouse (getHomePage ().getHome ()).click ();
        withMouse (getWdioSignUp ().getSignUpButton ()).click ();
        onTextBox (getWdioSignUp ().getEmailField ()).enterText ("Shahwaiz123@gmail.com");
        onTextBox (getWdioSignUp ().getPasswordField ()).enterText ("123456");
        withMouse (getWdioSignUp ().getSignUpFormButton ()).click ();
        onElement (getWdioSignUp ().getErrorMessage ()).verifyText ()
            .isEqualTo ("Please enter at least 8 characters");
    }

    @Test (priority = 3)
    public void loginUser () {
        withMouse (getHomePage ().getLoginButton ()).click ();
        onTextBox (getWdioSignUp ().getEmailField ()).enterText ("Shahwaiz123@gmail.com");
        onTextBox (getWdioSignUp ().getPasswordField ()).enterText ("12345678");
        withMouse (LoginPage.getLoginPage ()
            .getLogin ()).click ();
        onElement (LoginPage.getLoginPage ()
            .getSuccessAlert ()).verifyText ()
            .isEqualTo ("Success");
        onElement (LoginPage.getLoginPage ()
            .getLoggedInMessage ()).verifyText ()
            .isEqualTo ("You are logged in!");
    }

    @Test (priority = -1)
    public void fillForm () {
        withMouse (getHomePage ().getFormsButton ()).click ();
        onTextBox (FormPage.getformPage ()
            .getInputField ()).enterText ("Shahwaiz");
        String actText = ElementActions.onElement (FormPage.getformPage ()
                .getInputTextResult ())
            .getText ();
        Assert.assertEquals ("Shahwaiz", actText);
        withMouse (FormPage.getformPage ().getSwitchButton ()).click ();
        withMouse (FormPage.getformPage ().getDropDown ()).click ();

        withMouse (FormPage.getformPage ().getDropDownValue ()).click ();
    }

}
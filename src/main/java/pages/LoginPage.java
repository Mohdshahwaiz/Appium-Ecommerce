package pages;

import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class LoginPage {

    public static final LoginPage LOGIN_PAGE =new LoginPage ();
    public static LoginPage getLoginPage(){
        return LOGIN_PAGE;

    }

    private final By login=By.id ("button-LOGIN");
    private final By successAlert=By.xpath ("//XCUIElementTypeStaticText[@name='Success']");
    private final By loggedInMessage=By.id ("You are logged in!");

}

package pages;

import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class FormPage {

    private static final FormPage FORM_PAGE=new FormPage ();

    public static final FormPage getformPage(){
        return FORM_PAGE;
    }

    private final By inputField=By.id ("text-input");
    private final By youHaveTyped=By.id ("You have typed:");
    private final By inputTextResult=By.id ("input-text-result");
    private final By switchButton=By.id ("switch");
    private final By radioButtonBottomText=By.id ("switch-text");
    private final By dropDown=By.xpath ("//XCUIElementTypeStaticText[@name=\"\uDB80\uDD40\"]");
    private final By dropDownValue=By.xpath ("//XCUIElementTypePickerWheel[@value=\"This app is awesome\"]");

}

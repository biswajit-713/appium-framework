package api.apps.wunderlist.passwordreset;

import api.Platform;
import core.UiObject;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PasswordResetUiObjects {

    @AndroidFindBy(id = "forgot_password_email_edittext")
    WebElement email;

    @AndroidFindBy(id = "forgot_password_button")
    WebElement passwordReset;

    public PasswordResetUiObjects() {
        PageFactory.initElements(new AppiumFieldDecorator(Platform.driver), this);
    }

    public UiObject email() {
        return new UiObject(email);
    }
    public UiObject passwordReset() {
        return new UiObject(passwordReset);
    }
}

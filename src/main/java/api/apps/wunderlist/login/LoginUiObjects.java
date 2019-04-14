package api.apps.wunderlist.login;

import api.Platform;
import core.UiObject;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LoginUiObjects {

    @AndroidFindBy(id = "login_email_edittext")
    WebElement email;

    @AndroidFindBy(id = "login_password_edittext")
    WebElement password;

    @AndroidFindBy(id = "login_button")
    WebElement signIn;

    @AndroidFindBy(id = "forgot_password_textView")
    WebElement forgotPassword;

    @AndroidFindBy(id = "forgot_password_email_edittext")
    WebElement forgotPasswordEmail;

    @AndroidFindBy(id = "forgot_password_button")
    WebElement passwordReset;

    public LoginUiObjects() {
        PageFactory.initElements(new AppiumFieldDecorator(Platform.driver), this);
    }

    public UiObject getEmail() {
        return new UiObject(email);
    }

    public UiObject getPassword() {
        return new UiObject(password);
    }

    public UiObject getSignIn() {
        return new UiObject(signIn);
    }

    public UiObject forgotPassword() {
        return new UiObject(forgotPassword);
    }

    public UiObject forgotPasswordEmail() {
        return new UiObject(forgotPasswordEmail);
    }

    public UiObject passwordReset() {
        return new UiObject(passwordReset);
    }
}

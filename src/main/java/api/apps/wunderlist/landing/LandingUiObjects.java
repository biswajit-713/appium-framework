package api.apps.wunderlist.landing;

import api.Platform;
import core.UiObject;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LandingUiObjects {

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.wunderkinder.wunderlistandroid:id/CreateAccountButton\")")
    WebElement getStarted;

    @AndroidFindBy(id = "login_textView_sign_in")
    WebElement signIn;

    @AndroidFindBy(id = "WL_SSV_Title")
    WebElement titleText;

    @AndroidFindBy(id = "WL_SSV_Description")
    WebElement titleDescription;


    public LandingUiObjects() {
        PageFactory.initElements(new AppiumFieldDecorator(Platform.driver), this);
    }

    public UiObject getStarted() {
        return new UiObject(getStarted);
    }

    public UiObject getSignIn() {
        return new UiObject(signIn);
    }

    public UiObject getTitleText() {
        return new UiObject(titleText);
    }

    public UiObject titleDescription() {
        return new UiObject(titleDescription);
    }

}

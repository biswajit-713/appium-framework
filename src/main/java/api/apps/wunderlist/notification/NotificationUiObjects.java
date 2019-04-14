package api.apps.wunderlist.notification;

import api.Platform;
import core.UiObject;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class NotificationUiObjects {

    @AndroidFindBy(id = "message")
    WebElement message;

    @AndroidFindBy(id = "button1")
    WebElement okButton;

    public NotificationUiObjects() {
        PageFactory.initElements(new AppiumFieldDecorator(Platform.driver), this);
    }

    public UiObject message() {
        return new UiObject(message);
    }

    public UiObject okButton() {
        return new UiObject(okButton);
    }
}

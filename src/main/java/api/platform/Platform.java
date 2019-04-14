package api;

import api.platform.app.App;
import core.ADB;
import io.appium.java_client.AppiumDriver;

public class Platform {
    public static AppiumDriver driver;
    public static ADB adb;
    public static App app;

    public static App initializeApp() {
        if (app == null) {
            return new App();
        }
        return app;
    }
}

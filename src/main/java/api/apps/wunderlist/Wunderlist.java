package api.apps.wunderlist;

import api.Platform;
import api.apps.wunderlist.landing.LandingPage;
import api.apps.wunderlist.login.LoginPage;
import api.apps.wunderlist.notification.NotificationPage;
import api.apps.wunderlist.passwordreset.PasswordResetPage;
import api.interfaces.Application;

public class Wunderlist implements Application {

    public LandingPage landingPage = new LandingPage();
    public LoginPage loginPage = new LoginPage();
    public PasswordResetPage passwordResetPage = new PasswordResetPage();
    public NotificationPage notificationPage = new NotificationPage();

    @Override
    public void forceStop() {
        Platform.adb.forceStopApp(getPackageID());
    }

    @Override
    public void clearData() {
        Platform.adb.clearAppData(getPackageID());
    }

    @Override
    public Object open() {
        Platform.adb.launchAppActivity(getPackageID(), getActivityID());
        return null;
    }

    @Override
    public String getPackageID() {
        return "com.wunderkinder.wunderlistandroid";
    }

    @Override
    public String getActivityID() {
        return "com.wunderkinder.wunderlistandroid.activity.WLStartViewFragmentActivity";
    }

    @Override
    public String getAppVersion() {
        return Platform.adb.getAndroidVersion();
    }
}

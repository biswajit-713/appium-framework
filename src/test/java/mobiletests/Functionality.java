package mobiletests;

import api.Platform;
import api.apps.wunderlist.Wunderlist;
import api.apps.wunderlist.login.LoginPage;
import api.apps.wunderlist.notification.NotificationPage;
import api.apps.wunderlist.passwordreset.PasswordResetPage;
import core.managers.TestManager;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class Functionality extends TestManager {
    public static Wunderlist wunderlist = Platform.app.wunderlist;

    @Test
    public void verifyUserCanSendInstructionToEmailWhenTheyForgetPassword() throws InterruptedException {

        LoginPage loginPage = wunderlist.landingPage.tapSignIn();
        PasswordResetPage passwordResetPage = loginPage.forgotPassword();
        Assert.assertFalse(passwordResetPage.isPasswordResetDisabled());
        NotificationPage notificationPage = passwordResetPage.resetPassword("a.b@gmail.com");

        Assert.assertEquals("Incorrect notification message",
                "Please check your email for instructions to reset your password.",
                notificationPage.getInAppNotificationMessage());
        notificationPage.confirmInAppNotification();
    }

    @Test
    @Ignore
    public void getStarted() throws InterruptedException {
        LoginPage loginPage = wunderlist.landingPage.tapGetStarted();
    }
}

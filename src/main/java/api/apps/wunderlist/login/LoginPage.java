package api.apps.wunderlist.login;

import api.Platform;
import api.apps.wunderlist.notification.NotificationPage;
import api.apps.wunderlist.passwordreset.PasswordResetPage;
import core.report.Reporter;
import org.apache.log4j.Logger;

import java.util.NoSuchElementException;

public class LoginPage {
    private static Logger logger = Logger.getLogger(LoginPage.class);

    public LoginUiObjects uiObject = new LoginUiObjects();

    public void signIntoWunderlist(String email, String password) {

    }

    public PasswordResetPage forgotPassword() throws InterruptedException{
        logger.info("clicking forgot password link");
        Reporter.testStep("Tap on forgot password button");
        try {
            uiObject.forgotPassword().tap();
            Platform.app.wunderlist.passwordResetPage.uiObjects.email().waitToAppear(3);
            return Platform.app.wunderlist.passwordResetPage;
        } catch (NoSuchElementException e) {
            logger.error("Error occured while tapping Forgot password", e);
            throw new AssertionError(e);
        }
    }

    public NotificationPage resetPassword(String email) throws InterruptedException{
        Reporter.testStep("send reset password instruction to email");
        try {
            uiObject.forgotPasswordEmail().typeText(email);
            uiObject.passwordReset().tap();
            Platform.app.wunderlist.notificationPage.uiObjects.message().waitToAppear(5);
            return Platform.app.wunderlist.notificationPage;
        } catch (NoSuchElementException e) {
            logger.error("unable to reset password", e);
            throw new AssertionError(e);
        }
    }
}

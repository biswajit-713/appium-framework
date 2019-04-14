package api.apps.wunderlist.passwordreset;

import api.Platform;
import api.apps.wunderlist.notification.NotificationPage;
import core.report.Reporter;
import org.apache.log4j.Logger;

import java.util.NoSuchElementException;

public class PasswordResetPage {
    private static Logger logger = Logger.getLogger(PasswordResetPage.class);
    public PasswordResetUiObjects uiObjects = new PasswordResetUiObjects();

    public NotificationPage resetPassword(String email) throws InterruptedException{
        logger.info("Sending password reset instruction to email id");
        Reporter.testStep("enter email id to send password instruction");

        try {
            uiObjects.email().typeText(email);
            uiObjects.passwordReset().tap();
            Platform.app.wunderlist.notificationPage.uiObjects.message().waitToAppear(10);
            return Platform.app.wunderlist.notificationPage;
        } catch (NoSuchElementException e) {
            logger.error("unable to reset password", e);
            throw new AssertionError(e);
        }
    }

    public boolean isPasswordResetDisabled() {
        logger.info("Check if Password Reset button is disabled");
        return uiObjects.passwordReset().isEnabled();
    }
}

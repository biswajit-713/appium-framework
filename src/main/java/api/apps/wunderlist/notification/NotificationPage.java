package api.apps.wunderlist.notification;

import core.UiObject;
import core.report.Reporter;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriverException;

import java.util.NoSuchElementException;

public class NotificationPage {
    private static Logger logger = Logger.getLogger(NotificationPage.class);

    public NotificationUiObjects uiObjects = new NotificationUiObjects();

    public String getInAppNotificationMessage() {
        Reporter.testStep("Get the notification details");

        String message;
        try {
            message = uiObjects.message().getText();
            logger.info("Notification message - " + message);
            return message;
        } catch (NoSuchElementException e) {
            logger.error("Notification message not found", e);
            throw new AssertionError(e);
        }
    }

    public void confirmInAppNotification() throws InterruptedException {
        logger.info("Tapping ok button");
        Reporter.testStep("Confirm the notification");
        try {
            uiObjects.okButton().tap();
//            uiObjects.okButton().waitToDisappear(1);

            logger.info("Tapped ok button successfully");
        } catch (Exception e) {
            logger.error(e);

            if (!(e instanceof NoSuchElementException)) {
                throw new AssertionError(e);
            }
        }
    }
}

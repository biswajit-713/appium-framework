package api.apps.wunderlist.landing;

import api.Platform;
import api.apps.wunderlist.login.LoginPage;
import api.utils.AppUtils;
import core.report.Reporter;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;

public class LandingPage {

    private static Logger logger = Logger.getLogger(LandingPage.class);


    public LandingUiObjects uiObject = new LandingUiObjects();

    public LoginPage tapGetStarted() throws InterruptedException {
        logger.info("Tapping on Get Started button");
        Reporter.testStep("Tap Get Started");
        try {
            uiObject.getStarted().tap();
            Platform.app.wunderlist.loginPage.uiObject.getEmail().waitToAppear(3);
            return Platform.app.wunderlist.loginPage;
        } catch (NoSuchElementException e) {
            logger.error("Get Started is not clickable", e);
            throw new AssertionError("Get Started button is not clickable");
        }
    }

    public LoginPage tapSignIn() throws InterruptedException {
        logger.info("Tapping on SignIn link");
        Reporter.testStep("Tap Sign in");
        try {
            uiObject.getSignIn().tap();
            Platform.app.wunderlist.loginPage.uiObject.getEmail().waitToAppear(3);
            return Platform.app.wunderlist.loginPage;
        } catch (AssertionError e) {
            logger.error("SignIn page didn't load", e);
            throw new AssertionError("SignIn page didn't load");
        } catch (NoSuchElementException e) {
            logger.error("SignIn is not clickable", e);
            throw new AssertionError("SignIn link is not clickable");
        }
    }

    public String getTitleText() {
        Reporter.testStep("Get title text");
        try {
            logger.info("fetching the title text shown");
            return uiObject.getTitleText().getText();
        } catch (NoSuchElementException e) {
            logger.error("Title text not displayed", e);
            throw new AssertionError("Title text is not shown");
        }
    }

    public String getTitleDescription() {
        Reporter.testStep("Get title description");
        try {
            logger.info("fetching the title text shown");
            return uiObject.titleDescription().getText();
        } catch (NoSuchElementException e) {
            logger.error("Title text not displayed", e);
            throw new AssertionError("Title text is not shown");
        }
    }

    public void swipeLeft() {
        logger.info("swipe to left on landing page");
        AppUtils.swipe(AppUtils.DIRECTION.LEFT);
    }

    public void swipeRight() {
        logger.info("swipe to left on landing page");
        AppUtils.swipe(AppUtils.DIRECTION.RIGHT);
    }

    public void swipeUp() {
        logger.info("swipe to left on landing page");
        AppUtils.swipe(AppUtils.DIRECTION.UP);
    }

    public void swipeDown() {
        logger.info("swipe to left on landing page");
        AppUtils.swipe(AppUtils.DIRECTION.DOWN);
    }
}

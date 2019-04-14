package core;

import api.Platform;
import com.aventstack.extentreports.Status;
import core.report.Reporter;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UiObject {
    private String locator;
    private WebElement element;

    private static Logger logger = Logger.getLogger(UiObject.class);

    public UiObject(String locator) {
        this.locator = locator;
        logger.info("Locator provided: " + locator);
    }

    public UiObject(WebElement element) {
        this.element = element;
    }

    private boolean isUiSelector() {
        return locator.contains("UiSelector");
    }

    public boolean exists(int waitTimeInMilliSeconds) throws InterruptedException {
        try {
            WebDriverWait wait = new WebDriverWait(Platform.driver, waitTimeInMilliSeconds);
            element = wait.until(ExpectedConditions.visibilityOf(element));
            logger.info("found element with locator " + element.toString());
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            logger.info("Element not found");
            return false;
        }
    }


//    TODO - isChecked
//    TODO - isCheckable
//    TODO - isClickable
//    TODO - isEnabled
    public boolean isEnabled() {
        try {
            logger.info("Is element enabled : " + element.getAttribute("enabled"));
            return element.getAttribute("enabled").equalsIgnoreCase("true");
        } catch (NoSuchElementException e) {
            logger.error("Element not found", e);
            return false;
        }
    }
//    TODO - isFocusable
//    TODO - isFocused
//    TODO - isScrollable
//    TODO - isLongClickable
//    TODO - isSelected



//    TODO - get Text
//    TODO - get resource id - an attribute
//    TODO - get class name
//    TODO - get content description


    public UiObject typeText(String value) {
        logger.info("entering " + value + " in element with locator " + element.toString());
        try {
            element.sendKeys(value);
            Reporter.event("Type text in " + element.toString()).log(Status.PASS,"text entered as " + value);
        } catch (Exception e) {
            logger.error("Unable to enter text to " + element.toString(), e);
            Reporter.event("Type text in " + element.toString()).log(Status.FAIL,"Unable to enter text");
        }
        return this;
    }

    public UiObject tap(){
        String elementText = element.toString();
        logger.info("Tapping on element " + elementText);
        try {
            element.click();
            Reporter.event("tap on " + elementText).log(Status.PASS, "Test Passed");
        } catch (Exception e) {
            logger.error("Failed to click on " + elementText, e);
            Reporter.event("tap on " + elementText).log(Status.FAIL, "Test Failed");
        }
        return this;
    }


    public UiObject waitToAppear(int seconds) throws InterruptedException {
        logger.info("wait for maximum " + seconds + " seconds for " + element.toString() + " to appear");
        if (exists(seconds * 1000)) {
            logger.info("found element with locator " + element.toString());
            Reporter.event("wait for " + element.toString() + " to appear").log(Status.PASS,"object loaded");
        } else {
            logger.error(element.toString() + " didn't load in " + seconds + " seconds");
            Reporter.event("wait for " + element.toString() + " to appear").log(Status.FAIL,"object didn't load");
            throw new AssertionError(element.toString() + " not found");
        }
        return this;
    }

    public UiObject waitToDisappear(int secondsToWait) throws InterruptedException {
        int timeCounter = 0;

        boolean elementNotFound = false;

        while (true) {
            try {
                if (!exists(1000)) {
                    elementNotFound = true;
                    break;
                }

                if (!element.isDisplayed()) {
                    elementNotFound = true;
                    break;
                }
            } catch (NoSuchElementException e) {
                logger.info("element disappeared");
                break;
            }

            timeCounter++;
            if (timeCounter > secondsToWait) {
                logger.error("element didn't disappear after " + secondsToWait + " seconds");
                throw new AssertionError("element didn't disappear after " + secondsToWait + " seconds");
            }
            Thread.sleep(1000);
        }

        if (elementNotFound) {
            logger.info("Element disappeared after " + (secondsToWait - timeCounter) + " seconds.");
            Reporter.event("Element disappeared after " + (secondsToWait - timeCounter) + " seconds.")
                .log(Status.PASS, "event passed");
        } else {
            logger.error("Element didn't disappear after " + secondsToWait + " seconds.");
            Reporter.event("Element didn't disappear after " + secondsToWait + " seconds.")
                    .log(Status.FAIL, "event failed");
            throw new AssertionError("Element didn't disappear");
        }
        return this;
    }


    public String getText() {
        String text = element.getText();
        logger.info("Text retrieved is " + text);
        Reporter.event("text retrieved is " + text).log(Status.PASS, "attribute retrieved");
        return text;
    }

}

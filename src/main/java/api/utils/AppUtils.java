package api.utils;

import api.Platform;
import io.appium.java_client.TouchAction;
import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

public class AppUtils {

    public static Logger logger = Logger.getLogger(AppUtils.class);

    public enum DIRECTION {
        DOWN, UP, LEFT, RIGHT
    }

    private static Dimension getScreenDimension() {
        Dimension dimension = Platform.driver.manage().window().getSize();
        logger.info("Mobile screen dimension - width: " + dimension.width + ", height: " + dimension.height);
        return dimension;
    }

    public static void swipe(DIRECTION direction) {
        logger.info("Swiping to " + direction);

        Dimension dimension = getScreenDimension();
        Point fromPoint = null, toPoint= null;

        TouchAction action = new TouchAction(Platform.driver);

        switch (direction) {
            case LEFT:
                fromPoint = new Point((int)(dimension.width * 0.90), dimension.height/2);
                toPoint = new Point((int)(dimension.width * 0.05), dimension.height/2);
                break;
            case RIGHT:
                fromPoint = new Point((int)(dimension.width * 0.05), dimension.height/2);
                toPoint = new Point((int)(dimension.width * 0.90), dimension.height/2);
                break;
            case UP:
                fromPoint = new Point(dimension.width/2, (int) (dimension.height * 0.70));
                toPoint = new Point(dimension.width/2, (int) (dimension.height * 0.30));
                break;
            case DOWN:
                fromPoint = new Point(dimension.width/2, (int) (dimension.height * 0.30));
                toPoint = new Point(dimension.width/2, (int) (dimension.height * 0.70));
                break;
        }
        try {
            action
                    .press(fromPoint.x, fromPoint.y)
                    .waitAction(10)
                    .moveTo(toPoint.x, toPoint.y)
                    .waitAction(10)
                    .release()
                    .perform();
        } catch (Exception e) {
            logger.error("Unable to swipe to " + direction, e);
        }
    }

}

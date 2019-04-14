package core.managers;

import api.Platform;
import core.ADB;
import core.iOSInstrument;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.service.DriverService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class DriverManager {

//    TODO - create a string enum
    private static final String ANDROID = "Android";
    private static final String iOS = "iOS";
    public enum DeviceOS {
        ANDROID,
        iOS
    }

    private static DriverService service;

    private static HashMap<String, URL> host;
    private static String unlockPackage = "io.appium.unlock";

    private static Logger logger = Logger.getLogger(DriverManager.class);

    private static DesiredCapabilities getCapabilities(String deviceId) {
        logger.info("Getting the desired capabilities for device - " + deviceId);

        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (getTestPlatform() == DeviceOS.ANDROID) {
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, ANDROID);
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceId);
//        TODO - move app package and app activity to properties file
            capabilities.setCapability(MobileCapabilityType.APP_PACKAGE, "com.wunderkinder.wunderlistandroid");
            capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, "com.wunderkinder.wunderlistandroid.activity.WLStartViewFragmentActivity");
        } else {
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, iOS);
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceId);
//        TODO - move app package and app activity to properties file
            capabilities.setCapability(MobileCapabilityType.APP_PACKAGE, "com.wunderkinder.wunderlistandroid");
            capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, "com.wunderkinder.wunderlistandroid.activity.WLStartViewFragmentActivity");
        }

        logger.info("capabilities set to - " + capabilities.toString());
        return capabilities;
    }

    private static URL getHost(String deviceId) {
        String url = "http://127.0.0.1:4723/wd/hub";
        if (host == null) {
            logger.info("No appium server url found. setting up one");
            host = new HashMap<>();
            try {
                host.put(deviceId, new URL(url));
                logger.info("New appium server to run at " + url);
            } catch (MalformedURLException e) {
                logger.error("Error occured while initializing host", e);
            }
        }
        return host.get(deviceId);
    }

    private static ArrayList getAvailableDevices() {
        logger.info("Getting all connected devices");

        if (getTestPlatform() == DeviceOS.ANDROID) {
            return ADB.getConnectedDevices();
        } else {
            return iOSInstrument.getConnectedDevices();
        }
    }

//    TODO - get the test platform from gradle system properties captured from command line
    private static DeviceOS getTestPlatform() {
        return DeviceOS.ANDROID;
    }

    public static void createDriver() {

        ArrayList<String> devices = getAvailableDevices();
        for (String device: devices) {
            try {
                logger.info("Creating driver for device " + device);

                if (getTestPlatform() == DeviceOS.ANDROID) {
                    Platform.driver = new AndroidDriver(getHost(device), getCapabilities(device));
                    Platform.adb = new ADB(device);
                    Platform.app = Platform.initializeApp();
                    ADB.disableAutoFill();
                } else if (getTestPlatform() == DeviceOS.iOS) {

                }

                Platform.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                logger.info("Appium server started successfully");
            } catch (Exception e) {
                logger.error("Error occured while creating driver", e);
            }
        }
    }

    public static void killDriver() {
        if (Platform.driver != null) {
            logger.info("quitting the driver");
            Platform.driver.quit();
//            Platform.adb.uninstallApp(unlockPackage);
        } else {
            logger.info("No driver running");
        }
    }

}

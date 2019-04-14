package core.managers;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Scanner;

public class ServerManager {

    private static Logger logger = Logger.getLogger(ServerManager.class);

    private static String OS;
    private static String ANDROID_HOME;

    public static String getOS() {
        if (OS == null) {
            OS = System.getProperty("os.name");
        }
        logger.info("The OS is " + OS);
        return OS;
    }

    public static boolean isMac() {
        return getOS().startsWith("Mac");
    }

    public static String getAndroidHome() {

        if (ANDROID_HOME == null) {
            ANDROID_HOME = System.getenv("ANDROID_HOME");
            if (ANDROID_HOME == null) {
                throw new RuntimeException("Failed to find ANDROID_HOME");
            }
        }
        logger.info("ANDROID_HOME is set to " + ANDROID_HOME);
        return System.getenv("ANDROID_HOME");
    }

    public static String runCommand(String command) {
        String output = null;

        try {
            Scanner scanner = new Scanner(Runtime.getRuntime().exec(command).getInputStream()).useDelimiter("\\A");

            if (scanner.hasNext()) {
                output = scanner.next();
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return output;
    }
}

package core;

import core.managers.ServerManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;

public class ADB {

    private String id;
    private static Logger logger = Logger.getLogger(ADB.class);

    public ADB(String deviceId) {
        this.id = deviceId;
    }

    public static String command(String command) {
        logger.info("Formatting adb command: " + command);

        if (command.startsWith("adb")) {
            command = command.replace("adb", ServerManager.getAndroidHome() + "/platform-tools/adb");
            logger.info("command to be executed - " + command);
        } else {
            logger.warn("non adb command - " + command);
            throw new RuntimeException("Please run adb commands only");
        }
        String output = ServerManager.runCommand(command);
        logger.info("Output of command " + command + " - " + output);

        if (output == null) {
            return "";
        } else {
            return output;
        }
    }

    public static void startServer() {
        command("adb start-server");
    }

    public static void stopServer() {
        command("adb kill-sever");
    }

    public static void disableAutoFill() {
        logger.info("disabling autofill feature of platform oreo and above");
        command("adb shell settings put secure autofill_service null");
    }

    public static void enableAutoFill() {
        command("adb shell settings put secure autofill_service com.google.platform.gms/com.google.platform.gms.autofill.service.AutofillService");
    }

    public static ArrayList getConnectedDevices() {
        ArrayList devices = new ArrayList();
        String output = command("adb devices");
        logger.info("Output of command 'adb devices'"  + " - " + output);

        for(String line: output.split("\n")) {
            if (line.trim().endsWith("device")) {
                devices.add(line.replace("device", "").trim());
                logger.info("Device: " + line.replace("device", "").trim());
            }
        }
        return devices;
    }

    public String getForegroundActivity() {
        return command("adb -s " + this.id + " shell dumpsys | grep mCurrentFocus");
    }

    public String getAndroidVersion() {
        return command("adb -s " + this.id + " shell getprop ro.build.version.release");
    }

    public ArrayList getInstalledPackages() {
        ArrayList packageList = null;
        String[] output = command("adb -s " + this.id + " shell pm list packages").split("\n");

        for (String packageId: output) {
            packageList.add(packageId.replace("package:", "").trim());
        }
        return packageList;
    }

    public void launchAppActivity(String packageId, String activityId) {
//        TODO - launch an app based on app package and activity
    }

    public void clearAppData(String packageId) {
//        TODO - clear the app data
        command("adb -s " + this.id + " pm clear " + packageId);
    }

    public void forceStopApp(String packageId) {
//        TODO - force stop an app when test fails
    }

    public void installApp(String apkPath) {
//        TODO - install an apk
    }

    public void uninstallApp(String packageId) {
//        TODO - uninstall an app based on the package
    }

    public void clearLogBuffer() {
        command("adb -s " + this.id + " shell -c");
    }

    public void pushFile(String source, String target) {
//        TODO - push a file to device
    }

    public void getFile(String source, String target) {
//        TODO - pull file from device
    }

    public void deleteFile() {
//        TODO - delete file from device
    }

    public void takeScreenshot() {
//        TODO - take a screenshot and copy the file to machine
    }

    public String getDeviceModel() {
        return command("adb -s " + this.id + " shell getprop ro.product.vendor.model");
    }

    public ArrayList getLogcatProcesses() {
        String[] output = command("adb -s " + this.id + " shell top -n 1 | grep -i logcat").split("\n");
        ArrayList processes = new ArrayList();

        for (String line: output) {
            processes.add(line.split(" ")[0]);
            processes.removeAll(Arrays.asList("", null));
            logger.info("Logcat process: " + line.split(" ")[0]);
        }
        return processes;
    }
}

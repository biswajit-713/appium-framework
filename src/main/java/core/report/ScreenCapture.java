package core.report;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenCapture {

    private static String fileSeparator = System.getProperty("file.separator");
    private static String screenshotPath = System.getProperty("user.dir") + fileSeparator + "screenshots";

    public static String takeScreenshot(WebDriver driver) throws IOException {
        File sourcefile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(screenshotPath + fileSeparator + System.currentTimeMillis() + ".png");
        FileUtils.copyFile(sourcefile, destFile);
        return destFile.getAbsolutePath();
    }
}

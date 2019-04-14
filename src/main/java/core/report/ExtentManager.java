package core.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import core.managers.ServerManager;

public class ExtentManager {
    private static ExtentReports extent;
    private static String reportFileName = "Test-Automation-Report" + ".html";
    private static String fileSeparator = System.getProperty("file.separator");
    private static String reportPath = System.getProperty("user.dir") + fileSeparator + "reports";
    private static String reportFileLocation = reportPath + fileSeparator + reportFileName;


    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    public static ExtentReports createInstance() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportFileLocation);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(reportFileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(reportFileName);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        extent.setSystemInfo("OS", ServerManager.getOS());
        extent.setSystemInfo("AUT", "QA");

        return extent;
    }
}

package core.managers;

import api.Platform;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import core.TestInfo;
import core.report.ExtentTestManager;
import org.apache.log4j.Logger;
import org.junit.*;
import org.junit.internal.AssumptionViolatedException;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestManager {
    public static TestInfo testInfo = new TestInfo();

    public static Logger logger = Logger.getLogger(TestManager.class);

    ExtentHtmlReporter reporter;
    ExtentReports extent;
    ExtentTest extentLogger;

//    @Rule
//    public Retry retry = new Retry(3);

    @BeforeClass
    public static void beforeClass() {
        DriverManager.createDriver();
    }

    @AfterClass
    public static void afterClass() {
        DriverManager.killDriver();
    }

    @Before
    public void before() {
        logger.info("Launching app");
        Platform.driver.launchApp();
    }

    @After
    public void after() {
        logger.info("Closing the app");
        Platform.driver.closeApp();
        testInfo.reset();
    }

    @Rule
    public TestRule listen = new TestWatcher() {

        @Override
        protected void starting(Description description) {
//            reporter = new ExtentHtmlReporter("./extent-reports/execution-result-" + getTimestamp() +".html");
//            extent = new ExtentReports();
//            extent.attachReporter(reporter);
//            extentLogger = extent.createTest(description.getMethodName());
            logger.info("Starting test... " + description.getClassName() + " : " + description.getMethodName());
            ExtentTestManager.startTest(description.getClassName() + " : " + description.getMethodName());
        }

        @Override
        public void succeeded(Description description) {
            logger.info("Test completed... " + description.getClassName() + " : " + description.getMethodName());
//        TODO - implement Extent Report rule
            ExtentTestManager.getTest().log(Status.PASS, "Test Passed");
//            extentLogger.log(Status.PASS, description.toString());
        }

        @Override
        public void failed(Throwable e, Description description) {
            logger.info("Test failed... " + description.getClassName() + " : " + description.getMethodName());
//        TODO - take screenshot and implement Extent Report rule
            ExtentTestManager.getTest().log(Status.FAIL, e);
//            extentLogger.log(Status.FAIL, e);
        }

        @Override
        protected void skipped(AssumptionViolatedException e, Description description) {
            ExtentTestManager.getTest().log(Status.SKIP, "Test skipped");
//            extentLogger.log(Status.SKIP, e);
        }

        @Override
        protected void finished(Description description) {
            ExtentTestManager.endTest();
//            extent.flush();
        }

    };

    private String getTimestamp() {
        Date date = Calendar.getInstance().getTime();
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy_HH_mm_ss");

        String now = format.format(date);
        return now;
    }
}

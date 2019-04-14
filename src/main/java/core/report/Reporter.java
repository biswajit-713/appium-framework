package core.report;

import com.aventstack.extentreports.ExtentTest;

public class Reporter {

    private static ExtentTest testStep;

    public static void testStep(String step) {
        testStep = ExtentTestManager.getTest().createNode(step);
    }

    private static ExtentTest getTestStep() {
        return testStep;
    }

    public static ExtentTest event(String description) {
        return getTestStep().createNode(description);
    }

}

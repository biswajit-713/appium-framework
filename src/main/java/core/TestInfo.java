package core;

import org.apache.log4j.Logger;
import sun.rmi.runtime.Log;

public class TestInfo {
    private static String TIMESTAMP, SUITE, ID, NAME, NOTES, EXPECTED, ACTUAL, VERSION;
    private static Logger logger = Logger.getLogger(TestInfo.class);

    public TestInfo reset() {
        TIMESTAMP = null;
        SUITE = null;
        ID = null;
        NAME = null;
        NOTES = null;
        EXPECTED = null;
        ACTUAL = null;
        VERSION = null;
        return this;
    }

    public TestInfo timestamp(String value) {
        logger.info("setting timestamp to " + value);
        TIMESTAMP = value;
        return this;
    }
    public TestInfo suite(String value) {
        logger.info("setting suite to " + value);
        SUITE = value;
        return this;
    }
    public TestInfo id(String value) {
        logger.info("setting id to " + value);
        ID = value;
        return this;
    }
    public TestInfo name(String value) {
        logger.info("setting name to " + value);
        NAME = value;
        return this;
    }
    public TestInfo notes(String value) {
        logger.info("setting notes to " + value);
        NOTES = value;
        return this;
    }
    public TestInfo expected(String value) {
        logger.info("setting expected result to " + value);
        EXPECTED = value;
        return this;
    }
    public TestInfo actual(String value) {
        logger.info("setting actual result to " + value);
        ACTUAL = value;
        return this;
    }
    public TestInfo version(String value) {
        VERSION = value;
        return this;
    }

    public static String timestamp() {
        return TIMESTAMP;
    }

    public static String suite() {
        return SUITE;
    }

    public static String id() {
        return ID;
    }

    public static String name() {
        return NAME;
    }

    public static String notes() {
        return NOTES;
    }

    public static String expected() {
        return EXPECTED;
    }

    public static String actual() {
        return ACTUAL;
    }

    public static String version() {
        return VERSION;
    }
}

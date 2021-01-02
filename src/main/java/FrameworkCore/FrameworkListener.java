package FrameworkCore;

import configuration.Environment;
import io.restassured.RestAssured;
import org.apache.logging.log4j.Logger;
import org.testng.*;


public class FrameworkListener implements ISuiteListener, ITestListener {

    private Logger log = LoggerHook.log;

    /**
     * Function That executes on Start of TestSuite
     */
    public void onStart(ISuite iSuite) {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        new Environment();
        new RestAssureInitializer();
        /* check if the service under test is up before running any tests */
        if (!APIServerChecker.isAPIServerUp())
            throw new SkipException("Skipped as backed end API is Down");
    }

    /**
     * Function That executes at the end of TestSuite
     */
    public void onFinish(ISuite iSuite) {
        log.info("Finished running all the tests.");
    }

    /**
     * Function That executes at the start of Testcase
     */
    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.info("Starting test " + iTestResult.getName());
    }

    /**
     * Function That executes upon successful execution of Testcase
     */
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log.info("Test " + iTestResult.getName() + " PASSED");
    }

    /**
     * Function That executes upon failure of Testcase
     */
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log.error("Test " + iTestResult.getName() + "  FAILED");
    }

    /**
     * Function That executes upon skip of Testcase
     */
    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.warn("Test " + iTestResult.getName() + " SKIPPED");
    }

}

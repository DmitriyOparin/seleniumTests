package ru.tests.selenium.tests;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import ru.tests.selenium.app.Application;

public class TestBase {
    public static Application app;

    @BeforeSuite
    @Parameters({"browser"})
    public void setUp(String browser) {
        app = new Application(browser);
    }

    @AfterMethod
    public void screenshotIfTestFail(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String name = result.getName();
            app.screenshot(name);
        }
    }

    @AfterSuite
    public void tearDown() {
        app.quit();
    }
}

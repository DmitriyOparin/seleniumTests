package ru.tests.selenium.tests;

import org.testng.ITestResult;
import org.testng.annotations.*;
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
            String nameTest = result.getName();
            app.screenshot(nameTest);
//            app.logout();
        }
    }

    @AfterSuite
    public void tearDown() {
        app.quit();
    }

    @AfterClass
    public void logout(){
        app.logout();
    }
}

package ru.tests.selenium.tests;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import ru.tests.selenium.app.Application;

public class TestBase {
    public static Application app;

    @BeforeSuite
    @Parameters({"browser"})
    public void setUp(String browser, ITestContext context) {
        app = new Application(browser);
        context.setAttribute("app", app);
    }

    @AfterSuite
    public void tearDown() {
        app.quit();
    }

    @AfterClass
    public void logout() {
        app.logout();
    }
}

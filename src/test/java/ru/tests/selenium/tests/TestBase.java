package ru.tests.selenium.tests;

import org.testng.annotations.*;
import ru.tests.selenium.app.Application;

public class TestBase {
    public static Application app;

    @BeforeSuite
    @Parameters({"browser"})
    public void setUp(String browser) {
        app = new Application(browser);
    }

    @AfterSuite
    public void tearDown() {
        app.quit();
    }
}

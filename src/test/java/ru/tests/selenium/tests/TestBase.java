package ru.tests.selenium.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import ru.tests.selenium.app.Application;

public class TestBase {
    public Application app;

    @BeforeClass
    public void setUp() {
        app = new Application();
    }

    @AfterClass
    public void tearDown() {
        app.quit();
    }
}

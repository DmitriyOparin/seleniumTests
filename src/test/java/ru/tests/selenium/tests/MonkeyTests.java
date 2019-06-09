package ru.tests.selenium.tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MonkeyTests extends TestBase {
    @Test
    @Severity(value = SeverityLevel.NORMAL)
    @Parameters({"countLoop"})
    public void MonkeyTest(Integer countLoop){
        app.randomInputAndClick(countLoop);
    }
}

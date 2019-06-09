package ru.tests.selenium.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MainTests extends TestBase {

    @Test
    @Description(value = "Тест проверяет вход на сайт")
    @Severity(value = SeverityLevel.CRITICAL)
    @Parameters({"login", "password"})
    public void loginTest(String login, String password){
        app.login(login, password);
        app.controlLogin(login);
    }

    @Test(dependsOnMethods = "loginTest")
    @Description(value = "Тест проверяет добавление статьи")
    @Severity(value = SeverityLevel.CRITICAL)
    public void addNewPostTest(){
        String titlePost = app.generationTitlePost();
        String textPost = app.generationTextPost();
        app.addNewPost(titlePost, textPost);
        app.gotoMainPage();
        app.controlAddNewPost(titlePost, textPost);
    }

    @Test(dependsOnMethods = "addNewPostTest")
    @Flaky
    @Description(value = "Тест проверяет добавление статьи через JS")
    @Severity(value = SeverityLevel.MINOR)
    public void addNewPostTestJsExecutor(){
        String titlePost = app.generationTitlePost();
        String textPost = app.generationTextPost();
        app.addNewPostJsExecutor(titlePost, textPost);
        app.gotoMainPage();
        app.controlAddNewPost(titlePost, textPost);
    }
}
package ru.tests.selenium.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.tests.selenium.dataProviders.DataProviderGenerationNewPost;

public class MainTests extends TestBase {

    @Test
    @Description(value = "Тест проверяет вход на сайт")
    @Severity(value = SeverityLevel.CRITICAL)
    @Parameters({"login", "password"})
    public void loginTest(String login, String password){
        app.login(login, password);
        app.controlLogin(login);
    }

    @Test(dependsOnMethods = "loginTest", dataProviderClass = DataProviderGenerationNewPost.class, dataProvider = "generationNewPost")
    @Description(value = "Тест проверяет добавление статьи")
    @Severity(value = SeverityLevel.CRITICAL)
    public void addNewPostTest(String login, String password, String namePost, String textPost){
        app.addNewPost(namePost, textPost);
        app.gotoMainPage();
        app.controlAddNewPost(namePost, textPost);
    }

    @Test(dependsOnMethods = "addNewPostTest", dataProviderClass = DataProviderGenerationNewPost.class, dataProvider = "generationNewPost")
    @Flaky
    @Description(value = "Тест проверяет добавление статьи через JS")
    @Severity(value = SeverityLevel.MINOR)
    public void addNewPostTestJsExecutor(String login, String password, String namePost, String textPost){
        app.addNewPostJsExecutor(namePost, textPost);
        app.gotoMainPage();
        app.controlAddNewPost(namePost, textPost);
//        app.logout();
    }
}
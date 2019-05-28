package ru.tests.selenium.tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.tests.selenium.dataProviders.DataProviderGenerationNewPost;

public class MainTests extends TestBase {

    @Test
    @Parameters({"login", "password"})
    public void loginTest(String login, String password){
        app.login(login, password);
        app.controlLogin(login);
    }

    @Test(dependsOnMethods = "loginTest", dataProviderClass = DataProviderGenerationNewPost.class, dataProvider = "generationNewPost")
    public void addNewPostTest(String login, String password, String namePost, String textPost){
        app.addNewPost(namePost, textPost);
        app.gotoMainPage();
        app.controlAddNewPost(namePost, textPost);
        app.logout();
    }
}
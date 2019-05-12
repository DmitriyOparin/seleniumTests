package ru.tests.selenium.tests;

import org.testng.annotations.Test;

public class MainTests extends TestBase {

    @Test(priority = 1)
    public void loginTest(){
        app.login("admin", "admin");
        app.controlLogin("admin");
    }

    @Test(priority = 2)
    public void addNewPostTest(){
        long currentTimeMillis = System.currentTimeMillis();
        String namePost = "namePost_" + currentTimeMillis;
        String textPost = "textPost_" + currentTimeMillis;

        app.addNewPost(namePost, textPost);
        app.gotoMainPage();
        app.controlAddNewPost(namePost, textPost);
    }
}

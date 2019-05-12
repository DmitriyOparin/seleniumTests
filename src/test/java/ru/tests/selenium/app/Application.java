package ru.tests.selenium.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import ru.tests.selenium.pages.AdministrationPanelPage;
import ru.tests.selenium.pages.LoginPage;
import ru.tests.selenium.pages.MainPage;

import java.util.concurrent.TimeUnit;

public class Application {
    private WebDriver driver;

    private MainPage mainPage;
    private LoginPage loginPage;
    private AdministrationPanelPage administrationPanelPage;

    public Application() {
        System.setProperty("webdriver.chrome.driver", "src/tools/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        administrationPanelPage = new AdministrationPanelPage(driver);
    }

    public void quit() {
        driver.quit();
    }


    public void login(String userName, String userPass) {
        loginPage.open()
                .inputLoginAndPassword(userName, userPass)
                .submitButton.click();
    }

    public void controlLogin(String userName) {
        String getName = administrationPanelPage.userNameButton.getText();
        Assert.assertEquals(userName, getName);
    }

    public void addNewPost(String title, String text) {
        administrationPanelPage.clickButtonAddNewPost();
        administrationPanelPage.inputTitleAndTextPost(title, text);
    }

    public void controlAddNewPost(String title, String text) {

        String getTitle = mainPage.getTitleLastPost();
        Assert.assertEquals(title, getTitle);

        String getText = mainPage.getTextLastPost();
        Assert.assertEquals(text, getText);
    }

    public void gotoMainPage(){
        mainPage.gotoMainPage();
    }
}
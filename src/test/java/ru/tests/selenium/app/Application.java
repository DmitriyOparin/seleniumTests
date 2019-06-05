package ru.tests.selenium.app;

import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import ru.tests.selenium.pages.AdministrationPanelPage;
import ru.tests.selenium.pages.LoginPage;
import ru.tests.selenium.pages.MainPage;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Application {
    private WebDriver driver;

    private MainPage mainPage;
    private LoginPage loginPage;
    private AdministrationPanelPage administrationPanelPage;

    private SoftAssert softAssert;

    public Application(String browser) {
        if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/tools/chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "src/tools/geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (browser.equals("opera")) {
            System.setProperty("webdriver.opera.driver", "src/tools/operadriver.exe");
            driver = new OperaDriver();
        } else if (browser.equals("ie")) {
            System.setProperty("webdriver.ie.driver", "src/tools/IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        administrationPanelPage = new AdministrationPanelPage(driver);
        softAssert = new SoftAssert();
    }

    public void quit() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Step("ввод логина")
    public void login(String userName, String userPass) {
        loginPage.open()
                .inputLoginAndPassword(userName, userPass)
                .submitButton.click();
    }

    public void logout() {
        administrationPanelPage.clickButtonLogout();
    }

    @Step("проверка логина")
    public void controlLogin(String userName) {
        String getName = administrationPanelPage.userNameButton.getText();
//        Assert.assertEquals(userName, getName);
        softAssert.assertEquals(userName, getName, "Not equal logins");
        softAssert.assertAll();
    }

    public void addNewPost(String title, String text) {
//        administrationPanelPage.clickButtonAddNewPost();
        administrationPanelPage.openPageNewPost()
                .inputTitleAndTextPost(title, text);
    }

    public void controlAddNewPost(String title, String text) {
        String getTitle = mainPage.getTitleLastPost();
//        Assert.assertEquals(title, getTitle);
        softAssert.assertEquals(title, getTitle, "Not equal title");

        String getText = mainPage.getTextLastPost();
//        Assert.assertEquals(text, getText);
        softAssert.assertEquals(text, getText, "Not equal text");
        softAssert.assertAll();
    }

    public void gotoMainPage() {
        mainPage.gotoMainPage();
    }

    public void screenshot(String nameTest) {
        File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        new Screenshot(screen, nameTest);
    }

    public void addNewPostJsExecutor(String title, String text) {
        administrationPanelPage.openPageNewPost()
                .inputTitleAndTextPostJsExecutor(title, text);
    }
}
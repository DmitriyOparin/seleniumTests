package ru.tests.selenium.app;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.asserts.SoftAssert;
import ru.tests.selenium.dataGenerators.TextGenerators;
import ru.tests.selenium.pages.AdministrationPanelPage;
import ru.tests.selenium.pages.LoginPage;
import ru.tests.selenium.pages.MainPage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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

    @Step("Вход на сайт логин: {userName}, пароль: {userPass}")
    public void login(String userName, String userPass) {
        loginPage.open()
                .inputLoginAndPassword(userName, userPass)
                .submitButton.click();
    }

    public void logout() {
        administrationPanelPage.clickButtonLogout();
    }

    @Step("Проверка входа юзера: {userName} на сайт")
    public void controlLogin(String userName) {
        String getName = administrationPanelPage.userNameButton.getText();
//        Assert.assertEquals(userName, getName);
        softAssert.assertEquals(userName, getName, "Not equal logins");
        softAssert.assertAll();
    }

    @Step("Добавление новой статьи название: {title}, текст: {text}")
    public void addNewPost(String title, String text) {
//        administrationPanelPage.clickButtonAddNewPost();
        administrationPanelPage.openPageNewPost()
                .inputTitleAndTextPost(title, text);
    }

    @Step("Проверка добавления статьи название: {title}, текст: {text}")
    public void controlAddNewPost(String title, String text) {
        String getTitle = mainPage.getTitleLastPost();
//        Assert.assertEquals(title, getTitle);
        softAssert.assertEquals(title, getTitle, "Not equal title");

        String getText = mainPage.getTextLastPost();
//        Assert.assertEquals(text, getText);
        softAssert.assertEquals(text, getText, "Not equal text");
        softAssert.assertAll();
    }

    @Step("Переход на главную страницу")
    public void gotoMainPage() {
        mainPage.gotoMainPage();
    }


    public byte[] takeScreenshot(String nameTest) {
        File screenFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        byte[] screenBYTES = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Screenshot.saveScreenshot(screenFile, nameTest);
        return screenBYTES;
    }


    @Step("Добавление новой статьи название: {title}, текст: {text}")
    public void addNewPostJsExecutor(String title, String text) {
        administrationPanelPage.openPageNewPost()
                .inputTitleAndTextPostJsExecutor(title, text);
    }

    public String generationTitlePost() {
        return TextGenerators.randomTitleGeneration();
    }

    public String generationTextPost() {
        return TextGenerators.randomTextGeneration();
    }

    public void randomInputAndClick(Integer countLoop) {
        Random rand = new Random();
        mainPage.open();
        for (int i = 0; i < countLoop; i++) {
            List<WebElement> all_input_elements;
            List<WebElement> all_click_elements;

            List<WebElement> all_links = driver.findElements(By.xpath("//a"));
            List<WebElement> all_buttons = driver.findElements(By.xpath("//button"));
            List<WebElement> all_divs = driver.findElements(By.xpath("//div"));

            List<WebElement> all_inputs = driver.findElements(By.xpath("//input"));
            List<WebElement> all_textareas = driver.findElements(By.xpath("//textarea"));

            all_input_elements = joinLists(all_inputs, all_textareas);
            all_click_elements = joinLists(all_links, all_buttons, all_divs);

            if(all_input_elements.size() != 0){
                WebElement rand_input_elem = all_input_elements.get(rand.nextInt(all_input_elements.size()));
                rand_input_elem.sendKeys(TextGenerators.randomMonkeyTextGeneration());
            }

            if(all_click_elements.size() != 0){
                WebElement rand_click_elem = all_click_elements.get(rand.nextInt(all_click_elements.size()));
                rand_click_elem.click();
            }
        }
    }

    public List<WebElement> joinLists(List<WebElement>... args) {
        List<WebElement> result = new ArrayList<WebElement>();
        if (args.length != 0) {
            for (List list : args) {
                if (list.size() != 0) {
                    result.addAll(list);
                }
            }
        }
        return result;
    }
}
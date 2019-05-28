package ru.tests.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AdministrationPanelPage extends PageBase {
    public AdministrationPanelPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "wp-admin-bar-new-content")
    public WebElement addNewContentButton;

    @FindBy(xpath = "//li[@id='wp-admin-bar-my-account']//span")
    public WebElement userNameButton;

    public AdministrationPanelPage open() {
        driver.get("http://wordpresstest.ru/wp-admin/post-new.php");
        return this;
    }

    public void clickButtonAddNewPost() {
        Actions actions = new Actions(driver);
        actions.moveToElement(addNewContentButton).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@id='wp-admin-bar-new-content']" +
                "//li[@id='wp-admin-bar-new-post']"))).click();
    }

    public void inputTitleAndTextPost(String title, String text) {

        driver.findElement(By.xpath("//textarea[@id='post-title-0']"))
                .sendKeys(title);

        driver.findElement(By.xpath("//textarea[contains(@class, 'editor-default-block-appender')]"))
                .click();

        driver.findElement(By.xpath("//p[contains(@class, 'wp-block-paragraph')]"))
                .sendKeys(text);

        clickPublishPost(title);
    }

    private void clickPublishPost(String title) {
        driver.findElement(By.xpath("//div[contains(@class, 'edit-post-header__settings')]" +
                "//button[contains(., 'Опубликовать')]"))
                .click();

        driver.findElement(By.xpath("//div[contains(@class, 'editor-post-publish-panel__header')]" +
                "//button[contains(., 'Опубликовать')]"))
                .click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'post-publish-panel__postpublish')]" +
                "//a[contains(., '" + title + "')]")));
    }

    public void clickButtonLogout() {
        Actions actions = new Actions(driver);
        actions.moveToElement(userNameButton).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@id='wp-admin-bar-logout']"))).click();
    }
}
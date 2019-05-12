package ru.tests.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends PageBase {
    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getTitleLastPost() {
        String title = driver.findElement(By.xpath("//article[position()=1]//header//a"))
                .getText();
        return title;
    }

    public String getTextLastPost() {
        String text = driver.findElement(By.xpath("//article[position()=1]" +
                "//div[contains(@class,'entry-content')]//p"))
                .getText();
        return text;
    }
}

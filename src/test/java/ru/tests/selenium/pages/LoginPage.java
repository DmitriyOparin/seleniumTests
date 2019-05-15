package ru.tests.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends PageBase {
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="user_login")
    public WebElement userLoginInput;

    @FindBy(id="user_pass")
    public WebElement userPassInput;

    @FindBy(id="wp-submit")
    public WebElement submitButton;


    public LoginPage open() {
        driver.get("http://wordpresstest.ru/wp-login.php/");
        return this;
    }

    public LoginPage inputLoginAndPassword(String userName, String userPass){
        userLoginInput.sendKeys(userName);
        userPassInput.sendKeys(userPass);
        return this;
    }


}

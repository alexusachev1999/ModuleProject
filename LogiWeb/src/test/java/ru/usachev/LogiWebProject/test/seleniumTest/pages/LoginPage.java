package ru.usachev.LogiWebProject.test.seleniumTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public WebDriver driver;

    private String login = "test";

    private String password = "test";

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "/html/body/div[2]/div/div/form/div[1]/input")
    private WebElement loginField;

    @FindBy(xpath = "/html/body/div[2]/div/div/form/div[2]/input")
    private WebElement passwordField;

    @FindBy(xpath = "//*[contains(text(), 'Войти')]")
    private WebElement loginButton;

    public void inputLogin(){
        loginField.sendKeys(login);
    }

    public void inputPassword(){
        passwordField.sendKeys(password);
    }

    public void clickOnLoginButton(){
        loginButton.submit();
    }
}

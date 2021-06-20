package ru.usachev.LogiWebProject.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StartPage {
    public WebDriver driver;

    public StartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "/html/body/div/div[2]/div[1]/div/input")
    private WebElement adminButton;

    public void clickOnAdminButton(){
        adminButton.click();
    }
}

package ru.usachev.LogiWebProject.test.seleniumTest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminMenuPage {
    public WebDriver driver;

    public AdminMenuPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "/html/body/div[2]/a")
    private WebElement logoutButton;

    @FindBy(xpath = "/html/body/div[1]/input[1]")
    private WebElement driversButton;

    public void logout(){
        logoutButton.click();
    }

    public String getNameOfDriversButton(){
        String nameOfDriversButton = driversButton.getText();
        return nameOfDriversButton;
    }
}

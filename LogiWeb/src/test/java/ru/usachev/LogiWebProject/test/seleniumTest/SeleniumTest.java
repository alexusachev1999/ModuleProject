package ru.usachev.LogiWebProject.test.seleniumTest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.usachev.LogiWebProject.test.StartPage;
import ru.usachev.LogiWebProject.test.seleniumTest.LoginPage;
import ru.usachev.LogiWebProject.test.seleniumTest.ProfilePage;

public class SeleniumTest {
    private ChromeDriver driver;

    public static LoginPage loginPage;

    public static ProfilePage profilePage;

    public static StartPage startPage;

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "/bin/chromedriver");
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        startPage = new StartPage(driver);
    }

    @Test
    public void presentOfLoginPageTest() {
        driver.get("http://localhost:8099/logiweb/login");

        String title = driver.getTitle();
        Assert.assertEquals(title,"Добро пожаловать");
    }

    @Test
    public void loginTest(){
        scenarioOfLoginAndGetAdminMenu();

        String title = driver.getTitle();
        Assert.assertEquals(title, "Меню администратора");

        profilePage.logout();
    }

    public void scenarioOfLoginAndGetAdminMenu(){
        driver.get("http://localhost:8099/logiweb/login");
        loginPage.inputLogin();
        loginPage.inputPassword();
        loginPage.clickOnLoginButton();
        startPage.clickOnAdminButton();
    }

    @After
    public void close(){
        driver.quit();
    }
}

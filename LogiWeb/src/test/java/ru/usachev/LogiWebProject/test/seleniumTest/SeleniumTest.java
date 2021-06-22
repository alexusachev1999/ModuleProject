package ru.usachev.LogiWebProject.test.seleniumTest;

import org.junit.*;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.usachev.LogiWebProject.test.seleniumTest.pages.LoginPage;
import ru.usachev.LogiWebProject.test.seleniumTest.pages.AdminMenuPage;
import ru.usachev.LogiWebProject.test.seleniumTest.pages.StartPage;

@Ignore
public class SeleniumTest {
    private ChromeDriver driver;

    public static LoginPage loginPage;

    public static AdminMenuPage adminMenuPage;

    public static StartPage startPage;

    private static final String correctLogin = "test";

    private static final String correctPassword = "test";

    private static final String wrongPassword = "wrong";

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "/bin/chromedriver");
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        adminMenuPage = new AdminMenuPage(driver);
        startPage = new StartPage(driver);
    }

    @Test
    public void presentOfLoginPageTest() {
        driver.get("http://localhost:8099/logiweb/login");

        String title = driver.getTitle();
        Assert.assertEquals(title,"Добро пожаловать");
    }

    @Test
    public void loginWithCorrectDataTest(){
        scenarioOfLogin(correctLogin, correctPassword);
        startPage.clickOnAdminButton();

        String title = driver.getTitle();
        Assert.assertEquals(title, "Меню администратора");

        adminMenuPage.logout();
    }

    @Test
    public void loginWithWrongDataTest(){
        scenarioOfLogin(correctLogin, wrongPassword);
        String title = driver.getTitle();
        Assert.assertEquals(title, "Добро пожаловать");
    }

    public void scenarioOfLogin(String login, String password){
        driver.get("http://localhost:8099/logiweb/login");
        loginPage.inputLogin(login);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();
    }

    @After
    public void close(){
        driver.quit();
    }
}

package by.epam.at.mailtest.Steps;

import by.epam.at.mailtest.driver.DriverSingleton;
import by.epam.at.mailtest.pages.MainPage;
import org.openqa.selenium.WebDriver;

public class Steps {

    private WebDriver driver;

    public void initBrowser() {
        driver = DriverSingleton.getDriver();
    }

    public void closeDriver() {
        DriverSingleton.closeDriver();
    }

    public void loginMailRu(String username, String domain, String password) {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.logIn(username, domain, password);
    }

    public boolean isLoggedIn(String username, String domain) {
        MainPage mainPage = new MainPage(driver);
        String actualUsername = mainPage.getLoggedInUserName();
        return actualUsername.equals(username.concat(domain));
    }
}

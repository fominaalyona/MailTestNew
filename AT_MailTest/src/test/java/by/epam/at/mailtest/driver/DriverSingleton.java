package by.epam.at.mailtest.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DriverSingleton {

    private static WebDriver driver;

    private static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";

    private static final String GECKODRIVER_CHROMEDRIVER_EXE_PATH = "C:\\Data\\chromedriver\\chromedriver.exe";

    private DriverSingleton() {
    } ;

    public static WebDriver getDriver() {

        if (null == driver) {

            System.setProperty(WEBDRIVER_CHROME_DRIVER, GECKODRIVER_CHROMEDRIVER_EXE_PATH);

            driver = new ChromeDriver();

            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}

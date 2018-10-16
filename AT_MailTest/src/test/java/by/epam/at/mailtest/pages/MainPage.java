package by.epam.at.mailtest.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class MainPage extends NavigationTools {

    private final String BASE_URL = "https://mail.ru/";
    private final String LOGIN = "//div[@id=\"mailbox:loginContainer\"]/input[@id=\"mailbox:login\"]";
    private final String DOMAIN = "mailbox:domain";
    private final String PASSWORD = "//div[@class=\"mailbox__input__container\"]/input[@id=\"mailbox:password\"]";
    private final String BUTTON = "//label[@id=\"mailbox:submit\"]/input[@class=\"o-control\"]";
    private final String LOGEDNAME = "PH_user-email";
    private final String SIGNIN = "PH_authLink";

    @FindBy(xpath = LOGIN)
    private WebElement textBoxLogin;

    @FindBy(id = DOMAIN)
    private WebElement textBoxDomain;

    @FindBy(xpath = PASSWORD)
    private WebElement textBoxPassword;

    @FindBy(xpath = BUTTON)
    private WebElement loginButton;

    @FindBy(id = LOGEDNAME)
    private WebElement logedName;

    @FindBy(id = SIGNIN)
    private WebElement signin;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

    public void logIn(String login, String domain, String password) {
        textBoxLogin.sendKeys(login);
        Select drpDomain = new Select(textBoxDomain);
        drpDomain.selectByVisibleText(domain);
        textBoxPassword.sendKeys(password);
        loginButton.click();
    }

    public String getLoggedInUserName() {
        try{
        wait.until(ExpectedConditions.visibilityOf(logedName));}
        catch (NoSuchElementException | TimeoutException e) {
            return "Can't find such element on page!";
        }
        return logedName.getText();
    }

    public boolean isLogout(){
        if(signin.isDisplayed()){
            return true;
        }
        return false;
    }
}

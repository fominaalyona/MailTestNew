package by.epam.at.mailtest.pages;

import by.epam.at.mailtest.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavigationTools extends AbstractPage {

    private final String SENDEDLETTERS = "//a[@href=\"/messages/sent/\"]";
    private final String DRAFTS = "//a[@data-mnemo=\"drafts\"]";
    private final String INCOME = "//a[@href=\"/messages/inbox/\"]";
    private final String SPAM = "//a[@href=\"/messages/spam/\"]";
    private final String BASKET = "//a[@href=\"/messages/trash/\"]";
    private final String CLEANBASKET = "//span[@data-name=\"clear-folder\"]";

    @FindBy(xpath = SENDEDLETTERS)
    WebElement sendedLetters;

    @FindBy(xpath = DRAFTS)
    WebElement drafts;

    @FindBy(xpath = INCOME)
    WebElement incomeLetters;

    @FindBy(xpath = SPAM)
    WebElement spam;

    @FindBy(xpath = BASKET)
    WebElement basket;

    @FindBy(xpath = CLEANBASKET)
    WebElement cleanBasket;

    public void openSendedLetters() {
        sendedLetters.click();
    }

    public void openDrafts(){
        drafts.click();
    }

    public void openIncomeLetters(){
        incomeLetters.click();
    }

    public void openSpam(){
        spam.click();
    }

    public void openBasket(){
        basket.click();
    }

    public void cleanBasket(){cleanBasket.click();}

    public NavigationTools(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage() {
    }
}

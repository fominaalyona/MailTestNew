package by.epam.at.mailtest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IncomeLettersPage extends NavigationTools {

    private final String BASE_URL = "https://e.mail.ru/messages/inbox/?back=1";

    private final String WRITELETTER = "//span[@class=\"b-toolbar__btn__text b-toolbar__btn__text_pad\"]";

    @FindBy(xpath = WRITELETTER)
    private WebElement buttonWriteLetter;

    public IncomeLettersPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

    public void writeLetterClick(){
        buttonWriteLetter.click();
    }

}

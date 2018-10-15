package by.epam.at.mailtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class DraftFolderPage extends AbstractPage {

    private final String BASE_URL = "https://e.mail.ru/messages/drafts/";

    private final String LETTER = "//div[@class=\"b-datalist b-datalist_letters b-datalist_letters_to\"]//div[@data-bem =\"b-datalist__item\"]";

    @FindBy(xpath = LETTER)
    private List<WebElement> lettert;

    int index = 0;

    public DraftFolderPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

    public boolean checkDraftFolder(String addressee, String topic, String message){
        index = 0;
        for (WebElement letter:lettert) {
            if(letter.getText().contains(topic + message + "\n" + addressee)){
                return true;
            }
            index++;
        }
        return false;
    }

    public void clickOnDraftLetter(){
       lettert.get(index).click();
    }

    public boolean checkSendDraft(String addressee, String topic, String message){
        index = 0;
        for (WebElement letter:lettert) {
            if(letter.getText().contains(topic + message + "\n" + addressee)){
                try {
                    wait.until(ExpectedConditions.invisibilityOf(letter));
                }
                catch (TimeoutException e){
                    return false;
                }
                return true;
            }
            index++;
        }
        return true;
    }

}

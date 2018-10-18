package by.epam.at.mailtest.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class DeletedFolderPage extends NavigationTools {

    private final String BASE_URL = "https://e.mail.ru/messages/trash/";

    private final String DELETEDLETTER = "//div[@data-cache-key = \"500002_undefined_false\"]//div[@class=\"b-datalist b-datalist_letters b-datalist_letters_from\"]//div[@data-bem =\"b-datalist__item\"]";
    private final String EMPTYBASKET = "//div[@class = \"b-datalist__empty__block\"]//div[@class = \"js-widget b-folder-promo__item b-folder-promo__default\"]//span[@class = \"b-datalist__empty__text\"]";
    private final String ALERT = "//button[@class = \"btn btn_main btn_stylish js-clearButton\"]";
    private final String SUCCESSDELETING = "//span[@class = \"js-txt _js-title notify-message__title__text notify-message__title__text_ok\"]";

    @FindBy(xpath = DELETEDLETTER)
    private List<WebElement> letters;

    @FindBy(xpath = EMPTYBASKET)
    private WebElement emptyBasket;

    @FindBy(xpath = ALERT)
    private WebElement alert;

    @FindBy(xpath = SUCCESSDELETING)
    private WebElement scssDeleting;

    public DeletedFolderPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

    public boolean checkDeletedFolder(String addressee, String topic, String message) {
        for (WebElement letter : letters) {
            if (letter.getText().contains(topic + message)) {
                return true;
            }
        }
        return false;
    }

    public void confirmationOfDeleting(){
        wait.until(ExpectedConditions.visibilityOf(alert));
        alert.click();
    }

    public boolean checkBasket(){
        if(emptyBasket.isDisplayed()){
            return true;
        }
        return false;
    }
}

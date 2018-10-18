package by.epam.at.mailtest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SendedFolderPage extends NavigationTools {

    private final String BASE_URL = "https://e.mail.ru/messages/sent/";

    private final String SENDEDLETTER = "//div[@data-cache-key = \"500000_undefined_false\"]//div[@class=\"b-datalist b-datalist_letters b-datalist_letters_to\"]//div[@data-bem =\"b-datalist__item\"]";
    private final String LOGOUT = "PH_logoutLink";

    @FindBy(xpath = SENDEDLETTER)
    private List<WebElement> letters;

    @FindBy(id = LOGOUT)
    private WebElement logout;

    int index = 0;

    public SendedFolderPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

    public boolean checkSendedFolder(String addressee, String topic, String message) {
        index = 0;
        for (WebElement letter : letters) {
            if (letter.getText().contains(topic + message + "\n" + addressee)) {
                return true;
            }
            index++;
        }
        return false;
    }

    public void dragDropLetter() {
        Actions dragDrop = new Actions(driver);
        dragDrop.dragAndDrop(letters.get(index), basket).build().perform();
    }

    public void logoutClick() {
        logout.click();
    }
}

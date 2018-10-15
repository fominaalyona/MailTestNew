package by.epam.at.mailtest.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WriteLetterPage extends AbstractPage {

    private final String BASE_URL = "https://e.mail.ru/compose/";

    private final String ADDRESSEE = "//div[@class=\"label-input\"]/div[1]/textarea[2]";
    private final String TOPIC = "//div[@class=\"compose-head__field\"]/input[@class=\"b-input\"]";
    private final String MESSAGE = "tinymce";
    private final String SUBMIT = "//div[@class=\"b-toolbar__group\"]/div[1]/div[1]/span[@class=\"b-toolbar__btn__text\"]";
    private final String SAVE = "//*[@id=\"b-toolbar__right\"]/div[3]/div/div[2]/div[2]/div/div[1]/span";
    private final String SUCCESS = "//div[@class =\"message-sent__title\"]";
    private final String DRAFTS = "//a[@data-mnemo=\"drafts\"]";
    private final String SAVESTATUS = "//div[@data-mnemo=\"saveStatus\"]";
    private final String SENDDRAFT = "//div[@data-name=\"send\"]";

    @FindBy(xpath = DRAFTS)
    private WebElement drafts;

    @FindBy(xpath = ADDRESSEE)
    private WebElement addressee;

    @FindBy(xpath = TOPIC)
    private WebElement topic;

    @FindBy(id = MESSAGE)
    private WebElement inputField;

    @FindBy(xpath = SUBMIT)
    private WebElement buttonSend;

    @FindBy(xpath = SAVE)
    private WebElement saveButton;

    @FindBy(xpath = SUCCESS)
    private WebElement successfullySend;

    @FindBy(xpath = SAVESTATUS)
    private WebElement savedLetter;

    @FindBy(xpath = SENDDRAFT)
    private WebElement sendDraft;

    public WriteLetterPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

    public void sendLetter(String email, String topic, String message) {
        addressee.sendKeys(email);
        this.topic.sendKeys(topic);
        driver.switchTo().frame(0);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].textContent= arguments[1];", inputField, message);
        driver.switchTo().defaultContent();
        saveButton.click();
        wait.until(ExpectedConditions.visibilityOf(savedLetter));
        drafts.click();
    }

    public void sendDraft(){
        //wait.until(ExpectedConditions.elementToBeClickable(sendDraft));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendDraft.click();
    }

}
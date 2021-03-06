package by.epam.at.mailtest.steps;

import by.epam.at.mailtest.driver.DriverSingleton;
import by.epam.at.mailtest.pages.*;
import org.openqa.selenium.WebDriver;

public class Steps {

    private WebDriver driver;

    private MainPage mainPage;
    private IncomeLettersPage incomeLetters;
    private WriteLetterPage writeLetter;
    private DraftFolderPage draftPage;
    private SendedFolderPage sendedPage;
    private DeletedFolderPage deletedPage;

    public void initBrowser() {
        driver = DriverSingleton.getDriver();
    }

    public void initPages() {
        mainPage = new MainPage(driver);
        incomeLetters = new IncomeLettersPage(driver);
        writeLetter = new WriteLetterPage(driver);
        draftPage = new DraftFolderPage(driver);
        sendedPage = new SendedFolderPage(driver);
        deletedPage = new DeletedFolderPage(driver);
    }

    public void closeDriver() {
        DriverSingleton.closeDriver();
    }

    public void loginMailRu(String username, String domain, String password) {
        mainPage.openPage();
        mainPage.logIn(username, domain, password);
    }

    public boolean isLoggedIn(String username, String domain) {
        String actualUsername = mainPage.getLoggedInUserName();
        return actualUsername.equals(username.concat(domain));
    }

    public void saveLetter(String email, String topic, String message) {
        incomeLetters.writeLetterClick();
        writeLetter.writeLetter(email, topic, message);
        writeLetter.saveDraftLetter();
    }

    public void sendLetter(String email, String topic, String message) {
        incomeLetters.writeLetterClick();
        writeLetter.writeLetter(email, topic, message);
        writeLetter.send();
    }

    public void sendLetterWithKeyboard(String email, String topic, String message) {
        incomeLetters.writeWithKeyboard();
        writeLetter.writeLetter(email, topic, message);
        writeLetter.send();
    }

    public boolean checkDrafts(String addressee, String topic, String message) {
        draftPage.openPage();
        return draftPage.checkDraftFolder(addressee, topic, message);
    }

    public boolean sendDraftLetter(String addressee, String topic, String message) {
        draftPage.clickOnDraftLetter();
        writeLetter.send();
        draftPage.openPage();
        return draftPage.checkSendDraft(addressee, topic, message);
    }

    public boolean checkSendedMails(String addressee, String topic, String message) {
        //draftPage.openSendedLetters();
        sendedPage.openPage();
        return sendedPage.checkSendedFolder(addressee, topic, message);
    }

    public void moveToBasket(String addressee, String topic, String message) {
        draftPage.moveDraft(addressee, topic, message);
        //draftPage.openBasket();
    }

    public boolean checkDeletedFolder(String addressee, String topic, String message) {
        deletedPage.openPage();
        return deletedPage.checkDeletedFolder(addressee, topic, message);
    }

    public void dragDropLetter() {
        sendedPage.dragDropLetter();
    }

    public void cleanBasket() {
        deletedPage.cleanBasket();
        deletedPage.confirmationOfDeleting();
    }

    public boolean isBasketEmpty() {
        deletedPage.openBasket();
        return deletedPage.checkBasket();
    }

    public boolean isLogout() {
        sendedPage.logoutClick();
        return mainPage.isLogout();
    }

}

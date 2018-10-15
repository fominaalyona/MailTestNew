package by.epam.at.mailtest.steps;

import by.epam.at.mailtest.driver.DriverSingleton;
import by.epam.at.mailtest.pages.DraftFolderPage;
import by.epam.at.mailtest.pages.IncomeLettersPage;
import by.epam.at.mailtest.pages.MainPage;
import by.epam.at.mailtest.pages.WriteLetterPage;
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

    public void sendLetter(String email, String topic, String message) {
        IncomeLettersPage incomeLetters = new IncomeLettersPage(driver);
        //incomeLetters.openPage();
        incomeLetters.writeLetterClick();
        WriteLetterPage writeLetter = new WriteLetterPage(driver);
        writeLetter.sendLetter(email, topic, message);
    }

    public boolean checkDrafts(String addressee, String topic, String message) {
        DraftFolderPage draftPage = new DraftFolderPage(driver);
        //draftPage.openPage();
        return draftPage.checkDraftFolder(addressee, topic, message);
    }

    public boolean sendDraftLetter(String addressee, String topic, String message){
        DraftFolderPage draftPage = new DraftFolderPage(driver);
        draftPage.clickOnDraftLetter();
        WriteLetterPage writeLetter = new WriteLetterPage(driver);
        writeLetter.sendDraft();
        draftPage.openPage();
        return draftPage.checkSendDraft(addressee, topic, message);
    }



}

package by.epam.at.mailtest;

import by.epam.at.mailtest.steps.Steps;
import by.epam.at.mailtest.util.GenerateData;
import org.testng.Assert;
import org.testng.annotations.*;

public class MailTest {

    private Steps steps;
    private final String LOGIN = "java_test";
    private final String PASSWORD = "javalab2018";
    private final String DOMAIN = "@bk.ru";

    private final String RECEIVER = "java_test@bk.ru";
    private final String TOPIC = GenerateData.createRandomString(8);
    private final String MESSAGE = GenerateData.createRandomString(23);

    @BeforeClass(description = "Init browser")
    public void setUp() {
        steps = new Steps();
        steps.initBrowser();
        steps.initPages();
    }

    @Test(description = "Login to MailRu", priority = 1)
    public void oneCanLoginMailRu() {
        steps.loginMailRu(LOGIN, DOMAIN, PASSWORD);
        Assert.assertTrue(steps.isLoggedIn(LOGIN, DOMAIN));
    }

    @Test(description = "Save Letter", priority = 2)
    public void OneCanSaveLetter() {
        steps.saveLetter(RECEIVER, TOPIC, MESSAGE);
        Assert.assertTrue(steps.checkDrafts(RECEIVER, TOPIC, MESSAGE));
    }

    @Test(description = "Send Draft letter", priority = 3)
    public void OneSendDraftLetter() {
        Assert.assertTrue(steps.sendDraftLetter(RECEIVER, TOPIC, MESSAGE));
    }

    @Test(description = "Sended folder", priority = 4)
    public void OneCheckSendedFolder() {
        Assert.assertTrue(steps.checkSendedMails(RECEIVER, TOPIC, MESSAGE));
    }

    @Test(description = "Logout", priority = 5)
    public void OneCanLogout() {
        Assert.assertTrue(steps.isLogout());
    }

    @AfterClass(description = "Close browser")
    public void closeBrowser() {
        steps = new Steps();
        steps.closeDriver();
    }

}

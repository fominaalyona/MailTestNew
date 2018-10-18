package by.epam.at.mailtest;

import by.epam.at.mailtest.steps.Steps;
import by.epam.at.mailtest.util.GenerateData;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MailTestScenario2 {

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
    public void OneCanLoginMailRu() {
        steps.loginMailRu(LOGIN, DOMAIN, PASSWORD);
        Assert.assertTrue(steps.isLoggedIn(LOGIN, DOMAIN));
    }

    @Test(description = "Save Letter", priority = 2)
    public void OneCanSaveLetter() {
        steps.saveLetter(RECEIVER, TOPIC, MESSAGE);
        Assert.assertTrue(steps.checkDrafts(RECEIVER, TOPIC, MESSAGE));
    }

    @Test(description = "Delete draft", priority = 3)
    public void OneCanDeleteDraft(){
        steps.moveToBasket(RECEIVER, TOPIC, MESSAGE);
        Assert.assertFalse(steps.checkDrafts(RECEIVER, TOPIC, MESSAGE));
        Assert.assertTrue(steps.checkDeletedFolder(RECEIVER, TOPIC, MESSAGE));
    }

    @Test(description = "Clean up the basket", priority = 4)
    public void OneCanCleanBasket(){
        steps.cleanBasket();
        Assert.assertTrue(steps.isBasketEmpty());
    }

    @AfterClass(description = "Close browser")
    public void closeBrowser() {
        steps = new Steps();
        steps.closeDriver();
    }
}

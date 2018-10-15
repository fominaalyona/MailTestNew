package by.epam.at.mailtest;

import by.epam.at.mailtest.steps.Steps;
import org.testng.Assert;
import org.testng.annotations.*;

public class MailTest {

    private Steps steps;
    private final String LOGIN = "java_test";
    private final String PASSWORD = "javalab2018";
    private final String DOMAIN = "@bk.ru";

    private final String RECEIVER = "java_test@bk.ru";
    private final String TOPIC = "Test";
    private final String MESSAGE= "Auto-generated message with Selenium";

    @BeforeClass(description = "Init browser")
    public void setUp() {
        steps = new Steps();
        steps.initBrowser();
    }

    @Test(description = "Login to MailRu", priority = 1)
	public void oneCanLoginMailRu() {
		steps.loginMailRu(LOGIN, DOMAIN, PASSWORD);
		Assert.assertTrue(steps.isLoggedIn(LOGIN, DOMAIN));
	}

    @Test(description = "Send Letter", priority = 2)
    public void OneCanSendLetter()
    {
        steps.sendLetter(RECEIVER, TOPIC, MESSAGE);
    }

    @Test(description = "Check Draft folder", priority = 3)
    public void OneCheckDraftFolder(){
        Assert.assertTrue(steps.checkDrafts(RECEIVER, TOPIC, MESSAGE));
    }

    @Test(description = "Send Draft letter", priority = 4)
    public void OneSendDraftLetter(){
        Assert.assertTrue(steps.sendDraftLetter(RECEIVER, TOPIC, MESSAGE));
    }

	@AfterClass(description = "Close browser")
    public void closeBrowser(){
        steps = new Steps();
        steps.closeDriver();
    }

}

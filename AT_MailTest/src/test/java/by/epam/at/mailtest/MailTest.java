package by.epam.at.mailtest;

import by.epam.at.mailtest.Steps.Steps;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MailTest {

    private Steps steps;
    private final String LOGIN = "java_test";
    private final String PASSWORD = "javalab2018";
    private final String DOMAIN = "@bk.ru";

    @BeforeMethod(description = "Init browser")
    public void setUp() {
        steps = new Steps();
        steps.initBrowser();
    }

    @Test(description = "Login to MailRu")
	public void oneCanLoginMailRu() {
		steps.loginMailRu(LOGIN, DOMAIN, PASSWORD);
		Assert.assertTrue(steps.isLoggedIn(LOGIN, DOMAIN));
	}

	@AfterMethod(description = "Close browser")
    public void closeBrowser(){
        steps = new Steps();
        steps.closeDriver();
    }

}

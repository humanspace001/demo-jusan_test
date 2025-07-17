package org.tsb.demouitest.testPage.iosTestPage;
import org.junit.jupiter.api.Test;
import org.tsb.demouitest.pages.iosPage.CreditsPageiOS;
import org.tsb.demouitest.pages.iosPage.LoginPage;
import org.tsb.demouitest.pages.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoginTest extends BaseTest {
    @Test
    public void testLogin() {
        LoginPage loginPage = new LoginPage(driver);
        CreditsPageiOS creditsPage = new CreditsPageiOS(driver);

        loginPage.login("7066009830", creditsPage);
    }
}

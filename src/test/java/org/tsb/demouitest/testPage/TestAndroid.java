package org.tsb.demouitest.testPage;

import org.junit.jupiter.api.Test;
import org.tsb.demouitest.pages.androidPage.CreditsPageAndroid;
import org.tsb.demouitest.pages.BaseTest;

public class TestAndroid extends BaseTest {
    @Test
    public void testCreditFlow() {

        CreditsPageAndroid creditsPage = new CreditsPageAndroid(driver);

// Открытие раздела "Мой банк"
        creditsPage.clickOnCreditPage();

// Клик 4 раза по кнопке "0"
        creditsPage.clickZeroButtonMultipleTimes(4);

// Открытие страницы кредита
        creditsPage.clickOnCredit();
    }
}

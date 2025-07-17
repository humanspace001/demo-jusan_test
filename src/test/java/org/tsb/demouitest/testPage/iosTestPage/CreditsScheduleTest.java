package org.tsb.demouitest.testPage.iosTestPage;

import org.junit.jupiter.api.Test;
import org.tsb.demouitest.pages.iosPage.CreditsPageiOS;
import org.tsb.demouitest.pages.iosPage.PaymentSchedulePage;
import org.tsb.demouitest.pages.BaseTest;

public class CreditsScheduleTest extends BaseTest {

    @Test
    public void testCreditFlow() {

        CreditsPageiOS creditsPage = new CreditsPageiOS(driver);
        PaymentSchedulePage paymentSchedulePage = new PaymentSchedulePage(driver);

        creditsPage.clickButtonMultipleTimes(4);

        creditsPage.clickOnCreditPage();

        creditsPage.clickOnCredit();

        paymentSchedulePage.clickOnSchedule();

        paymentSchedulePage.clickOnPaymentDate();

        paymentSchedulePage.clickCancelFilledButton();

        paymentSchedulePage.goBack();
    }
}


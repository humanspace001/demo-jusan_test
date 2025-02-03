package org.tsb.demouitest;

import org.junit.jupiter.api.Test;
import org.tsb.demouitest.iOSPhone.CreditsPage;
import org.tsb.demouitest.iOSPhone.PaymentSchedulePage;

public class CreditsScheduleTest extends BaseTest {

    @Test
    public void testCreditFlow() {
        // Инициализация страниц
        CreditsPage creditsPage = new CreditsPage(driver);
        PaymentSchedulePage paymentSchedulePage = new PaymentSchedulePage(driver);

        creditsPage.clickButtonMultipleTimes(4);

        creditsPage.clickOnCreditPage();

        // Нажатие на кредит
        creditsPage.clickOnCredit();

        // Нажатие на график
        paymentSchedulePage.clickOnSchedule();

        // Нажатие на дату платежа
        paymentSchedulePage.clickOnPaymentDate();

        paymentSchedulePage.clickCancelFilledButton();

        // Переход назад
        paymentSchedulePage.goBack();
    }
}


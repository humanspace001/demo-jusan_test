package org.tsb.demouitest;

import org.junit.jupiter.api.Test;
import org.tsb.demouitest.iOSPhone.CreditsPage;
import org.tsb.demouitest.iOSPhone.PaymentSchedulePage;
import org.tsb.demouitest.iOSPhone.UpcomingPaymentPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StatusPaymentCredits extends BaseTest{
    @Test
    public void testUpcomingPaymentElements() {
        CreditsPage creditsPage = new CreditsPage(driver);
        // Переход на страницу Ближайший платеж
        UpcomingPaymentPage upcomingPaymentPage = new UpcomingPaymentPage(driver);

        creditsPage.clickButtonMultipleTimes(4);

        creditsPage.clickOnCreditPage();

        // Нажатие на кредит
        creditsPage.clickOnCredit();
        // Проверить наличие элемента "paymentSection" перед кликом
        assertTrue(upcomingPaymentPage.isElementVisible("paymentSection"), "Секция платежей не отображается!");

        // Кликнуть на "paymentSection"
        upcomingPaymentPage.clickOnPaymentSection();
        // Ассерты на наличие элементов
        assertTrue(upcomingPaymentPage.isHeaderTextVisible(), "Текст 'Сейчас вы можете совершить:' не отображается!");
        assertTrue(upcomingPaymentPage.isScheduledRepaymentTextVisible(), "Текст 'Плановое погашение - оплатить следующий платеж по графику' не отображается!");
        assertTrue(upcomingPaymentPage.isEarlyRepaymentTextVisible(), "Текст 'Досрочое погашение - погасить часть займа досрочно...' не отображается!");
        upcomingPaymentPage.clickOnCloseButton();
    }
}

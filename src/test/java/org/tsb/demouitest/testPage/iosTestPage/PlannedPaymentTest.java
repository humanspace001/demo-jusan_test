package org.tsb.demouitest.testPage.iosTestPage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.tsb.demouitest.pages.iosPage.CreditsPageiOS;
import org.tsb.demouitest.pages.iosPage.PlannedPaymentPage;
import org.tsb.demouitest.pages.BaseTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PlannedPaymentTest extends BaseTest {
    private PlannedPaymentPage plannedPaymentPage;
    private CreditsPageiOS creditsPage ;
    @BeforeEach
    public void setUpTest() {
        plannedPaymentPage = new PlannedPaymentPage(driver);
         creditsPage = new CreditsPageiOS(driver);
    }

    @Test
    public void testPlannedPayment() {
        creditsPage.clickButtonMultipleTimes(4);

        creditsPage.clickOnCreditPage();
        //  Клик по "Плановое погашение"
        plannedPaymentPage.clickPlannedRepayment();

        //  Включаем свитч, если он выключен
        plannedPaymentPage.enableSwitchIfOff();

        //  Клик по кнопке "Оплатить"
        plannedPaymentPage.clickPayButton();

        //  Клик по "Подтвердить и оплатить"
        plannedPaymentPage.confirmPayment();

        // Проверяем успешность операции
        assertThat(plannedPaymentPage.isElementVisible(By.id("payButton"))).isFalse();
    }
}

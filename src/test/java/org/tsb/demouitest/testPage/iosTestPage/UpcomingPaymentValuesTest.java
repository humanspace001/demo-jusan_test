package org.tsb.demouitest.testPage.iosTestPage;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tsb.demouitest.apiData.CreditDataManager;
import org.tsb.demouitest.apiData.JsonUtils;
import org.tsb.demouitest.pages.BaseTest;
import org.tsb.demouitest.pages.iosPage.CreditsPageiOS;
import org.tsb.demouitest.pages.iosPage.UpcomingPaymentPage;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UpcomingPaymentValuesTest extends BaseTest {
    String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhZG1pbiIsImF1ZCI6WyJrcmVuZGVsIl0sInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdLCJleHAiOjE3NTMzNDg4ODksImF1dGhvcml0aWVzIjpbImtyZW5kZWw6ZmluZDpjbGllbnQ6cHJvZHVjdHMiLCJrcmVuZGVsOmhhczpqdXNhbnBheSIsImtyZW5kZWw6ZmluZDpqc2Y6cHJvZHVjdHMiLCJrcmVuZGVsOmZpbmQ6bXk6YmFuazpwcm9kdWN0cyIsImtyZW5kZWw6ZmluZDpjYXJkOmRldGFpbHMiXSwianRpIjoiY0hmNjhsUlo0Z2t2MUQxem9pZ3ZQbERYSUU4IiwiY2xpZW50X2lkIjoiYWRtaW4ifQ.pX2EAla64_sFbeQ5_UFPT-Iy_8CVVu4zBcFwpIBe3uUS3bxTz4QDonnWrbL2Xw02-0NGXstSPy8dL4O_QOwYNleQiEoKvkE7HoNtUVQEhUU_gpIqoyc-fxC15wkOpRBQldGij1f-shkMwnl7hYViWsOhEgA3-ZOT-7oC5w7RW7f0GEi1oFahQk0jCj-UW_oI-uzHgxBQyeTAlo97a4E5Ld2JPMejh8aQIB_ZpPvHmDiA4q04Wol2WSw7npDm_B81-61NX6mOBemWHGs__Hw_ZiedUsJbe3YZIaBlkGRDCkN0hw6luuycZuoUjaOEPDDART-WfERB1MQUb880DLaKMA";
    private UpcomingPaymentPage upcomingPaymentPage;
    CreditsPageiOS creditsPage;
    JSONObject creditData;
    @BeforeEach
    public void setUpTest() {
        creditsPage = new CreditsPageiOS(driver);
        upcomingPaymentPage = new UpcomingPaymentPage(driver);
//        creditData = CreditDataManager.getCreditInfo();
        CreditDataManager.setDynamicHeaders("811208300191", "77772362313", token);
        creditData = CreditDataManager.updateCache();
    }

    private void navigateToUpcomingPayment() {
        upcomingPaymentPage = new UpcomingPaymentPage(driver);

        creditsPage.clickButtonMultipleTimes(4);
        creditsPage.clickOnCreditPage();
        creditsPage.clickOnCredit();
        upcomingPaymentPage.clickOnPaymentSection();
    }


    @Test
    public void testMonthlyPaymentValue() {
        navigateToUpcomingPayment();
        BigDecimal expected = JsonUtils.getJsonValue(creditData, "next_payment_amount", BigDecimal.class);

        BigDecimal actual = upcomingPaymentPage.getNumericValue("monthlyPaymentValue")
                .orElseThrow(() -> new AssertionError("\u274c \u0417\u043d\u0430\u0447\u0435\u043d\u0438\u0435 'monthlyPaymentValue' \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u043e!"));

        assertThat(actual)
                .as("\u274c \u0417\u043d\u0430\u0447\u0435\u043d\u0438\u0435 \u0434\u043b\u044f 'monthlyPaymentValue' \u043d\u0435 \u0441\u043e\u0432\u043f\u0430\u0434\u0430\u0435\u0442!")
                .isEqualTo(expected);
    }

    @Test
    public void testPaidValue() {
        BigDecimal expected = JsonUtils.getJsonValue(creditData, "paid_amount", BigDecimal.class);

        BigDecimal actual = upcomingPaymentPage.getNumericValue("paidValue")
                .orElseThrow(() -> new AssertionError("\u274c \u0417\u043d\u0430\u0447\u0435\u043d\u0438\u0435 'paidValue' \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u043e!"));

        assertThat(actual)
                .as("\u274c \u0417\u043d\u0430\u0447\u0435\u043d\u0438\u0435 \u0434\u043b\u044f 'paidValue' \u043d\u0435 \u0441\u043e\u0432\u043f\u0430\u0434\u0430\u0435\u0442!")
                .isEqualTo(expected);
    }
}

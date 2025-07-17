package org.tsb.demouitest.testPage.iosTestPage;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tsb.demouitest.apiData.JsonUtils;
import org.tsb.demouitest.apiData.CreditDataManager;
import org.tsb.demouitest.pages.iosPage.CreditsPageiOS;

import org.tsb.demouitest.pages.iosPage.UpcomingPaymentPage;
import org.tsb.demouitest.pages.BaseTest;

import java.math.BigDecimal;

import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class StatusPaymentCredits extends BaseTest {
    private CreditsPageiOS creditsPage;
    private UpcomingPaymentPage upcomingPaymentPage;
    private JSONObject creditData;
    private String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhZG1pbiIsImF1ZCI6WyJrcmVuZGVsIl0sInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdLCJleHAiOjE3NTMyNTE0NzYsImF1dGhvcml0aWVzIjpbImtyZW5kZWw6ZmluZDpjbGllbnQ6cHJvZHVjdHMiLCJrcmVuZGVsOmhhczpqdXNhbnBheSIsImtyZW5kZWw6ZmluZDpqc2Y6cHJvZHVjdHMiLCJrcmVuZGVsOmZpbmQ6bXk6YmFuazpwcm9kdWN0cyIsImtyZW5kZWw6ZmluZDpjYXJkOmRldGFpbHMiXSwianRpIjoiTy1VR3Q0eEZfM3c0czB4a3hjSVdmbHNWdjdZIiwiY2xpZW50X2lkIjoiYWRtaW4ifQ.KE7459HQKBUL-v-sOEoXXaLfcVaL2U39VKt033IIKA3VHHErBtCWCCsGDdzYxF6vgxXil4GfZr33jMSnPciQxGNMJAA3qUhJCs5hSILdPC2i5HsUWr6ZdNYUzs03ZmqxDzMv4S-3fZsFsVhq6mwNF9_iGCsQPPq8q5LCl7Ar9zMhnIW0UHH9wgvv4UAHghlE-IWLUxGwg4BYuU5OnX_qDapa6TC5ra_DN8pr0Mh5P6157CL4WsW41rrenOeMISq8DTGDIe9g6lrYHMaHhxvqtRoSTLOk8poST95bhFRAPoc67moM8s2FVGPVt31EGkcXyuiRXdRcdzb6iGq-1OadeA";
    @BeforeEach
    public void setUpTest() {
        creditsPage = new CreditsPageiOS(driver);
        upcomingPaymentPage = new UpcomingPaymentPage(driver);
//        CreditDataManager.setDynamicHeaders("811208300191", "77772362313", token);
//        creditData = CreditDataManager.updateCache();
    }

    private void navigateToUpcomingPayment() {
        creditsPage.clickButtonMultipleTimes(4);
        creditsPage.clickOnCreditPage();
        creditsPage.clickOnCredit();
        upcomingPaymentPage.clickOnPaymentSection();
    }

    @Test
    public void testPaymentOverdue() {
        navigateToUpcomingPayment();

        JSONObject creditData = CreditDataManager.getCreditInfo();

        upcomingPaymentPage.monthlyPaymentValueMatchesApi(creditData, upcomingPaymentPage);


    }
}

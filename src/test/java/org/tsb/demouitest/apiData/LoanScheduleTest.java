package org.tsb.demouitest.apiData;

import org.json.JSONObject;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class LoanScheduleTest {
    @ParameterizedTest(name = "🧪 [{index}] Тест loan schedule → ID={1}")
    @MethodSource("org.tsb.demouitest.apiData.LoanScheduleDataLoader#provideLoanScheduleData")
    void testLoanSchedule(String depId, String id, String dateFrom, String dateTo) {
        JSONObject result = CreditDataManager.fetchLoanSchedule(depId, id, dateFrom, dateTo);
        System.out.println("✅ Ответ: " + result.toString(2));
    }
}

package org.tsb.demouitest.apiData;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.params.provider.Arguments;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.stream.Stream; // ✅ Stream

public class LoanScheduleDataLoader {

    public static Stream<Arguments> provideLoanScheduleData() {
        try {
            String json = Files.readString(Paths.get("src/test/resources/testdata/loan_schedule_input.json"));
            JSONArray array = new JSONArray(json);

            return array.toList().stream().map(obj -> {
                JSONObject jsonObj = new JSONObject((java.util.Map<?, ?>) obj);
                return Arguments.of(
                        jsonObj.getString("depId"),
                        jsonObj.getString("id"),
                        jsonObj.getString("dateFrom"),
                        jsonObj.getString("dateTo")
                );
            });
        } catch (Exception e) {
            throw new RuntimeException("❌ Ошибка чтения loan_schedule_input.json", e);
        }
    }
}

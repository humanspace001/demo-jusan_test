package org.tsb.demouitest.apiData;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreditDataManager {
    private static final String CACHE_FILE = "cacheTest.json";
    private static final String TIMESTAMP_FILE = "cache_timestamp.txt";
    private static final long CACHE_EXPIRATION_HOURS = 24; // Кэш актуален 24 часа

    private static final Map<String, String> HEADERS = new HashMap<>();

    /**
     * ✅ Устанавливаем заголовки вручную (токен передаётся в аргументе)
     */
    public static void setDynamicHeaders(String iin, String phone, String accessToken) {
        HEADERS.clear(); // Очищаем перед установкой новых данных
        HEADERS.put("accept", "application/json");
        HEADERS.put("jsn-account-type", "JUSAN");
        HEADERS.put("jsn-iin", iin);
        HEADERS.put("jsn-phone", phone);
        HEADERS.put("jsn-roles", "ticketon,cash_by_code");
        HEADERS.put("jsn-sid", "msid_123134782_jhasgfdjhaf");
        HEADERS.put("jsn-app", "jysan");
        HEADERS.put("jsn-app-version", "4");
        HEADERS.put("jsn-app-channel", "ios");
        HEADERS.put("jsn-language", "RU");
        HEADERS.put("jsn-theme", "dark");
        HEADERS.put("jsn-client-type", "JUSAN");
        HEADERS.put("jsn-avatar", "https://mobile.jusan.kz/avatar.png");
        HEADERS.put("Authorization", "Bearer " + accessToken);

        System.out.println("✅ Установлены динамические заголовки для IIN: " + iin + ", phone: " + phone);
    }

    public static JSONObject getCreditInfo() {
        if (isCacheExpired()) {
            return updateCache();
        }

        JSONObject cachedResponse = FileCache.loadResponse();
        if (cachedResponse != null) {
            System.out.println("📂 Используем кешированные данные из `cacheTest.json`" + cachedResponse);
            return cachedResponse;
        }

        return updateCache();
    }


    public static JSONObject updateCache() {
        System.out.println("🔄 Запрашиваем API для обновления кеша...");
        Response response = ApiClient.get("/krendel/v1/my/bank/products/credits", HEADERS);
        JSONArray jsonArray = new JSONArray(response.asString());
        JSONObject jsonObject = jsonArray.getJSONObject(0);

        FileCache.saveResponse("credit_info", jsonObject);
        saveTimestamp();

        System.out.println("✅ Кеш успешно обновлен!" + jsonObject);
        return jsonObject;
    }


    private static boolean isCacheExpired() {
        try {
            File timestampFile = new File(TIMESTAMP_FILE);
            if (!timestampFile.exists()) {
                return true; // Если файла с датой нет – кеш считается устаревшим
            }

            long lastUpdate = Long.parseLong(Files.readString(Paths.get(TIMESTAMP_FILE)).trim());
            long currentTime = Instant.now().getEpochSecond();
            long elapsedHours = (currentTime - lastUpdate) / 3600;

            return elapsedHours >= CACHE_EXPIRATION_HOURS;
        } catch (IOException | NumberFormatException e) {
            System.err.println("⚠️ Ошибка проверки кеша: " + e.getMessage());
            return true; // Если ошибка, принудительно обновляем кэш
        }
    }

    private static void saveTimestamp() {
        try {
            Files.writeString(Paths.get(TIMESTAMP_FILE), String.valueOf(Instant.now().getEpochSecond()));
            System.out.println("📌 Кеш обновлен, время сохранено!");
        } catch (IOException e) {
            System.err.println("⚠️ Ошибка сохранения времени кеша: " + e.getMessage());
        }
    }

    public static void processCreditInfo(JSONObject creditData) {
        try {
            BigDecimal nextPaymentAmount = creditData.getBigDecimal("next_payment_amount");
            System.out.println("🟢 Сумма следующего платежа: " + nextPaymentAmount);

            String nextPaymentAmountStr = nextPaymentAmount.toPlainString();
            System.out.println("🟢 next_payment_amount (как строка): " + nextPaymentAmountStr);

        } catch (Exception e) {
            System.err.println("❌ Ошибка обработки данных: " + e.getMessage());
        }
    }
    public static JSONObject fetchLoanSchedule(String depId, String id, String from, String to) {
        JSONObject body = new JSONObject()
                .put("depId", depId)
                .put("id", id)
                .put("dateFrom", from)
                .put("dateTo", to);

        Response response = given()
                .headers(HEADERS)
                .contentType("application/json")
                .body(body.toString())
                .when()
                .post("/copart/api/v1/loan/schedule")
                .then().statusCode(200)
                .extract().response();

        return new JSONObject(response.asString());
    }
}

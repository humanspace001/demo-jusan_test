package org.tsb.demouitest.apiData;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class ApiClient {
    static {
        RestAssured.baseURI = "https://test-apps-service.tsb.kz"; // Базовый URL API
    }

    public static Response get(String endpoint, Map<String, String> headers) {
        return given()
                .headers(headers)
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }
}

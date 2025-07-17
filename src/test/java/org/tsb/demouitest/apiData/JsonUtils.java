package org.tsb.demouitest.apiData;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.tsb.demouitest.apiData.model.CreditInfo;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class JsonUtils {
    private static final String FILE_PATH = "cacheTest.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void saveToJson(List<CreditInfo> credits) throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), credits);
    }

    public static List<CreditInfo> fromJsonFile() throws IOException {
        return objectMapper.readValue(new File(FILE_PATH), new TypeReference<List<CreditInfo>>() {});
    }
    public static <T> T getJsonValue(JSONObject json, String key, Class<T> type) {
        if (!json.has(key)) {
            throw new IllegalArgumentException("❌ Ключ не найден: " + key);
        }

        try {
            Object value = json.get(key);

            if (type.isInstance(value)) {
                return type.cast(value);
            }

            if (type == String.class) {
                return type.cast(value.toString());
            } else if (type == BigDecimal.class) {
                return type.cast(new BigDecimal(value.toString()));
            } else if (type == Integer.class) {
                return type.cast(Integer.parseInt(value.toString()));
            } else if (type == Double.class) {
                return type.cast(Double.parseDouble(value.toString()));
            }

        } catch (Exception e) {
            throw new RuntimeException("❌ Ошибка при обработке ключа: " + key + " -> " + e.getMessage());
        }

        throw new RuntimeException("❌ Неподдерживаемый тип: " + type.getSimpleName());
    }
}


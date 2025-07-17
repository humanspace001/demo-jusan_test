package org.tsb.demouitest.apiData;

import org.json.JSONObject;
import java.io.*;

public class FileCache {
    private static final String FILE_PATH = "cacheTest.json";

    public static void saveResponse(String key, JSONObject data) {
        try (FileWriter file = new FileWriter(FILE_PATH)) {
            file.write(data.toString(4)); // Сохраняем JSON с отступами
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject loadResponse() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            return new JSONObject(json.toString());
        } catch (IOException e) {
            System.out.println("⚠️ Файл кеша не найден, тесты будут провалены.");
            return null;
        }
    }
}

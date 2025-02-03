package org.tsb.demouitest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Capabilities;
import org.tsb.demouitest.iOSPhone.BaseOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class BaseTest  {
    protected AppiumDriver driver;

    @BeforeEach
    public void setUp() {
        var options = new BaseOptions().build();
        driver = new IOSDriver(getUrl(), options);
        resetApp(); // Сбрасываем состояние приложения
    }

    private URL getUrl() {
        try {
            return new URL("http://127.0.0.1:4723");
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Appium server URL", e);
        }
    }

    private void resetApp() {
        if (driver != null) {
            // Завершение приложения
            HashMap<String, String> terminateArgs = new HashMap<>();
            terminateArgs.put("bundleId", "kz.tsb.app24"); // Укажите ваш bundleId
            driver.executeScript("mobile: terminateApp", terminateArgs);

            // Запуск приложения
            HashMap<String, String> launchArgs = new HashMap<>();
            launchArgs.put("bundleId", "kz.tsb.app24"); // Укажите ваш bundleId
            driver.executeScript("mobile: launchApp", launchArgs);
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Завершение сессии Appium
        }
    }
}

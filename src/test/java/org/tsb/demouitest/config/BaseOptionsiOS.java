package org.tsb.demouitest.config;

import org.openqa.selenium.MutableCapabilities;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class BaseOptionsiOS {

    private static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723/wd/hub";

    public MutableCapabilities build() {
        MutableCapabilities options = new MutableCapabilities();
        options.setCapability("platformName", "iOS");
        options.setCapability("appium:deviceName", "iPhone 16 Pro Max");
        options.setCapability("appium:automationName", "XCUITest");
        options.setCapability("appium:platformVersion", "18.3");
        options.setCapability("appium:bundleId", "kz.tsb.app24");
        options.setCapability("appium:noReset", true);
        options.setCapability("appium:includeSafariInWebviews", true);
        options.setCapability("appium:newCommandTimeout", 3600);
        options.setCapability("appium:connectHardwareKeyboard", true);
        return options;
    }

    public boolean isAppiumRunning() {
        try {
            URL url = new URL(APPIUM_SERVER_URL + "/status");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(2000);
            connection.connect();

            return connection.getResponseCode() == 200;
        } catch (IOException e) {
            return false;
        }
    }

    public void startAppiumServer() {
        if (isAppiumRunning()) {
            System.out.println("✅ Appium Server уже запущен, пропускаем старт.");
            return;
        }

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "appium", "--port", "4723", "--base-path", "/wd/hub");
            processBuilder.start();
            System.out.println("🚀 Appium Server запущен на порту 4723");
            Thread.sleep(5000); // Даем серверу время на запуск
        } catch (IOException | InterruptedException e) {
            System.err.println("❌ Ошибка при запуске Appium: " + e.getMessage());
        }
    }
}
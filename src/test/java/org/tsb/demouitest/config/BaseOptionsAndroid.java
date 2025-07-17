package org.tsb.demouitest.config;

import org.openqa.selenium.MutableCapabilities;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class BaseOptionsAndroid {
    private static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723";
    private static final String APP_PATH = "src/test/resources/app-debug.apk";

    public MutableCapabilities build() {
        MutableCapabilities options = new MutableCapabilities();
        options.setCapability("platformName", "Android");
        options.setCapability("appium:deviceName", "emulator-5554");
        options.setCapability("appium:automationName", "UiAutomator2");
        options.setCapability("appium:app", APP_PATH);
        options.setCapability("appium:appPackage", "kz.tsb.app24.debug");
        options.setCapability("appium:appActivity", "kz.tsb.app24.view.SplashActivity");
        options.setCapability("appium:noReset", true);
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
            System.out.println("✅ Appium Server уже запущен.");
            return;
        }
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("appium", "--port", "4723", "--base-path", "/wd/hub");
            processBuilder.start();
            System.out.println("🚀 Appium Server запущен.");
            Thread.sleep(5000);
        } catch (IOException | InterruptedException e) {
            System.err.println("❌ Ошибка запуска Appium: " + e.getMessage());
        }
    }
}


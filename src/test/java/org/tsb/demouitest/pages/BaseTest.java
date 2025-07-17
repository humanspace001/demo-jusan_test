package org.tsb.demouitest.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;

import org.junit.jupiter.api.*;
import org.tsb.demouitest.config.BaseOptionsiOS;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {
    protected static AppiumDriver driver;

    @BeforeAll
    public static void globalSetUp() {
        if (driver == null) {
            BaseOptionsiOS baseOptions = new BaseOptionsiOS();
            baseOptions.startAppiumServer();
//            BaseOptionsAndroid baseOptions = new BaseOptionsAndroid();
//            baseOptions.startAppiumServer();

            var options = baseOptions.build();
            driver = new IOSDriver(getUrl(), options);
            resetApp();
        }
    }

    @BeforeEach
    public void setUp() {
        System.out.println("✅ Используется активная сессия Appium: " + driver.getSessionId());
    }

    @AfterAll
    public static void globalTearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
            System.out.println("❌ Сессия Appium завершена.");
        }
    }

    private static URL getUrl() {
        try {
            return new URL("http://127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException("❌ Invalid Appium server URL", e);
        }
    }

    private static void resetApp() {
        if (driver != null) {
            System.out.println("🔄 Перезапуск приложения...");
            HashMap<String, String> terminateArgs = new HashMap<>();
            terminateArgs.put("bundleId", "kz.tsb.app24");
            driver.executeScript("mobile: terminateApp", terminateArgs);

            HashMap<String, String> launchArgs = new HashMap<>();
            launchArgs.put("bundleId", "kz.tsb.app24");
            driver.executeScript("mobile: launchApp", launchArgs);
        }
    }
}

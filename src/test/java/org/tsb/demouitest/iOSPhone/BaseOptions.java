package org.tsb.demouitest.iOSPhone;

import org.openqa.selenium.MutableCapabilities;

import java.util.HashMap;
import java.util.Map;

public class BaseOptions {
    public MutableCapabilities build() {
        MutableCapabilities options = new MutableCapabilities();
        options.setCapability("platformName", "iOS");
        options.setCapability("appium:deviceName", "iPhone 16 Pro Max");
        options.setCapability("appium:automationName", "XCUITest");
        options.setCapability("appium:platformVersion", "18.2");
        options.setCapability("appium:bundleId", "kz.tsb.app24");
        options.setCapability("appium:noReset", true);
        options.setCapability("appium:includeSafariInWebviews", true);
        options.setCapability("appium:newCommandTimeout", 3600);
        options.setCapability("appium:connectHardwareKeyboard", true);
        return options;
    }
}
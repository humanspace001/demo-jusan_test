package org.tsb.demouitest;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class BasePage {
    protected AppiumDriver driver;

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }
}


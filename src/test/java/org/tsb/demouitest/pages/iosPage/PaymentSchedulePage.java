package org.tsb.demouitest.pages.iosPage;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.tsb.demouitest.BasePage;

import java.util.Map;

public class PaymentSchedulePage extends BasePage {

    // Локаторы
    private final Map<String, By> locators = Map.of(
            "scheduleButton", AppiumBy.xpath("//XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]"),
            "paymentDate", AppiumBy.xpath("//XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell[1]/XCUIElementTypeOther"),
            "backButton", AppiumBy.xpath("//XCUIElementTypeButton[@name=\"chevron left\"]"),
            "cancelFilledButton", AppiumBy.xpath("//XCUIElementTypeButton[@name=\"cancel filled\"]")
    );

    public PaymentSchedulePage(AppiumDriver driver) {
        super(driver);
    }


    public void clickOnSchedule() {
        click(locators.get("scheduleButton"));
    }

    public void clickOnPaymentDate() {
        click(locators.get("paymentDate"));
    }

    public void goBack() {
        click(locators.get("backButton"));
    }

    public void clickCancelFilledButton() {
        click(locators.get("cancelFilledButton"));
    }
}

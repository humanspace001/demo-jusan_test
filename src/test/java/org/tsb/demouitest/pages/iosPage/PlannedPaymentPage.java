package org.tsb.demouitest.pages.iosPage;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.tsb.demouitest.BasePage;

import java.util.Map;

public class PlannedPaymentPage extends BasePage {
    private final Map<String, By> locators = Map.of(
            "plannedRepaymentButton", AppiumBy.xpath("(//XCUIElementTypeButton[@name='Плановое погашение'])[2]"),
            "toggleSwitch", AppiumBy.xpath("//XCUIElementTypeSwitch[@value='0']"),
            "payButton", AppiumBy.xpath("//XCUIElementTypeButton[@name='Оплатить 16 470.30₸']"),
            "confirmPaymentButton", AppiumBy.xpath("//XCUIElementTypeButton[@name='Подтвердить и оплатить']")
    );

    public PlannedPaymentPage(AppiumDriver driver) {
        super(driver);
    }

    public void clickPlannedRepayment() {
        clickSafely(locators.get("plannedRepaymentButton"));
    }

    public void enableSwitchIfOff() {
        if (isElementVisible(locators.get("toggleSwitch"))) {
            clickSafely(locators.get("toggleSwitch"));
        }
    }

    public void clickPayButton() {
        clickSafely(locators.get("payButton"));
    }

    public void confirmPayment() {
        clickSafely(locators.get("confirmPaymentButton"));
    }
}

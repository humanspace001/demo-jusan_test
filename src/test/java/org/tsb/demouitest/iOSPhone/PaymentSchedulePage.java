package org.tsb.demouitest.iOSPhone;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

    // Конструктор
    public PaymentSchedulePage(AppiumDriver driver) {
        super(driver);
    }

    // Метод для выполнения действия (универсальный)
    private void click(String elementKey) {
        click(locators.get(elementKey));
    }

    // Методы для взаимодействия
    public void clickOnSchedule() {
        click("scheduleButton");
    }

    public void clickOnPaymentDate() {
        click("paymentDate");
    }

    public void goBack() {
        click("backButton");
    }

    public void clickCancelFilledButton() {
        click("cancelFilledButton");
    }
}

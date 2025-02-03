package org.tsb.demouitest.iOSPhone;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.tsb.demouitest.BasePage;

import java.util.Map;

public class UpcomingPaymentPage extends BasePage {
    // Локаторы
    private final Map<String, By> locators = Map.of(
            "headerText", AppiumBy.xpath("//XCUIElementTypeStaticText[@name='Сейчас вы можете совершить:']"),
            "scheduledRepaymentText", AppiumBy.xpath("//XCUIElementTypeStaticText[@name='Плановое погашение - оплатить следующий платеж по графику']"),
            "earlyRepaymentText", AppiumBy.xpath("//XCUIElementTypeStaticText[@name='Досрочое погашение - погасить часть займа досрочно, чтобы уменьшить ежемесячный платеж, либо погасить займ полностью']"),
            "paymentSection", AppiumBy.xpath("//XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[6]"),
            "closeButton", AppiumBy.xpath("//XCUIElementTypeButton[@name='Закрыть']")
    );

    // Конструктор
    public UpcomingPaymentPage(AppiumDriver driver) {
        super(driver);
    }

    // Универсальный метод для проверки наличия элемента
    public boolean isElementVisible(String elementKey) {
        try {
            return driver.findElement(locators.get(elementKey)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Универсальный метод для получения текста элемента
    public String getText(String elementKey) {
        WebElement element = driver.findElement(locators.get(elementKey));
        return element.getText();
    }

    // Универсальный метод для клика по элементу
    public void click(String elementKey) {
        WebElement element = driver.findElement(locators.get(elementKey));
        element.click();
    }

    // Методы для взаимодействия
    public void clickOnPaymentSection() {
        click("paymentSection");
    }

    public boolean isHeaderTextVisible() {
        return isElementVisible("headerText");
    }

    public boolean isScheduledRepaymentTextVisible() {
        return isElementVisible("scheduledRepaymentText");
    }

    public boolean isEarlyRepaymentTextVisible() {
        return isElementVisible("earlyRepaymentText");
    }
    public void clickOnCloseButton() {
        click("closeButton");
    }
}

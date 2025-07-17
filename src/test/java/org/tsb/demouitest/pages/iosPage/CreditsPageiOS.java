package org.tsb.demouitest.pages.iosPage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.tsb.demouitest.BasePage;

import java.util.Map;

public class CreditsPageiOS extends BasePage {

    private final Map<String, By> locators = Map.of(
            "creditCell", AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"TW24200-000376244/500\"]"),
            "myBankButton", AppiumBy.xpath("//XCUIElementTypeButton[@name='Мой банк']"),
            "zeroButton", AppiumBy.xpath("//XCUIElementTypeButton[@name='0']")
    );

    public CreditsPageiOS(AppiumDriver driver) {
        super(driver);
    }

    // Клик по кнопке "Мой банк" с проверкой доступности
    public void clickOnCreditPage() {
        clickSafely(locators.get("myBankButton"));
    }

    // Клик по кредиту с проверкой
    public void clickOnCredit() {
        waitForElementToBeVisible(locators.get("creditCell"));

        if (!isElementVisible(locators.get("creditCell"))) {
            scrollToElement(locators.get("creditCell"));
        }

        clickSafely(locators.get("creditCell"));
      //  scrollToElementAndClick(locators.get("creditCell"));
//        clickSafely(locators.get("creditCell"));
    }

    // Клик по кнопке несколько раз
    public void clickButtonMultipleTimes(int times) {
        By buttonLocator = locators.get("zeroButton");
        if (isElementVisible(buttonLocator)) {
            WebElement button = waitForElementToBeClickable(buttonLocator);
            for (int i = 0; i < times; i++) {
                button.click();
            }
        } else {
            System.out.println("Кнопка '0' не найдена на экране.");
        }
    }

    // Автоскролл перед кликом (если элемент не виден)
    public void scrollToElementAndClick(By locator) {
        if (!isElementVisible(locator)) {
            scrollToElement(locator);
        }
        clickSafely(locator);
    }

}

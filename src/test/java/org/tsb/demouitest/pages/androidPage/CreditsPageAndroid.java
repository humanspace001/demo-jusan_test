package org.tsb.demouitest.pages.androidPage;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.tsb.demouitest.BasePage;

import java.util.Map;

public class CreditsPageAndroid extends BasePage {

    private final Map<String, By> locators = Map.of(
            "myBankButton", AppiumBy.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View[2]"),
            "zeroButton", AppiumBy.xpath("//android.widget.TextView[@text='0']"),
            "creditCell", AppiumBy.xpath("//android.widget.ScrollView/android.view.View[3]/android.view.View[3]")
    );

    public CreditsPageAndroid(AppiumDriver driver) {
        super(driver);
    }

    // Переход в раздел "Мой банк"
    public void clickOnCreditPage() {
        clickSafely(locators.get("myBankButton"));
    }

    // Клик по кредиту
    public void clickOnCredit() {
        waitForElementToBeVisible(locators.get("creditCell"));

        if (!isElementVisible(locators.get("creditCell"))) {
            scrollToElement(locators.get("creditCell"));
        }

        clickSafely(locators.get("creditCell"));
    }

    // Клик по кнопке "0" четыре раза при авторизации
    public void clickZeroButtonMultipleTimes(int times) {
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

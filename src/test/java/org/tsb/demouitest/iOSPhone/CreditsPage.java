package org.tsb.demouitest.iOSPhone;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tsb.demouitest.BasePage;

import java.time.Duration;

public class CreditsPage extends BasePage {
    private final By creditCellLocator = AppiumBy.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[3]");
    private final By myBankXpath = AppiumBy.xpath("//XCUIElementTypeButton[@name='Мой банк']");
    private final By buttonLocator = AppiumBy.xpath("//XCUIElementTypeButton[@name='0']");

    public CreditsPage(AppiumDriver driver) {
        super(driver);
    }

    // Метод для нажатия на "Мой банк" с ожиданием
    public void clickOnCreditPage() {
        waitForElementToBeClickable(myBankXpath).click();
    }

    // Метод для нажатия на кредит с ожиданием
    public void clickOnCredit() {
        waitForElementToBeClickable(creditCellLocator).click();
    }

    // Метод для многократного нажатия на кнопку
    public void clickButtonMultipleTimes(int times) {
        WebElement button = waitForElementToBeClickable(buttonLocator);
        for (int i = 0; i < times; i++) {
            button.click();
        }
    }

    // Метод ожидания элемента, пока он не станет кликабельным
    private WebElement waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Устанавливаем таймаут 10 секунд
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}

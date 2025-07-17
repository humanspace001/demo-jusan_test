package org.tsb.demouitest.pages.iosPage;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.tsb.demouitest.BasePage;

import java.util.Map;

public class LoginPage extends BasePage {

    private final Map<String, By> locators = Map.of(
            "profileIcon", AppiumBy.xpath("//XCUIElementTypeImage[@name='ic_profile']"),
            "phoneField", AppiumBy.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeOther[2]/XCUIElementTypeImage[3]"),
            "clickToEnterPhone", AppiumBy.xpath("//XCUIElementTypeTextView[@value='+7']"),
            "phoneInputField", AppiumBy.xpath("//XCUIElementTypeTextView[@value='+7 (']"),
            "continueButton", AppiumBy.xpath("//XCUIElementTypeButton[@name='Продолжить']"),
            "pinCodeField", AppiumBy.xpath("//XCUIElementTypeApplication[@name='Jusan']/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeStaticText[1]"),
            "pinCodeArea", AppiumBy.xpath("//XCUIElementTypeApplication[@name='Jusan']/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]")
    );

    public LoginPage(AppiumDriver driver) {
        super(driver);
    }

    // Клик на иконку профиля
    public void clickOnProfileIcon() {
        clickSafely(locators.get("profileIcon"));
    }

    // Открытие поля ввода телефона
    public void clickToEnterPhone() {
        clickSafely(locators.get("clickToEnterPhone"));
    }

    // Ввод номера телефона
    public void enterPhoneNumber(String phoneNumber) {
        WebElement inputField = waitForElementToBeVisible(locators.get("phoneInputField"));
        inputField.sendKeys(phoneNumber);
    }

    // Нажатие на кнопку "Продолжить"
    public void clickContinue() {
        clickSafely(locators.get("continueButton"));
    }

    // Ввод PIN-кода (4 раза нажать "0")
    public void enterPinCode(CreditsPageiOS creditsPage) {
        creditsPage.clickButtonMultipleTimes(4);
    }

    // Общий метод для авторизации
    public void login(String phoneNumber, CreditsPageiOS creditsPage) {
        clickOnProfileIcon();
        clickToEnterPhone();
        enterPhoneNumber(phoneNumber);
        clickContinue();
        enterPinCode(creditsPage);
    }
}

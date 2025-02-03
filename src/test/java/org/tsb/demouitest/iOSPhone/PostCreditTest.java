package org.tsb.demouitest.iOSPhone;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class PostCreditTest {
    private final AppiumDriver driver;

    // Локаторы для iOS
    private final String usernameFieldXPath = "//XCUIElementTypeTextField[@name='username']";
    private final String passwordFieldXPath = "//XCUIElementTypeSecureTextField[@name='password']";
    private final String loginButtonXPath = "//XCUIElementTypeButton[@name='login']";
    private final String errorMessageXPath = "//XCUIElementTypeStaticText[@name='errorMessage']";

    // Конструктор
    public PostCreditTest(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Методы для взаимодействия с элементами
    public void enterUsername(String username) {
        WebElement usernameField = driver.findElement(AppiumBy.xpath(usernameFieldXPath));
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement passwordField = driver.findElement(AppiumBy.xpath(passwordFieldXPath));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(AppiumBy.xpath(loginButtonXPath)).click();
    }

    public String getErrorMessage() {
        return new WebDriverWait(driver, Duration.ofSeconds(11))
                .until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(errorMessageXPath)))
                .getText();
    }
}

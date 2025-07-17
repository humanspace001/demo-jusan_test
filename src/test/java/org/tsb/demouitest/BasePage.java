package org.tsb.demouitest;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;

public class BasePage {
    protected AppiumDriver driver;
    private WebDriverWait wait;

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Новый формат
    }
    protected WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected WebElement waitForElementToBeVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public boolean isElementVisible(By locator) {
        try {
            return waitForElementToBeVisible(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickSafely(By locator) {
        try {
            WebElement element = waitForElementToBeClickable(locator);
            element.click();
            System.out.println("Клик выполнен: " + locator);
        } catch (Exception e) {
            System.err.println("Ошибка: элемент " + locator + " не найден или не кликабельный!");
        }
    }

    // Обычный клик (использует ожидание кликабельности)
    protected void click(By locator) {
        waitForElementToBeClickable(locator).click();
    }
    public void scrollToElement(By locator) {
        if (!isElementVisible(locator)) {
            System.out.println("Скроллим к элементу: " + locator);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            HashMap<String, Object> scrollObject = new HashMap<>();
            scrollObject.put("element", driver.findElement(locator));
            scrollObject.put("toVisible", true);
            js.executeScript("mobile: scroll", scrollObject);
        }
    }


    public void scrollToElementAndClick(By locator) {
        scrollToElement(locator);
        clickSafely(locator);
    }
}


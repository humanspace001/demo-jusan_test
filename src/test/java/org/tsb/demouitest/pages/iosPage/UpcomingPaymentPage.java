package org.tsb.demouitest.pages.iosPage;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tsb.demouitest.BasePage;
import java.util.Optional;
import org.tsb.demouitest.apiData.JsonUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UpcomingPaymentPage extends BasePage {


    private static final Logger log = LoggerFactory.getLogger(UpcomingPaymentPage.class);
    private JSONObject creditData;

    private final Map<String, By> locators = new HashMap<>();

    public UpcomingPaymentPage(AppiumDriver driver) {
        super(driver);

        // Заголовки
        locators.put("headerText", AppiumBy.xpath("//XCUIElementTypeStaticText[@name='Сейчас вы можете совершить:']"));
        locators.put("scheduledRepaymentText", AppiumBy.xpath("//XCUIElementTypeStaticText[@name='Плановое погашение - оплатить следующий платеж по графику']"));
        locators.put("earlyRepaymentText", AppiumBy.xpath("//XCUIElementTypeStaticText[@name='Досрочое погашение - погасить часть займа досрочно, чтобы уменьшить ежемесячный платеж, либо погасить займ полностью']"));
        locators.put("paymentSection", AppiumBy.xpath("//XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[6]"));
        locators.put("closeButton", AppiumBy.xpath("//XCUIElementTypeButton[@name='Закрыть']"));


        locators.put("monthlyPaymentLabel", AppiumBy.xpath("//XCUIElementTypeStaticText[@name='Ежемесячный платеж']"));
        locators.put("monthlyPaymentValue", AppiumBy.xpath("(//XCUIElementTypeStaticText[contains(@name, '₸')])[2]"));


        locators.put("paidLabel", AppiumBy.xpath("//XCUIElementTypeStaticText[@name='Оплачено']"));
        locators.put("paidValue", AppiumBy.xpath("(//XCUIElementTypeStaticText[contains(@name, '₸')])[1]"));

        locators.put("remainingDebtLabel", AppiumBy.xpath("//XCUIElementTypeStaticText[@name='Остаток задолженности на сегодня']"));
        locators.put("remainingDebtValue", AppiumBy.xpath("//XCUIElementTypeStaticText[@name='Остаток задолженности на сегодня']/following-sibling::XCUIElementTypeStaticText"));

        locators.put("overduePrincipalLabel", AppiumBy.xpath("//XCUIElementTypeStaticText[@name='Остаток основного долга']"));
        locators.put("overduePrincipalValue", AppiumBy.xpath("//XCUIElementTypeStaticText[@name='Остаток основного долга']/following-sibling::XCUIElementTypeStaticText"));

        locators.put("overdueInterestLabel", AppiumBy.xpath("//XCUIElementTypeStaticText[@name='Проценты на сегодня']"));
        locators.put("overdueInterestValue", AppiumBy.xpath("//XCUIElementTypeStaticText[@name='Проценты на сегодня']/following-sibling::XCUIElementTypeStaticText"));

        locators.put("penaltiesLabel", AppiumBy.xpath("//XCUIElementTypeStaticText[@name='Пеня']"));
        locators.put("penaltiesValue", AppiumBy.xpath("//XCUIElementTypeStaticText[@name='Пеня']/following-sibling::XCUIElementTypeStaticText"));

        locators.put("overdueDaysLabel", AppiumBy.xpath("//XCUIElementTypeStaticText[@name='Дней просрочки']"));
        locators.put("overdueDaysValue", AppiumBy.xpath("//XCUIElementTypeStaticText[@name='Дней просрочки']/following-sibling::XCUIElementTypeStaticText"));
    }
    private final Map<String, By> amountLocators = new HashMap<>() {{
        put("monthlyPaymentValue", AppiumBy.xpath("//XCUIElementTypeStaticText[@name='monthlyPaymentValue']"));
        put("paidValue", AppiumBy.xpath("//XCUIElementTypeStaticText[@name='paidValue']"));
    }};


    public boolean isElementVisible(String elementKey) {
        try {
            return driver.findElement(locators.get(elementKey)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public Optional<String> getText(String elementKey) {
        try {
            WebElement element = driver.findElement(locators.get(elementKey));
            return Optional.of(element.getText().trim());
        } catch (NoSuchElementException e) {
            System.out.printf("⚠️ Элемент '%s' не найден!%n", elementKey);
            return Optional.empty();
        }
    }

//    public Optional<BigDecimal> getNumericValue(String elementKey) {
//        try {
//            WebElement element = driver.findElement(locators.get(elementKey));
//            String text = element.getText().trim();
//            String numericText = text.replaceAll("[^0-9.]", "");
//            if (numericText.isEmpty()) {
//                return Optional.empty();
//            }
//
//            return Optional.of(new BigDecimal(numericText));
//        } catch (NoSuchElementException e) {
//            System.out.printf("⚠️ Элемент '%s' не найден!%n", elementKey);
//            return Optional.empty();
//        }
//    }

    public void clickOnPaymentSection() {
        clickSafely(locators.get("paymentSection"));
    }

    public void clickOnCloseButton() {
        clickSafely(locators.get("closeButton"));
    }

    public boolean verifyElementText(String labelKey, String valueKey, Object expectedValue) {
        String label = getText(labelKey).orElse("LABEL_NOT_FOUND");
        String actualValue = getText(valueKey).orElse("NOT_FOUND");

        System.out.printf("🔍 Проверяем: %s | Ожидаемое: %s | Фактическое: %s%n", label, expectedValue, actualValue);
        return actualValue.equals(expectedValue.toString());
    }
    public String getLocatorXPath(String elementKey) {
        By locator = locators.get(elementKey);
        return (locator != null) ? locator.toString() : "LOCATOR_NOT_FOUND";
    }


    public void monthlyPaymentValueMatchesApi(JSONObject creditData, UpcomingPaymentPage upcomingPaymentPage) {
        BigDecimal expected = JsonUtils.getJsonValue(creditData,
                "next_payment_amount",
                BigDecimal.class);

        BigDecimal actual = upcomingPaymentPage.getNumericValue("monthlyPaymentValue")
                .orElseThrow(() -> new AssertionError(
                        "❌ Значение 'monthlyPaymentValue' не найдено!"
                ));

        assertThat(actual)
                .as("❌ 'monthlyPaymentValue' не совпадает")
                .isEqualTo(expected);

        System.out.println("Значение 'monthlyPaymentValue' = " + actual +
                "  Cовпадает с Ожидаемым результатом = " + expected);

    }

    public void paidValueMatchesApi(JSONObject creditData, UpcomingPaymentPage upcomingPaymentPage) {
        BigDecimal expected = JsonUtils.getJsonValue(creditData,
                "paid_amount",
                BigDecimal.class);

        BigDecimal actual = upcomingPaymentPage.getNumericValue("paidValue")
                .orElseThrow(() -> new AssertionError(
                        "❌ Значение 'paidValue' не найдено!"
                ));

        assertThat(actual)
                .as("❌ 'paidValue' не совпадает")
                .isEqualTo(expected);
    }

    public Optional<BigDecimal> getNumericValue(String key) {
        By locator = amountLocators.get(key);
        if (locator == null) {
            return Optional.empty();
        }
        try {
            String raw = driver.findElement(locator).getText();
            String sanitized = raw.replaceAll("[^\\d.,-]", "")
                    .replace(",", ".")
                    .trim();

            if (sanitized.isEmpty() || sanitized.equals("-") || sanitized.equals("."))
                return Optional.empty();

            return Optional.of(new BigDecimal(sanitized));
        } catch (NoSuchElementException | StaleElementReferenceException |
                 NumberFormatException e) {
            return Optional.empty();
        }
    }

}

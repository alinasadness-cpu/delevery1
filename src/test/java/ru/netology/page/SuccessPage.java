package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class SuccessPage {
    private final SelenideElement successNotification = $("[data-test-id='order-success']");

    public SuccessPage checkSuccessVisible() {
        successNotification.shouldBe(visible);
        sleep(500);
        return this;
    }

    public SuccessPage checkSuccessText(String expectedText) {
        successNotification.shouldBe(visible);
        return this;
    }
}

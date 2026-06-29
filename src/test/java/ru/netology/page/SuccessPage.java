package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class SuccessPage {
    private final SelenideElement successNotification = $("[data-test-id='order-success']");

    public void checkSuccessVisible() {
        successNotification.shouldBe(visible);
    }
}

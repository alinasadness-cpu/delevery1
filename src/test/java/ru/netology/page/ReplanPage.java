package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ReplanPage {
    private final SelenideElement notification = $("[data-test-id='replan-notification']");
    private final SelenideElement replanButton = $("[data-test-id='replan-notification'] button");

    public void checkNotificationVisible() {
        notification.shouldBe(visible);
    }

    public void replan() {
        replanButton.shouldBe(visible).click();
    }
}

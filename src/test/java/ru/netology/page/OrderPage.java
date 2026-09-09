package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class OrderPage {

    private final SelenideElement cityField = $("[data-test-id='city'] input");
    private final SelenideElement dateField = $("[data-test-id='date'] input");
    private final SelenideElement nameField = $("[data-test-id='name'] input");
    private final SelenideElement phoneField = $("[data-test-id='phone'] input");
    private final SelenideElement agreementCheckbox = $("[data-test-id='agreement'] .checkbox__box");
    private final SelenideElement planButton = $(".button__text");

    private final SelenideElement successNotification = $("[data-test-id='success-notification'] .notification__content");
    private final SelenideElement replanNotification = $("[data-test-id='replan-notification'] .notification__content");
    private final SelenideElement replanButton = $("[data-test-id='replan-notification'] button");

    @Step("Заполнить форму данными: {userInfo}, дата: {date}")
    public void fillForm(DataHelper.UserInfo userInfo, String date) {
        cityField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        cityField.setValue(userInfo.getCity());

        dateField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        dateField.setValue(date);

        nameField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        nameField.setValue(userInfo.getName());

        phoneField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        phoneField.setValue(userInfo.getPhone());
    }

    @Step("Согласиться с условиями")
    public void agree() {
        agreementCheckbox.click();
    }

    @Step("Нажать кнопку 'Запланировать'")
    public void continueOrder() {
        planButton.click();
    }

    @Step("Нажать кнопку 'Перепланировать'")
    public void replan() {
        replanButton.click();
    }

    @Step("Проверить уведомление об успехе: {expectedText}")
    public void checkSuccessNotification(String expectedText) {
        successNotification.shouldBe(visible)
                .shouldHave(text(expectedText));
    }

    @Step("Проверить уведомление о перепланировании: {expectedText}")
    public void checkReplanNotification(String expectedText) {
        replanNotification.shouldBe(visible)
                .shouldHave(text(expectedText));
    }
}



package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
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

    public void fillForm(DataHelper.UserInfo userInfo, String date) {
        cityField.doubleClick();
        cityField.sendKeys(Keys.BACK_SPACE);
        cityField.setValue(userInfo.getCity());
        
        dateField.doubleClick();
        dateField.sendKeys(Keys.BACK_SPACE);
        dateField.setValue(date);
        
        nameField.doubleClick();
        nameField.sendKeys(Keys.BACK_SPACE);
        nameField.setValue(userInfo.getName());
        
        phoneField.doubleClick();
        phoneField.sendKeys(Keys.BACK_SPACE);
        phoneField.setValue(userInfo.getPhone());
    }

    public void agree() {
        agreementCheckbox.click();
    }

    public void continueOrder() {
        planButton.click();
    }

    public void replan() {
        replanButton.click();
    }

    public void checkSuccessNotification(String expectedText) {
        successNotification.shouldBe(visible)
                .shouldHave(text(expectedText));
    }

    public void checkReplanNotification(String expectedText) {
        replanNotification.shouldBe(visible)
                .shouldHave(text(expectedText));
    }
}

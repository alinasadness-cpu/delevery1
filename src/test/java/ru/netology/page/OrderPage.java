package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class OrderPage {
    private final SelenideElement cityField = $("[data-test-id='city'] input");
    private final SelenideElement dateField = $(".calendar-input__native-control");
    private final SelenideElement nameField = $("[name='name']");
    private final SelenideElement phoneField = $("[name='phone']");
    private final SelenideElement agreementCheckbox = $("[data-test-id='agreement'] .checkbox__box");
    private final SelenideElement planButton = $(".button__text"); // Кнопка "Запланировать"

    public OrderPage fillForm(DataHelper.UserInfo userInfo, String date) {
        cityField.shouldBe(visible).setValue(userInfo.getCity());
        dateField.shouldBe(visible).setValue(date);
        nameField.shouldBe(visible).setValue(userInfo.getName());
        phoneField.shouldBe(visible).setValue(userInfo.getPhone());
        return this;
    }

    public OrderPage agree() {
        agreementCheckbox.shouldBe(visible).click();
        return this;
    }

    public OrderPage continueOrder() {
        planButton.shouldBe(visible).click();
        return this;
    }
}

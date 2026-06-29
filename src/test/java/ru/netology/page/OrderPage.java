package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class OrderPage {
    private final SelenideElement cityField = $("[data-test-id='city'] input");
    private final SelenideElement dateField = $("[data-test-id='date'] input");
    private final SelenideElement nameField = $("[data-test-id='name'] input");
    private final SelenideElement phoneField = $("[data-test-id='phone'] input");
    private final SelenideElement agreementCheckbox = $("[data-test-id='agreement'] .checkbox__box");
    private final SelenideElement continueButton = $(".button_theme_alfa-on-white");

    public OrderPage fillForm(DataHelper.UserInfo userInfo, String date) {
        cityField.setValue(userInfo.getCity());
        dateField.setValue(date);
        nameField.setValue(userInfo.getName());
        phoneField.setValue(userInfo.getPhone());
        return this;
    }

    public OrderPage agree() {
        agreementCheckbox.click();
        return this;
    }

    public OrderPage continueOrder() {
        continueButton.click();
        return this;
    }
}
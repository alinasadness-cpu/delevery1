package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class OrderPage {
    
    private final SelenideElement cityField = $("[data-test-id='city'] input");
    private final SelenideElement dateField = $("input[type='date']");
    private final SelenideElement nameField = $("input[name='name']");
    private final SelenideElement phoneField = $("input[name='phone']");
    private final SelenideElement agreementCheckbox = $("[data-test-id='agreement'] .checkbox__box");
    private final SelenideElement planButton = $(".button__text");

    public OrderPage fillForm(DataHelper.UserInfo userInfo, String date) {
        cityField.shouldBe(visible).setValue(userInfo.getCity());
        sleep(300);
        dateField.shouldBe(visible).setValue(date);
        sleep(300);
        nameField.shouldBe(visible).setValue(userInfo.getName());
        sleep(300);
        phoneField.shouldBe(visible).setValue(userInfo.getPhone());
        sleep(300);
        return this;
    }

    public OrderPage agree() {
        agreementCheckbox.shouldBe(visible).click();
        sleep(300);
        return this;
    }

    public OrderPage continueOrder() {
        planButton.shouldBe(visible).click();
        sleep(500);
        return this;
    }
}

package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class OrderPage {
    private final SelenideElement cityField = $("[data-test-id='city'] input");
    private final SelenideElement dateField = $(".calendar-input__native-control");
    private final SelenideElement nameField = $("input[name='name']");
    private final SelenideElement phoneField = $("input[name='phone']");
    private final SelenideElement agreementCheckbox = $("[data-test-id='agreement'] .checkbox__box");
    private final SelenideElement planButton = $(".button__text");

    public void fillForm(DataHelper.UserInfo userInfo, String date) {
        System.out.println("=== FILLING FORM ===");
        
        try {
            System.out.println("Setting city: " + userInfo.getCity());
            cityField.shouldBe(visible).setValue(userInfo.getCity());
            System.out.println("✅ City set");
        } catch (Exception e) {
            System.out.println("❌ ERROR setting city: " + e.getMessage());
            throw e;
        }
        sleep(300);
        
        try {
            System.out.println("Setting date: " + date);
            dateField.shouldBe(visible).setValue(date);
            System.out.println("✅ Date set");
        } catch (Exception e) {
            System.out.println("❌ ERROR setting date: " + e.getMessage());
            throw e;
        }
        sleep(300);
        
        try {
            System.out.println("Setting name: " + userInfo.getName());
            nameField.shouldBe(visible).setValue(userInfo.getName());
            System.out.println("✅ Name set");
        } catch (Exception e) {
            System.out.println("❌ ERROR setting name: " + e.getMessage());
            throw e;
        }
        sleep(300);
        
        try {
            System.out.println("Setting phone: " + userInfo.getPhone());
            phoneField.shouldBe(visible).setValue(userInfo.getPhone());
            System.out.println("✅ Phone set");
        } catch (Exception e) {
            System.out.println("❌ ERROR setting phone: " + e.getMessage());
            throw e;
        }
        sleep(300);
        
        System.out.println("=== FORM FILLED ===");
    }

    public void agree() {
        System.out.println("Checking agreement...");
        agreementCheckbox.shouldBe(visible).click();
        sleep(300);
        System.out.println("✅ Agreement checked");
    }

    public void continueOrder() {
        System.out.println("Clicking plan button...");
        planButton.shouldBe(visible).click();
        sleep(500);
        System.out.println("✅ Button clicked");
    }
}

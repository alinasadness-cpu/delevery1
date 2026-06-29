package ru.netology.delivery.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.delivery.data.DataGenerator;
import ru.netology.delivery.data.UserInfo;

import static com.codeborne.selenide.Selenide.*;

public class DeliveryTest {

    private UserInfo user;
    private String firstDate;
    private String secondDate;

    @BeforeAll
    static void setUpAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.headless = true;
        Configuration.timeout = 15000;
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
        firstDate = DataGenerator.generateDate(3);
        secondDate = DataGenerator.generateDate(5);
        user = DataGenerator.generateUser();
    }

    @Test
    void shouldRescheduleMeeting() {
        // 1. Заполнение формы первой датой
        $("[data-test-id='city'] input").setValue(user.getCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(firstDate);
        $("[data-test-id='name'] input").setValue(user.getName());
        $("[data-test-id='phone'] input").setValue(user.getPhone());
        $("[data-test-id='agreement'] .checkbox__text").click();
        $$("button").findBy(Condition.text("Запланировать")).click();

        // 2. Проверка успешного планирования
        $("[data-test-id='success-notification']").shouldBe(Condition.visible);
        $("[data-test-id='success-notification'] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + firstDate));

        // 3. Изменение даты и повторная отправка
        $("[data-test-id='date'] input").doubleClick().sendKeys(secondDate);
        $$("button").findBy(Condition.text("Запланировать")).click();

        // 4. Проверка диалога перепланирования
        $("[data-test-id='replan-notification']").shouldBe(Condition.visible);
        $("[data-test-id='replan-notification'] .notification__content")
                .shouldHave(Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?"));

        // 5. Нажатие кнопки "Перепланировать"
        $$("button").findBy(Condition.text("Перепланировать")).click();

        // 6. Проверка успешного перепланирования
        $("[data-test-id='success-notification']").shouldBe(Condition.visible);
        $("[data-test-id='success-notification'] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + secondDate));
    }
}
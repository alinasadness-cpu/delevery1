package ru.netology.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.OrderPage;

import static com.codeborne.selenide.Selenide.open;

public class OrderDeliveryTest {
    private DataHelper.UserInfo userInfo;
    private String initialDate;
    private String newDate;

    @BeforeEach
    void setup() {
        open("http://localhost:9999");   
        userInfo = DataHelper.generateUserInfo();
        initialDate = DataHelper.generateDate(3);
        newDate = DataHelper.generateDate(7);
    }

    @Test
    void shouldReplanMeeting() {
        OrderPage orderPage = new OrderPage();

        orderPage.fillForm(userInfo, initialDate);
        orderPage.agree();
        orderPage.continueOrder();
        orderPage.checkSuccessNotification("Встреча успешно запланирована на " + initialDate);

        orderPage.fillForm(userInfo, newDate);
        orderPage.agree();
        orderPage.continueOrder();

        orderPage.checkReplanNotification("У вас уже запланирована встреча на другую дату. Перепланировать?");
        orderPage.replan();

        orderPage.checkSuccessNotification("Встреча успешно запланирована на " + newDate);
    }
}

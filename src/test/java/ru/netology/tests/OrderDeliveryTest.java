package ru.netology.tests;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.OrderPage;
import ru.netology.page.ReplanPage;
import ru.netology.page.SuccessPage;

import static com.codeborne.selenide.Selenide.open;

public class OrderDeliveryTest {
    private DataHelper.UserInfo userInfo;
    private String initialDate;
    private String newDate;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setup() { 
        open("http://localhost:9999");
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
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

        SuccessPage successPage = new SuccessPage();
        successPage.checkSuccessVisible();

        orderPage = new OrderPage();
        orderPage.fillForm(userInfo, newDate);
        orderPage.agree();
        orderPage.continueOrder();

        ReplanPage replanPage = new ReplanPage();
        replanPage.checkNotificationVisible();
        replanPage.replan();

        successPage = new SuccessPage();
        successPage.checkSuccessVisible();
    }
}

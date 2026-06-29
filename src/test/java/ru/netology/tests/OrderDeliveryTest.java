package ru.netology.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.OrderPage;
import ru.netology.page.ReplanPage;
import ru.netology.page.SuccessPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
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
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 15000;
        Configuration.headless = true;
        
        open("http://localhost:9999");
        
        // Ждем загрузки страницы
        $("[data-test-id='city'] input").shouldBe(visible);
        
        userInfo = DataHelper.generateUserInfo();
        initialDate = DataHelper.generateDate(3);
        newDate = DataHelper.generateDate(7);
    }

    @Test
    void shouldReplanMeeting() {
        // Первый заказ
        OrderPage orderPage = new OrderPage();
        orderPage.fillForm(userInfo, initialDate)
                .agree()
                .continueOrder();

        new SuccessPage().checkSuccessVisible();

        // Второй заказ с новой датой
        orderPage = new OrderPage();
        orderPage.fillForm(userInfo, newDate)
                .agree()
                .continueOrder();

        // Перепланирование
        ReplanPage replanPage = new ReplanPage();
        replanPage.checkNotificationVisible()
                .replan();

        new SuccessPage().checkSuccessText("Встреча успешно запланирована на " + newDate);
    }
}

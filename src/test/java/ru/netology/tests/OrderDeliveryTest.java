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
        Configuration.headless = true;
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 15000;
        
        open("http://localhost:9999");
        Selenide.sleep(3000);
        
        userInfo = DataHelper.generateUserInfo();
        initialDate = DataHelper.generateDate(3);
        newDate = DataHelper.generateDate(7);
        
        // ОТЛАДКА: выводим данные
        System.out.println("=== TEST DATA ===");
        System.out.println("City: " + userInfo.getCity());
        System.out.println("Name: " + userInfo.getName());
        System.out.println("Phone: " + userInfo.getPhone());
        System.out.println("Initial date: " + initialDate);
        System.out.println("New date: " + newDate);
        System.out.println("=================");
    }

    @Test
    void shouldReplanMeeting() {
        // ОТЛАДКА: проверяем элементы ДО заполнения
        System.out.println("Checking elements before fill...");
        
        try {
            $("[data-test-id='city'] input").shouldBe(visible);
            System.out.println("✅ City field found");
        } catch (Exception e) {
            System.out.println("❌ City field NOT found: " + e.getMessage());
        }
        
        try {
            $(".calendar-input__native-control").shouldBe(visible);
            System.out.println("✅ Date field found");
        } catch (Exception e) {
            System.out.println("❌ Date field NOT found: " + e.getMessage());
        }
        
        try {
            $("input[name='name']").shouldBe(visible);
            System.out.println("✅ Name field found");
        } catch (Exception e) {
            System.out.println("❌ Name field NOT found: " + e.getMessage());
        }
        
        try {
            $("input[name='phone']").shouldBe(visible);
            System.out.println("✅ Phone field found");
        } catch (Exception e) {
            System.out.println("❌ Phone field NOT found: " + e.getMessage());
        }
        
        System.out.println("=== STARTING FILL FORM ===");
        
        // Теперь заполняем форму
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

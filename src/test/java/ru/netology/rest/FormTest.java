package ru.netology.rest;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.rest.DataClass.*;


public class FormTest {
    String City = getCity();
    String Name = getName();
    String Phone = getPhone();
    LocalDate data = LocalDate.now().plusDays(5);
    DateTimeFormatter DateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    LocalDate now = LocalDate.now();

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

        @Test
        void shouldRegistrationForm () {
            open("http://localhost:9999");
            SelenideElement form = $("[action]");
            form.$(("[data-test-id=city] input")).sendKeys(City);
            form.$(("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
            form.$(("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
            form.$(("[data-test-id=date] input")).sendKeys(data.format(DateFormatter));
            form.$(("[name=name]")).sendKeys(Name);
            form.$(("[name=phone]")).sendKeys(Phone);
            form.$(("[data-test-id=agreement]")).click();
            form.$(byText("Запланировать")).click();
            $((".notification__title")).waitUntil(Condition.visible, 15000);
        }

        @Test
        void shouldRegistrationNewDateCheck () {
            open("http://localhost:9999");
            SelenideElement form = $("[action]");
            form.$(("[data-test-id=city] input")).sendKeys(City);
            form.$(("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
            form.$(("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
            form.$(("[data-test-id=date] input")).sendKeys(data.format(DateFormatter));
            form.$(("[name=name]")).sendKeys(Name);
            form.$(("[name=phone]")).sendKeys(Phone);
            form.$(("[data-test-id=agreement]")).click();
            form.$(byText("Запланировать")).click();
            $(byText("Успешно!")).waitUntil(Condition.visible, 15000);
            form.$(("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
            form.$(("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
            form.$(("[data-test-id=date] input")).sendKeys(data.format(DateFormatter));
            form.$(byText("Запланировать")).click();
            $((".notification_status_error .button")).click();
            $(withText("Успешно!")).waitUntil(Condition.visible, 15000);
        }

        @Test
        void shouldNotRegistrationIfDataInvalid () {
            open("http://localhost:9999");
            SelenideElement form = $("[action]");
            form.$(("[data-test-id=city] input")).sendKeys(City);
            form.$(("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
            form.$(("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
            form.$(("[data-test-id=date] input")).sendKeys(data.format(DateFormatter));
            form.$(("[name=name]")).sendKeys(Name);
            form.$(("[name=phone]")).sendKeys(Phone);
            form.$(("[data-test-id=agreement]")).click();
            form.$(byText("Запланировать")).click();
            $(byText("Успешно!")).waitUntil(Condition.visible, 15000);
            form.$(("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
            form.$(("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
            form.$(("[data-test-id=date] input")).sendKeys(now.format(DateFormatter));
            form.$(("[data-test-id=agreement]")).click();
            form.$(byText("Запланировать")).click();
            $(byText("Заказ на выбранную дату невозможен")).waitUntil(Condition.visible, 15000);
        }

    }
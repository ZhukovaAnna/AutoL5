package ru.netology.rest;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.cssSelector;

public class FormTest {
    private DataClass dataClass = new DataClass();
     String City= dataClass.randomCity();
     String Name = dataClass.getName();
     String Phone = dataClass.getPhone();
    LocalDate data = LocalDate.now().plusDays(5);
    DateTimeFormatter DateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    LocalDate now = LocalDate.now();

    @Test
    void shouldRegistrationForm(){
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
    void shouldRegistrationNewDateCheck() {
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
        form.$(("[data-test-id=agreement]")).click();
       form.$(byText("Запланировать")).click();
        $(withText("Успешно!")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void shouldNotRegistrationIfDataInvalid() {
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
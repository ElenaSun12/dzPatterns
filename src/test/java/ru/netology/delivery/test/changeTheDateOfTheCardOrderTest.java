package ru.netology.delivery.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.delivery.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class DeliveryTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        // TODO: добавить логику теста в рамках которого будет выполнено планирование и перепланирование встречи.
        // Для заполнения полей формы можно использовать пользователя validUser и строки с датами в переменных
        // firstMeetingDate и secondMeetingDate. Можно также вызывать методы generateCity(locale),
        // generateName(locale), generatePhone(locale) для генерации и получения в тесте соответственно города,
        // имени и номера телефона без создания пользователя в методе generateUser(String locale) в датагенераторе

        // Заполняем форму первой встречи
        SelenideElement form = $(".form");
        $("[data-test-id=city] input").setValue(validUser.getCity());
        $("[data-test-id=date] input").doubleClick().sendKeys(firstMeetingDate);
        $("[data-test-id=name] input").setValue(validUser.getName());
        $("[data-test-id=phone] input").setValue(validUser.getPhone());
        $("[data-test-id=agreement]").click();

        // Отправляем форму
        form.$(".button").click();
        // Использовать `exactText` надежнее, если на странице могут быть другие кнопки
//        $(withText("Запланировать")).click();

        // Проверяем успешное планирование первой встречи
        $("[data-test-id=success-notification] .notification__content").shouldHave(Condition.text("Встреча успешно запланирована на " + firstMeetingDate), Duration.ofSeconds(15)).shouldBe(Condition.visible);

        // Перепланируем встречу
        //form.$("[data-test-id='date'] input").setValue(secondMeetingDate); - тут что-то не так, не меняет дату на secondMeetingDate
        $("[data-test-id=date] input").doubleClick().sendKeys(secondMeetingDate);
        //$(withText("Запланировать")).click();
        form.$(".button").click();


        // Проверяем наличие окна с перепланированием
        $("[data-test-id=replan-notification]").shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?"));

        // Нажимаем кнопку "Перепланировать"
        $("[data-test-id=replan-notification] .button").click();

        // Проверяем успешное перепланирование
        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + secondMeetingDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }
//        $("[data-test-id=success-notification] .notification__title")
//                .shouldBe(Condition.visible, Duration.ofSeconds(15))
//                .shouldHave(Condition.text("Успешно"));
//    }
}

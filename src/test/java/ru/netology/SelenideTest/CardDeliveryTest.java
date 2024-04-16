package ru.netology.SelenideTest;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    public static String date(int days) {

        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    }

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTest() {
        String date = date(3);
        $("[data-test-id='city'] input").setValue("Санкт-Петербург");
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Леонов Алекс Васин");
        $("[data-test-id='phone'] input").setValue("+79112223333");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='notification'] .notification__content").shouldHave(text("Встреча успешно забронирована на " + date), Duration.ofMillis(15_000)).shouldBe(visible);


    }

}

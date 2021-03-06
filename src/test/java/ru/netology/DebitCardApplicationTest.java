package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class DebitCardApplicationTest {
    @BeforeEach
    public void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    void shouldSubmitRequest() {

        $("[data-test-id=name] input").setValue("Салам Юлия");
        $("[data-test-id=phone] input").setValue("+79600000000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldSubmitRequestNotValid1() {

        $("[data-test-id=name] input").setValue("Juliya");
        $("[data-test-id=phone] input").setValue("+79600000000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[class=input__sub]").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldSubmitRequestNotValid2() {

        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79600000000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldSubmitRequestNotValid3() {

        $("[data-test-id=name] input").setValue("Юлия");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldSubmitRequestNotValid4() {

        $("[data-test-id=name] input").setValue("Юлия");
        $("[data-test-id=phone] input").setValue("+7960000000000000000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldSubmitRequestNotValid5() {

        $("[data-test-id=name] input").setValue("Юлия");
        $("[data-test-id=phone] input").setValue("+79600000000");
        // $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
}

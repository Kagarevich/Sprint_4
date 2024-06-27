package ru.ya.uicheck;

import org.junit.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ya.page.HomePageMestoSelenide;
import ru.ya.page.PageObject;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideBaseChecks extends Base {
    @Test
    public void check() {
        $(byLinkText("Регистрация")).shouldBe(enabled,Duration.ofSeconds(10)).click();
        $(byId("email")).setValue("test.testov@gmail.com");
        $(byId("password")).setValue("Qwerty123!");
        $(byText("Зарегистрироваться")).shouldBe(enabled, Duration.ofSeconds(10)).click();
    }

    @Test
    public void check2() {
        System.out.println($$(byClassName("auth-form__textfield")).size());
    }

    @Test
    public void check3() {
        HomePageMestoSelenide homePage = po.signIn("test.testov@gmail.com", "Qwerty123!");
        String cardText = $$(byClassName("card")).get(2).find(byClassName("card__title")).getText();
    }
}

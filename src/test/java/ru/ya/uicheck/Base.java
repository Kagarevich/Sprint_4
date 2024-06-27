package ru.ya.uicheck;

import org.junit.Before;
import ru.ya.page.PageObject;

import static com.codeborne.selenide.Selenide.*;

public class Base {
    protected PageObject po;

    @Before
    public void openPage() {
        po = open(
                "https://qa-mesto.praktikum-services.ru/",
                PageObject.class);
    }
}

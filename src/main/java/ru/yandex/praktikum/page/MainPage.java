package ru.yandex.praktikum.page;

import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {
    public static final String URL = "https://qa-scooter.praktikum-services.ru/";

    public MainPage(WebDriver driver) {
        super(driver);
    }
}

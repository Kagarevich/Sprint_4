package ru.yandex.praktikum.uicheck;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.page.AboutRentPage;
import ru.yandex.praktikum.page.ConfirmOrderPage;
import ru.yandex.praktikum.page.HomePage;
import ru.yandex.praktikum.page.OrderPage;

@RunWith(Parameterized.class)
public class OrderTest extends BaseTestClass {

    private final String button; //кнопка по местоположению на HomePage
    private final String name;
    private final String surname;
    private final String address;
    private final String phoneNumber;
    private final String date;
    private final String color;
    private final String comment;
    private final String expectedConfirmOrderText = "Заказ оформлен";
    private final int timeRent;
    private final int stationNumber;


    public OrderTest(
            String button,
            String name,
            String surname,
            String address,
            String phoneNumber,
            int subwayNumber,
            String date,
            int timeRent,
            String color,
            String comment) {
        this.button = button;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.stationNumber = subwayNumber;
        this.date = date;
        this.timeRent = timeRent;
        this.color = color;
        this.comment = comment;

    }


    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {"headerOrderButton", "Райан", "Гослинг", "Кирпичникова 127", "+78007777333", 10, "19.08.2024", 7,
                        "greyColor", "Самокат"},
                {"howItWorkOrderButton", "Миша", "Пушкин", "Дом Колотушкин 40", "+78005553535", 5, "19.08.2024", 7,
                        "blackColor", "Не надо"},
                {"headerOrderButton", "Рима", "Тарасова", "Гоголя 34", "88005553535", 15, "19.06.2024", 7,
                        "greyAndBlackColor", "Не ищите меня"},
                {"howItWorkOrderButton", "Костя", "Пушкин", "Дом Колотушкин 117", "+78005553535", 5, "19.08.2024", 7,
                        "blackColor", "Не верну"},
        };
    }


    @Test
    public void buttonOrder() {

        HomePage mainPage = new HomePage(driver);
        //Кликаем на кнопку "Заказать"
        mainPage.orderButtonClick(button);

        OrderPage orderPage = new OrderPage(driver);
        //заполняем поля заказа
        orderPage.nameInputClearThenWrite(name);
        orderPage.surnameInputClearThenWrite(surname);
        orderPage.addressInputClearThenWrite(address);
        orderPage.subwayStationChoose(stationNumber);
        orderPage.phoneNumberInputClearThenWrite(phoneNumber);
        //Жмём кнопку "Далее"
        orderPage.nextButtonClick();

        AboutRentPage aboutRent = new AboutRentPage(driver);
        //заполняем поля аренды
        aboutRent.waitForLoadRentPage();
        aboutRent.dateRentClearThenWrite(date);
        aboutRent.chooseTimeRent(timeRent);
        aboutRent.chooseColorRent(color);
        aboutRent.commentClearThenWrite(comment);
        //Жмём кнопку "Заказать"
        aboutRent.rentButtonClick();

        ConfirmOrderPage confirmOrderPage = new ConfirmOrderPage(driver);
        confirmOrderPage.waitLoadConfirmModal();
        //Жмём кнопку "Да"
        //Тут как я понял и есть баг, так как кнопка находится, но не кликается, если хром запущен через автотест
        confirmOrderPage.confirmButtonClick();

        //Сложная(нет, но пришлось вспоминать матчер) проверка, что заказ оформлен по надписи на экране "Заказ оформлен"
        //не увидел смысла проверять всё доп окно "Заказ оформлен", для текущей задачи достаточно наличие надписи
        Assert.assertThat(
                "Отсутствует подтверждение заказа",
                confirmOrderPage.getTextModalHeader(),
                CoreMatchers.containsString(expectedConfirmOrderText));
    }
}

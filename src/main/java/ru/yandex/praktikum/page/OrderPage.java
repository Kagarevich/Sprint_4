package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class OrderPage {
    private final WebDriver driver;
    //локатор инпута "Имя"
    private final By nameInput = By.xpath(".//input[@class='Input_Input__1iN_Z " +
            "Input_Responsible__1jDKN' and @placeholder='* Имя']");
    // локатор инпута "Фамилия"
    private final By surnameInput = By.xpath(".//input[@class='Input_Input__1iN_Z " +
            "Input_Responsible__1jDKN' and @placeholder='* Фамилия']");
    //локатор инпута "Адрес"
    private final By addressInput = By.xpath(".//input[@class='Input_Input__1iN_Z " +
            "Input_Responsible__1jDKN' and @placeholder='* Адрес: куда привезти заказ']");
    //локатор инпута станции метро (можно было назвать и underground на UK манер, а не subway)
    private final By subwayStationListSearchInput = By.xpath("//input[@class='select-search__input']");
    //ЗАГОТОВКА под локатор кнопки нужной станции метро
    //Пришлось вставить %s, чтобы в дальнейшем вставлять номер выбранном станции в локатор
    private final String subwayNameButton = ".//button[@value='%s']";
    //локатор поля "Телефон"
    private final By phoneNumberInput = By.xpath(".//input[@class='Input_Input__1iN_Z " +
            "Input_Responsible__1jDKN' and @placeholder='* Телефон: на него позвонит курьер']");
    //локатор кнопки "Далее"
    private final By nextButton = By.xpath(".//button[@class='Button_Button__ra12g " +
            "Button_Middle__1CSJM']");


    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void nameInputClearThenWrite(String name) {
        driver.findElement(nameInput).clear();
        driver.findElement(nameInput).sendKeys(name);
    }

    public void surnameInputClearThenWrite(String surname) {
        driver.findElement(surnameInput).clear();
        driver.findElement(surnameInput).sendKeys(surname);
    }

    public void addressInputClearThenWrite(String address) {
        driver.findElement(addressInput).clear();
        driver.findElement(addressInput).sendKeys(address);
    }

    public void subwayStationChoose(int stationNumber) {
        driver.findElement(subwayStationListSearchInput).click();
        //Вставляем номер станции в локатор.
        //можно было сделать просто через конкатинацию строк, но тогда потерялся бы элемент паттерна page object,
        //А именно локатор (в нашем случае заготовка под локатор)
        By subwayStation = By.xpath(String.format(subwayNameButton, stationNumber));
        //крутим списочек станций метро *вжух*
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView();",
                driver.findElement(subwayStation)
        );
        driver.findElement(subwayStation).click();
    }

    public void phoneNumberInputClearThenWrite(String phoneNumber) {
        driver.findElement(phoneNumberInput).clear();
        driver.findElement(phoneNumberInput).sendKeys(phoneNumber);
    }

    public void nextButtonClick() {
        driver.findElement(nextButton).click();
    }
}

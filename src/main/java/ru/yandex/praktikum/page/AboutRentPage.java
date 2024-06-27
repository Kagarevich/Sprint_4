package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AboutRentPage {
    private final WebDriver driver;

    //локатор инпута "* Когда привезти самокат"
    private final By dateRentInput = By.xpath(".//input[@placeholder='* Когда привезти самокат' " +
            "and @class='Input_Input__1iN_Z Input_Responsible__1jDKN']");

    //локатор поля "* Срок аренды"
    private final By timeRent = By.className("Dropdown-arrow");

    //ЗАГОТОВКА под локатор срока аренды в днях
    private final String timeRentInDays = ".//*[@class='Dropdown-option']";

    //локатор комментария
    private final By commentInput = By.xpath(".//input[@class='Input_Input__1iN_Z " +
            "Input_Responsible__1jDKN' and @placeholder='Комментарий для курьера']");

    //локатор кнопки заказать
    private final By confirmRentButton = By.xpath(".//button[@class='Button_Button__ra12g " +
            "Button_Middle__1CSJM' and text()='Заказать']");

    //локаторы доступных цветов самоката
    private final By colorRentBlack = By.xpath(".//label[@for='black']");
    private final By colorRentGrey = By.xpath(".//label[@for='grey']");


    public AboutRentPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadRentPage() {
        (new WebDriverWait(driver, 20)).until((driver) ->
                driver.findElement(confirmRentButton).getText() != null &&
                !driver.findElement(confirmRentButton).getText().isEmpty());
    }

    public void dateRentClearThenWrite(String date) {
        driver.findElement(dateRentInput).clear();
        driver.findElement(dateRentInput).sendKeys(date);
    }

    public void chooseTimeRent(int timeRent) {
        driver.findElement(this.timeRent).click();
        driver.findElement(By.xpath(String.format(timeRentInDays + "[%d]", timeRent))).click();
    }

    //сделал через elif в одном методе
    public void chooseColorRent(String color) {
        if (color.equals("blackColor")) {
            driver.findElement(colorRentBlack).click();
        } else if (color.equals("greyColor")) {
            driver.findElement(colorRentGrey).click();
        } else if (color.equals("greyAndBlackColor")) {
            driver.findElement(colorRentBlack).click();
            driver.findElement(colorRentGrey).click();
        }
    }

    public void commentClearThenWrite(String comment) {
        driver.findElement(commentInput).clear();
        driver.findElement(commentInput).sendKeys(comment);
    }

    public void rentButtonClick() {
        driver.findElement(confirmRentButton).click();
    }
}

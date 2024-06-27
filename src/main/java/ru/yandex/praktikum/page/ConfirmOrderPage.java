package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConfirmOrderPage {

    private final WebDriver driver;

    //локатор хедера модалки (используется в том числе и при проверке подтверждения оформления заказа)
    private final By modalHeader = By.className("Order_ModalHeader__3FDaJ");

    //локатор кнопки подтверждения "Да"
    private final By confirmOrderButton = By.xpath(".//div[@class='Order_Modal__YZ-d3']" +
            "/div[@class='Order_Buttons__1xGrp']" +
            "/*[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    public ConfirmOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitLoadConfirmModal() {
        (new WebDriverWait(this.driver, 20)).until((driver) ->
                driver.findElement(modalHeader).getText() != null &&
                        !driver.findElement(modalHeader).getText().isEmpty());
    }

    public void confirmButtonClick() {
        driver.findElement(confirmOrderButton).click();
    }

    public String getTextModalHeader() {
        return driver.findElement(modalHeader).getText();
    }
}

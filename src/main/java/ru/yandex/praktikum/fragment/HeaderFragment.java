package ru.yandex.praktikum.fragment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.praktikum.page.BasePage;
import ru.yandex.praktikum.page.OrderStatusPage;

public class HeaderFragment extends BasePage {

    private final By orderButton = By.className("Header_Link__1TAG7");
    private final By orderNumberInput = By.className("Input_Input__1iN_Z Header_Input__xIoUq");
    private final By searchOrderButton = By.xpath(".//*[@class='Button_Button__ra12g Header_Button__28dPO']");

    public HeaderFragment(WebDriver driver) {
        super(driver);
    }

    public OrderStatusPage searchOrder(String keys) {
        driver.findElement(orderButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderNumberInput)).sendKeys(keys);
        driver.findElement(searchOrderButton).click();
        return new OrderStatusPage(this.driver);
    }
}

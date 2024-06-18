package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private final WebDriver driver;

    //добавил только необходимые элементы, а не все подряд

    //кнопка "Заказать" в хедере
    private final By headerOrderButton = By.xpath(".//*[@class='Header_Nav__AGCXC']" +
            "/button[@class='Button_Button__ra12g']");

    //блок с кнопкой "Заказать" в блоке "Как это работает". Добавил для прокрутки
    private final By howItWork = By.className("Home_FinishButton__1_cWm");

    //кнопка "Заказать" в блоке "Как это работает"(RoadMap)
    //Я хотел добавить в xpath ещё класс кнопки, но браузер упорно его игнорирует
    private final By howItWorkOrderButton = By.xpath(".//div[@class='Home_FinishButton__1_cWm']" +
            "/button");

    //список вопросов "Вопросы о важном"
    private final By questionAndAnswerAccordion = By.className("accordion");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadHomePage() {
        (new WebDriverWait(driver, Duration.ofSeconds(20))).until((driver) ->
                driver.findElement(headerOrderButton).getText() != null &&
                !driver.findElement(headerOrderButton).getText().isEmpty());
    }

    public void waitForLoadHowItWork() {
        (new WebDriverWait(this.driver, Duration.ofSeconds(20))).until((driver) ->
                driver.findElement(howItWork).getText() != null && !driver.findElement(howItWork).getText().isEmpty());
    }

    public void accordionClick(By question) {
        driver.findElement(question).click();
    }

    public String getAnswerText(By answer) {
        return this.driver.findElement(answer).getText();
    }

    public void scrollQuestionAndAnswer() {
        WebElement element = driver.findElement(questionAndAnswerAccordion);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", new Object[]{element});
    }

    public void scrollHowItWork() {
        WebElement element = driver.findElement(howItWork);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", new Object[]{element});
    }

    public By getQuestion(int numberOfQuestion) {
        return By.id("accordion__heading-" + numberOfQuestion);
    }

    public By getAnswer(int numberOfAnswer) {
        return By.id("accordion__panel-" + numberOfAnswer);
    }

    //сделал один метод для нажатия выбранной кнопки
    //Можно было их сделать и константами, но тут всего две кнопки заказа
    public void orderButtonClick(String chooseButton) {
        if (chooseButton.equals("headerOrderButton")) {
            waitForLoadHomePage();
            driver.findElement(headerOrderButton).click();
        } else if (chooseButton.equals("howItWorkOrderButton")) {
            scrollHowItWork();
            waitForLoadHowItWork();
            driver.findElement(howItWorkOrderButton).click();
        }
    }
}

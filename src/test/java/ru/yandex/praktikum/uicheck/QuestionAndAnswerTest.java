package ru.yandex.praktikum.uicheck;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.page.HomePage;

import static ru.yandex.praktikum.page.constant.Answers.*;

//хотел назвать класс Q&A или QandA, но выбрал оптимальный вариант, хоть и не самый лаконичный
//также можно было назвать и FAQ
@RunWith(Parameterized.class)
public class QuestionAndAnswerTest extends BaseTestClass {

    private final int numberOfQuestion;
    private final int numberOfAnswer;
    private final String expectedResult;

    public QuestionAndAnswerTest(int numberOfQuestion, int numberOfAnswer, String expected) {
        this.numberOfQuestion = numberOfQuestion;
        this.numberOfAnswer = numberOfAnswer;
        this.expectedResult = expected;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {0, 0, ANSWER_0},
                {1, 1, ANSWER_1},
                {2, 2, ANSWER_2},
                {3, 3, ANSWER_3},
                {4, 4, ANSWER_4},
                {5, 5, ANSWER_5},
                {6, 6, ANSWER_6},
                {7, 7, ANSWER_7},
        };
    }

    @Test
    public void equalsAnswerQuestion() {
        HomePage mainPage = new HomePage(driver);
        //ждем загрузки страницы
        mainPage.waitForLoadHomePage();
        //прокрутка до списка вопросов и ответов
        mainPage.scrollQuestionAndAnswer();
        //кликаем по варианту вопроса
        mainPage.accordionClick(mainPage.getQuestion(numberOfQuestion));
        //получаем текст ответа и сохраняем его для крассивого ассерта
        String actualResult = mainPage.getAnswerText(mainPage.getAnswer(numberOfAnswer));
        //сравниваем ожидаемый и фактический результаты
        Assert.assertEquals("Ожидаемый текст не совпадает с фактическим!", expectedResult, actualResult);
    }
}

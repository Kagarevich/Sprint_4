package ru.yandex.praktikum.webinarUiChecks;

import org.junit.Test;
import ru.yandex.praktikum.fragment.HeaderFragment;
import ru.yandex.praktikum.page.BasePage;

import static org.junit.Assert.assertTrue;

public class OrderStatusCheckTest extends BaseTest {

    @Test
    public void checkOrderStatusUnknownNumbersShowsNotFoundImage() {

        String keys = "5";

        boolean result = new HeaderFragment(driver)
                .searchOrder(keys)
                .isImageDisplayed();

        assertTrue(result);
    }
}

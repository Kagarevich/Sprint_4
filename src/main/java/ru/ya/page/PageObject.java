package ru.ya.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class PageObject {
    @FindBy(how = How.ID, using = "email")
    private SelenideElement emailField;
    @FindBy(how = How.ID, using = "password")
    private SelenideElement passwordField;
    @FindBy(how = How.CLASS_NAME,using = "header__auth-link")
    private SelenideElement signInAndUpSwitchLink;
    @FindBy(how = How.CLASS_NAME,using = "auth-form__button")
    private SelenideElement signInAndUpButton;

    public PageObject setEmailField(String email) {
        emailField.setValue(email);
        return this;
    }
    public PageObject setPasswordField(String password) {
        passwordField.setValue(password);
        return this;
    }

    public PageObject signInAndUpSwitchLinkClick() {
        signInAndUpSwitchLink.click();
        return this;
    }

    public HomePageMestoSelenide signIn(String email, String password){
        this.setEmailField(email)
                .setPasswordField(password)
                .signInAndUpButton.click();
        HomePageMestoSelenide homePage = page(HomePageMestoSelenide.class);
        homePage.waitForLoadHomePage();
        return homePage;
    }

    public void signUp(String email, String password){
        this.signInAndUpSwitchLinkClick()
                .setEmailField(email)
                .setPasswordField(password)
                .signInAndUpButton.click();
    }
}

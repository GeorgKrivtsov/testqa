package testqa.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    private SelenideElement fldLoginEmail = $x("//input[@id='loginEmail']");
    private SelenideElement fldLoginPassword = $x("//input[@id='loginPassword']");
    private SelenideElement btnAuthButton = $x("//button[@id='authButton']");


    @Step("Проверка отображается ли поле 'E-Mail'")
    public boolean isFldLoginEmailDisplayed() {
        return fldLoginEmail.isDisplayed();
    }

    @Step("Проверка отображается ли поле 'Пароль'")
    public boolean isFldLoginPassword() {
        return fldLoginPassword.isDisplayed();
    }

    @Step("Ввод логина {0}")
    public LoginPage setFldLoginEmail(String email) {
        fldLoginEmail.setValue(email);
        return this;
    }

    @Step("Ввод пароля {0}")
    public LoginPage setFldLoginPassword(String password) {
        fldLoginPassword.setValue(password);
        return this;
    }

    @Step("Нажатие на кнопку вход")
    public void clickOnAuthButton() {
        btnAuthButton.click();
    }


}

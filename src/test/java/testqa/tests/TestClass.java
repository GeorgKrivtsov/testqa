package testqa.tests;

import com.codeborne.selenide.Selenide;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import testqa.core.BaseTest;
import testqa.pages.FormPage;
import testqa.pages.LoginPage;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestClass extends BaseTest {
    private LoginPage loginPage = new LoginPage();
    private FormPage formPage = new FormPage();

    @Test
    @Order(1)
    @DisplayName("Проверка формы авторизации")
    public void testLoginForm() {
        open("https://www.google.ru/");
        Selenide.webdriver().driver().getWebDriver().get("file:///" +getClass().getClassLoader().getResource("qa-test.html").getPath());

        loginPage
            .setFldLoginEmail("test@protei.ru")
            .setFldLoginPassword("test")
            .clickOnAuthButton();

        assertThat(formPage.isTableDisplayed())
                .as("Верификация не прошла - таблица не отобразилась").isTrue();

    }

    @Test
    @Order(2)
    @DisplayName("Проверка Анкеты")
    public void testForm() {

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(formPage.isFldLoginEmailDisplayed()).as("Поле Email не отобразилось").isTrue();
            softAssertions.assertThat(formPage.isFldDataNameDisplayed()).as("Поле Имя не отобразилось").isTrue();

            softAssertions.assertThat(formPage.isSelectGenderDisplayed()).as("Поле ввыбора пола не отобразилось").isTrue();

            softAssertions.assertThat(formPage.isBtnSendDisplayed()).as("Кнопка ввода не отобразилось").isTrue();

            softAssertions.assertThat(formPage.isTableDisplayed()).as("Таблица не отобразилось").isTrue();
        });

        formPage
                .setFldEmail("test@protei.ru")
                .setFldName("Имя")
                .selectGenderByName("Мужской")
                .selectDataCheckByName("Вариант 1.1")
                .selectDataSelectByName("Вариант 2.1")
                .clickOnBtnSend();

        assertThat(formPage.getTxtNotification()).as("").isEqualTo("Данные добавлены.");

        formPage.clickOnBtnOk();

        assertThat(formPage.getRowCount()).as("Таблица не содержит записей").isGreaterThanOrEqualTo(1);

        List<String> listRow = formPage.getListRowByIndex(0);

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(listRow.get(0)).as("Поле Email заполнилось не правильно").isEqualTo("test@protei.ru");
            softAssertions.assertThat(listRow.get(1)).as("Поле Имя заполнилось не правильно").isEqualTo("Имя");
            softAssertions.assertThat(listRow.get(2)).as("Поле Пол заполнилось не правильно").isEqualTo("Мужской");
            softAssertions.assertThat(listRow.get(3)).as("Поле Выбор 1 заполнилось не правильно").isEqualTo("1.1");
            softAssertions.assertThat(listRow.get(4)).as("Поле Выбор 2 заполнилось не правильно").isEqualTo("2.1");
        });


    }

}

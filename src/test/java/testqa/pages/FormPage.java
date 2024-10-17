package testqa.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import testqa.elements.Table;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class FormPage {

    private SelenideElement fldEmail = $x("//input[@id='dataEmail']");
    private SelenideElement fldName = $x("//input[@id='dataName']");

    private SelenideElement selectGender = $x("//select[@id='dataGender']");

    private ElementsCollection dataCheck = $$x("//input[contains(@id, 'dataCheck')]/..");
    private ElementsCollection dataSelect = $$x("//input[contains(@id, 'dataSelect')]/..");

    private SelenideElement btnSend = $x("//button[@id='dataSend']");

    private Table table = new Table($x("//table[@id='dataTable']"));

    private SelenideElement txtNotification = $x("//div[@class='uk-modal-dialog']//div[contains(@class, 'uk-margin uk-modal-content')]");
    private SelenideElement btnOk = $x("//div[@class='uk-modal-dialog']//button");


    @Step("Проверка отображается ли поле 'E-Mail'")
    public boolean isFldLoginEmailDisplayed() {
        return fldEmail.shouldBe(Condition.visible, Duration.ofSeconds(2)).isDisplayed();
    }

    @Step("Ввод почты {0}")
    public FormPage setFldEmail(String email) {
        fldEmail.setValue(email);
        return this;
    }

    @Step("Проверка отображается ли поле 'Имя'")
    public boolean isFldDataNameDisplayed() {
        return fldName.isDisplayed();
    }

    @Step("Ввод Имени {0}")
    public FormPage setFldName(String email) {
        fldName.setValue(email);
        return this;
    }

    @Step("Проверка отображается ли поле 'Имя'")
    public boolean isSelectGenderDisplayed() {
        return selectGender.isDisplayed();
    }

    @Step("Выбор пола {0}")
    public FormPage selectGenderByName(String gender) {
        selectGender.click();
//        ElementsCollection list = selectGender.$$x("option");
//        for(SelenideElement item : list) {
//            if(item.getText().equalsIgnoreCase(gender)){
//                item.click();
//                return this;
//            }
//        }
        selectGender.$$x("option").find(Condition.text(gender)).click();
        return this;
    }

    @Step("Сделать выбор {0}")
    public FormPage selectDataCheckByName (String txt) {
        dataCheck.find(Condition.text(txt)).$x("input").click();
        return this;
    }

    @Step("Сделать выбор {0}")
    public FormPage selectDataSelectByName (String txt) {
        dataSelect.find(Condition.text(txt)).$x("input").click();
        return this;
    }

    @Step("Проверка отображается ли кнопка Отправить")
    public boolean isBtnSendDisplayed() {
        return btnSend.isDisplayed();
    }

    @Step("Нажать на кнопку Отправить")
    public void clickOnBtnSend() {
        btnSend.click();
    }

    @Step("Проверка отображается ли таблица")
    public boolean isTableDisplayed() {
        return table.isTableDisplayed();
    }

    @Step("Проверка отображается ли заголовок столбца по названию")
    public boolean isColumnHeaderByNameDisplayed(String columnName) {
        return table.isColumnHeaderByNameExist(columnName);
    }

    @Step("Получить текст уведомления")
    public String getTxtNotification() {
        return txtNotification.shouldBe(Condition.visible, Duration.ofSeconds(2)).getText();
    }

    @Step("Нажать на кнопку ок")
    public void clickOnBtnOk() {
        btnOk.click();
    }

    @Step("Получить значение количества строк")
    public Integer getRowCount() {
        return table.getRowCount();
    }

    @Step("Получить список из столбцов строки")
    public List<String> getListRowByIndex (Integer index) {
        return table.getRowByIndex(index);
    }








}

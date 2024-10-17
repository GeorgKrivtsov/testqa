package testqa.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Table extends BaseElement {

    public Table (SelenideElement element) {
        super(element);
    }

    private SelenideElement thead = selement.$x("thead/tr");
    private SelenideElement tbody = selement.$x("tbody");

    public boolean isTableDisplayed() {
        return selement.isDisplayed();
    }

    public boolean isColumnHeaderByNameExist(String columnName) {
        return thead.$$x("/th").find(Condition.text(columnName)).exists();
    }

    public Integer getRowCount() {
        return tbody.$$x("tr").size();
    }

    public List<String> getRowByIndex(Integer index) {
        ElementsCollection rows = tbody.$$x("tr/td");
        List<String> list = new ArrayList<>();
        if(getRowCount() > 0) {
            for (SelenideElement column : rows) {
                list.add(column.getText());
            }
            return list;
        } else {
            Assertions.fail("Таблица пуста");
        }
        return null;
    }






}

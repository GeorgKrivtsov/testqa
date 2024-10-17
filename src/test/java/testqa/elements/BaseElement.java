package testqa.elements;

import com.codeborne.selenide.SelenideElement;

public class BaseElement {

    protected SelenideElement selement;

    public BaseElement (SelenideElement element) {
        this.selement = element;
    }

}

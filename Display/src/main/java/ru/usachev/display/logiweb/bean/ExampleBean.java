package ru.usachev.display.logiweb.bean;

import javax.inject.Named;

@Named
public class ExampleBean {
    private String text = "Example bean";

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

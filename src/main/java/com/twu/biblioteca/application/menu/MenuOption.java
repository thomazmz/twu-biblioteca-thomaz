package com.twu.biblioteca.application.menu;

public class MenuOption {

    private String sentence;

    private MenuOptionCommand commannd;

    public MenuOption(String sentence, MenuOptionCommand command) {
        this.sentence = sentence;
        this.commannd = command;
    }

    public void execute() {
        commannd.execute();
    }

    public String getSentence() {
        return sentence;
    }

    public interface MenuOptionCommand {
        void execute();
    }
}
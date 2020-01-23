package com.twu.biblioteca.application.menu;

public class MenuOption {

    private String sentence;

    private MenuOptionCommand commannd;

    public MenuOption(String sentence, MenuOptionCommand command) {
        this.sentence = sentence;
        this.commannd = command;
    }

    @Override
    public String toString() {
        return sentence;
    }

    public void execute() {
        commannd.execute();
    }

    public interface MenuOptionCommand {
        void execute();
    }
}
package com.twu.biblioteca.application.menu;

class MenuOption {

    private String sentence;

    private MenuCommand commannd;

    public MenuOption(final String sentence,
                         final MenuCommand command) {
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
}
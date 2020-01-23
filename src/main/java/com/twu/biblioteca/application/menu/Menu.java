package com.twu.biblioteca.application.menu;

import com.twu.biblioteca.application.ApplicationIO;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.twu.biblioteca.application.ApplicationIO.lineBreak;

public class Menu {

    private static final String invalidInputMessage = "Please, select a valid option!\n";

    private static final String optionDivider = ". ";

    private ApplicationIO applicationIO;

    private String instruction;

    private Map<String, MenuOption> options = new LinkedHashMap<>();

    public Menu(String instruction, ApplicationIO applicationIO) {
        this.applicationIO = applicationIO;
        this.instruction = instruction;
    }

    public void putOption(String selector, String sentence, MenuOption.MenuOptionCommand command) {
        MenuOption option = new MenuOption(sentence, command);
        this.putOption(selector, option);
    }

    public void putOption(String selector, MenuOption option) {
        options.put(selector, option);
    }

    public void render() {
        applicationIO.print(this.toString());
        String selectedKey = applicationIO.read();
        if(options.containsKey(selectedKey)) {
            MenuOption selectedOption = options.get(selectedKey);
            selectedOption.execute();
        } else {
            applicationIO.print(invalidInputMessage);
            applicationIO.read();
        }
    }

    @Override
    public String toString() {
        return this.instruction + lineBreak + this.getOptionsString();
    }

    private String getOptionsString() {
        return options.keySet().stream().reduce("",  this::concatOptionsString);
    }

    private String concatOptionsString(String optionsString, String optionKey) {
        return optionsString + optionKey + optionDivider + options.get(optionKey) + lineBreak;
    }
}
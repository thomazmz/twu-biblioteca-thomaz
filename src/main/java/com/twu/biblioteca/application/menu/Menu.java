package com.twu.biblioteca.application.menu;

import com.twu.biblioteca.application.ApplicationIO;

import java.security.InvalidParameterException;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.twu.biblioteca.application.ApplicationIO.isValidString;
import static com.twu.biblioteca.application.ApplicationIO.lineBreak;

public class Menu {

    public ApplicationIO applicationIO;

    public String header;

    private Map<String, MenuOption> options = new LinkedHashMap<>();

    public Menu(String header, ApplicationIO applicationIO) {
        this.header = header;
        this.applicationIO = applicationIO;
    }

    public void putOption(String selector, String sentence, MenuCommand command) throws InvalidParameterException {
            MenuOption option = new MenuOption(sentence, command);
            this.putOption(selector, option);
    }

    public void putOption(String selector, MenuOption menuOption) throws InvalidParameterException {
        if(isValidString(selector)) {
            options.put(selector, menuOption);
        } else {
            throw new InvalidParameterException();
        }
    }

    public void render() {
        applicationIO.print(this.toString());
        readInput();
    }

    private void readInput() {
        String selectedKey = applicationIO.readString();
        if(options.containsKey(selectedKey)) {
            MenuOption selectedOption = options.get(selectedKey);
            selectedOption.execute();
        } else {
            applicationIO.print("Invalid input.\n");
            readInput();
        }
    }

    @Override
    public String toString() {
        String optionsString = options.keySet().stream().reduce("", this::concatOptionsString);
        return  this.header + lineBreak + optionsString;
    }

    private String concatOptionsString(String optionsString, String optionKey) {
        return optionsString + optionKey + ". " + options.get(optionKey) + lineBreak;
    }
}
package com.twu.biblioteca.application.menu;

import com.twu.biblioteca.application.ApplicationIO;

import java.util.LinkedHashMap;
import java.util.Map;

public class Menu {

    private static final String invalidInputMessage = "Please, select a valid option!\n";

    private static final String lineBreak = "\n";

    private ApplicationIO io;

    private String instruction;

    private Map<String, MenuOption> options = new LinkedHashMap<>();

    public Menu(String instruction, ApplicationIO io) {
        this.instruction = instruction;
        this.io = io;
    }

    public void putOption(String selector, String sentence, MenuOption.MenuOptionCommand command) {
        MenuOption option = new MenuOption(sentence, command);
        this.putOption(selector, option);
    }

    public void putOption(String selector, MenuOption option) {
        options.put(selector, option);
    }

    public void print() {
        printMenuInstruction();
        printOptions();
        readOptionInput();
    }

    private void printMenuInstruction() {
        io.print(instruction + lineBreak);
    }

    private void printOptions() {
        options.keySet().forEach(key -> {
            io.print(key + ". " + options.get(key).getSentence() + lineBreak);
        });
    }

    private void readOptionInput() {
        String selectedKey = io.read();
        if(options.containsKey(selectedKey)) {
            MenuOption selectedOption = options.get(selectedKey);
            selectedOption.execute();
        } else {
            io.print(invalidInputMessage);
            readOptionInput();
        }
    }
}

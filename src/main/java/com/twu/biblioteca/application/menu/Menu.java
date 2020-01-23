package com.twu.biblioteca.application.menu;

import com.twu.biblioteca.application.ApplicationInterface;
import com.twu.biblioteca.application.ApplicationInterfaceComponent;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.twu.biblioteca.application.ApplicationInterface.lineBreak;

public class Menu extends ApplicationInterfaceComponent {

    private static final String invalidInputMessage = "Please, select a valid option!\n";
    private static final String optionDivider = ". ";

    private String instruction;

    private Map<String, MenuOption> options = new LinkedHashMap<>();

    public Menu(String instruction, ApplicationInterface applicationInterface) {
        super(applicationInterface);
        this.instruction = instruction;
    }

    public void putOption(String selector, String sentence, MenuOption.MenuOptionCommand command) {
        MenuOption option = new MenuOption(sentence, command);
        this.putOption(selector, option);
    }

    public void putOption(String selector, MenuOption option) {
        options.put(selector, option);
    }

    @Override
    public void render() {
        applicationInterface.print(this.toString());
        String selectedKey = applicationInterface.read();
        if(options.containsKey(selectedKey)) {
            MenuOption selectedOption = options.get(selectedKey);
            selectedOption.execute();
        } else {
            applicationInterface.print(invalidInputMessage);
            applicationInterface.read();
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
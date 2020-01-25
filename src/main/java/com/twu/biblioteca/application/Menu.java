package com.twu.biblioteca.application;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static com.twu.biblioteca.application.ApplicationIO.LINE_BREAK;

public class Menu {

    public String header;
    private Map<String, Option> options;

    public Menu(String header) {
        this.header = header;
        options = new LinkedHashMap<>();
    }

    public void setOption(String selector, String sentence, Command command) {
        Option option = new Option(sentence, command);
        options.put(selector, option);
    }

    public Optional<Option> select(String key) {
            return Optional.ofNullable(options.get(key));
    }

    @Override
    public String toString() {
        return LINE_BREAK + header + LINE_BREAK + buildOptionsString();
    }

    private String buildOptionsString() {
        return options.keySet().stream().reduce("", this::buildOptionString);
    }

    private String buildOptionString(String string, String key) {
        return string.concat(String.format("%-3s", key) + options.get(key) + LINE_BREAK);
    }

    public static class Option {

        private String sentence;
        private Command command;

        public Option(String sentence, Command command) {
            this.sentence = sentence;
            this.command = command;
        }

        @Override
        public String toString() {
            return sentence;
        }

        public void execute() {
            command.execute();
        }
    }

    public interface Command {
        void execute();
    }
}
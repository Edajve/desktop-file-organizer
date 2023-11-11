package org.example.src.operations.flagOperations;

import java.util.ArrayList;
import java.util.List;

public class Rename {
    private List<String> arguments;

    public Rename(List<String> arguments) {
        this.arguments = arguments.subList(1, arguments.size()); // take off the -rename or -r flag as its redundant
    }

    public void setArguments(List<String> arguments) {
        this.arguments = arguments;
    }

    public List<String> getArguments() {
        return arguments;
    }

    public void execute() {
        System.out.println(getArguments());
    }
}

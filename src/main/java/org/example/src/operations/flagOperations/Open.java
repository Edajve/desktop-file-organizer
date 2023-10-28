package org.example.src.operations.flagOperations;

import java.util.List;

public class Open {
    private List<String> arguments;

    public Open(List<String> arguments) {
        this.arguments = arguments;
    }

    public List<String> getArguments() {
        return arguments;
    }

    public void setArguments(List<String> arguments) {
        this.arguments = arguments;
    }
}

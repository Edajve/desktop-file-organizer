package org.example.src.operations.flagOperations;

import java.util.List;

public class Delete {
    final private List<String> arguments;

    public List<String> getArguments() {
        return this.arguments;
    }

    public void deleteIndexFromArguments(int index) {
        this.arguments.remove(index);
    }

    public Delete(List<String> arguments) {
        this.arguments = arguments;
    }

    public void execute() {

    }
}

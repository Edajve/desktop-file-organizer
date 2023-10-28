package org.example.src.operations.flagOperations;

import org.example.src.operations.FileOperations;

import java.awt.Desktop;

import java.util.List;

/**
 * This class opens a file if it specifies the file name and the application
 * that it wants to be opened.
 */
public class Open {
    private List<String> arguments;
    final private FileOperations fileOperations;


    public Open(List<String> arguments) {
        this.arguments = arguments;
        this.fileOperations = new FileOperations();
    }

    public List<String> getArguments() {
        return arguments;
    }

    public void setArguments(List<String> arguments) {
        this.arguments = arguments;
    }
}

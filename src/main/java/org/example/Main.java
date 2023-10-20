package org.example;

import org.example.src.Scanners.Desktop;
import org.example.src.operations.FileOperations;
import org.example.src.utils.Utility;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Desktop desktop = new Desktop(new FileOperations(new Utility()));
        desktop.pollDesktop();
    }
}
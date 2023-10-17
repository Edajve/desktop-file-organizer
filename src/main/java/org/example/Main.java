package org.example;

import org.example.src.Scanners.Desktop;
import org.example.src.Scanners.FileStructure;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Desktop desktop = new Desktop(new FileStructure());
        desktop.pollDesktop();
    }
}
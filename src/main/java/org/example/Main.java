package org.example;

import org.example.src.Scanners.Desktop;
import org.example.src.Scanners.FileStructure;

public class Main {
    public static void main(String[] args) {
        Desktop desktop = new Desktop(new FileStructure());
        desktop.pollDesktop();

//        FileStructure.createJsonFromFileStructure();
    }
}
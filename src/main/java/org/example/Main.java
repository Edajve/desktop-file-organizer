package org.example;

import org.example.src.Scanners.Desktop;
import org.example.src.Scanners.FileStructure;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
//        Desktop desktop = new Desktop();
//        desktop.scanDesktop();

        FileStructure fileStructure = new FileStructure();
        fileStructure.scanFileStructure();
//        System.out.println(fileStructure.getDirectoryStructure());

        // this is to move a file from one place to another
//        Path targetFile = Paths.get("/Users/dajve.echols/Desktop/targetVIdeo.png");
//        Path targetDestination = Paths.get("/Users/dajve.echols/Desktop/test_folder/targetVIdeo.png");
//        scan.moveFolder(targetFile, targetDestination);
    }
}
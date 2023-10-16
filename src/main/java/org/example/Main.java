package org.example;

import org.example.src.constants.DirectoryPaths;
import org.example.src.structure.Directory;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Path basePath = Paths.get(DirectoryPaths.ROOT_DIRECTORY);
        Directory directory = new Directory("01-vivid-seats", 0);
        File file = new File(String.valueOf(basePath));
        System.out.println( directory.buildDirectoryTree(file, 0));
    }
}

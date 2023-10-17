package org.example;

import org.example.src.Scanners.Desktop;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Desktop scan = new Desktop();
        scan.scanDesktop();

        // this is to move a file from one place to another
//        Path targetFile = Paths.get("/Users/dajve.echols/Desktop/targetVIdeo.png");
//        Path targetDestination = Paths.get("/Users/dajve.echols/Desktop/test_folder/targetVIdeo.png");
//
//        scan.moveFolder(targetFile, targetDestination);


        // this builds the current directory
//        Path rootDirectory = Paths.get(DirectoryPaths.ROOT_DIRECTORY);
//        List<String> directoriesToOmit = Collections.singletonList(Ignore.DirectoryName.CODE);
//
//        Map<String, Object> dirStructure = Builder.buildFileStructure(rootDirectory, directoriesToOmit, rootDirectory);
//
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        String jsonStructure = gson.toJson(dirStructure);
//        System.out.println(jsonStructure);
    }
}
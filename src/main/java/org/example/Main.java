package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.src.Scanners.DesktopScan;
import org.example.src.constants.DirectoryPaths;
import org.example.src.constants.Ignore;
import org.example.src.structure.Builder;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        DesktopScan scan = new DesktopScan();
        scan.scanDesktop();
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
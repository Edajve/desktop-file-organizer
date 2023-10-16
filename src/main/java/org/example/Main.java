package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.src.constants.DirectoryPaths;
import org.example.src.constants.Ignore;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.example.src.structure.Directory.directoryToMap;

public class Main {
    public static void main(String[] args) throws IOException {
        Path startDir = Paths.get(DirectoryPaths.ROOT_DIRECTORY);
        List<String> directoriesToOmit = Collections.singletonList(Ignore.DirectoryName.CODE);

        Map<String, Object> dirStructure = directoryToMap(startDir, directoriesToOmit, startDir);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonStructure = gson.toJson(dirStructure);
        System.out.println(jsonStructure);
    }
}
package org.example.src.Scanners;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.src.constants.DirectoryPaths;
import org.example.src.constants.Ignore;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileStructure {
    private static final Logger logger = LogManager.getLogger(Desktop.class);

    private JsonElement directoryStructure;

    public JsonElement getDirectoryStructure() {
        return directoryStructure;
    }

    public void scanFileStructure() throws IOException {
        logger.info("Scanning file system...");
        Path rootDirectory = Paths.get(DirectoryPaths.ROOT_DIRECTORY);
        List<String> directoriesToOmit = Collections.singletonList(Ignore.DirectoryName.CODE);
        logger.info("Building Json from file structure from path '" + rootDirectory + "'");
        Map<String, Object> dirStructure = createJsonFromFileStructure(rootDirectory, directoriesToOmit, rootDirectory);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        this.directoryStructure = gson.toJsonTree(dirStructure);
    }

    /**
     * @param dir               the directory where you want to start from
     * @param directoriesToOmit name of any directory you don't want included
     * @param root              the directory you want to start the relative path from
     * @return a json structure of your file system, along with the relative path of every directory/subdirectory starting from root directory
     */
    public static Map<String, Object> createJsonFromFileStructure(Path dir, List<String> directoriesToOmit, Path root) throws IOException {
        Map<String, Object> dirMap = new HashMap<>();
        Map<String, Object> subDirs = new HashMap<>();

        dirMap.put("path", root.relativize(dir).toString());

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path path : stream) {
                if (Files.isDirectory(path) && !directoriesToOmit.contains(path.getFileName().toString())) {
                    subDirs.put(path.getFileName().toString(), createJsonFromFileStructure(path, directoriesToOmit, root));
                }
            }
        }

        if (!subDirs.isEmpty()) {
            dirMap.put("subdirectories", subDirs);
        }

        return dirMap;
    }
}

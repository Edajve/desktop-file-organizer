package org.example.src.Scanners;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.src.constants.DirectoryPaths;
import org.example.src.constants.Ignore;
import org.example.src.constants.KeyWords;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

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
        Map<String, Object> dirStructure = createJsonFromFileStructure(rootDirectory, directoriesToOmit, rootDirectory, KeyWords.generateKeywordToPathMapping());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        this.directoryStructure = gson.toJsonTree(dirStructure);
    }

    /**
     * @param dir               the directory where you want to start from
     * @param directoriesToOmit name of any directory you don't want included
     * @param root              the directory you want to start the relative path from
     * @return a json structure of your file system, along with the relative path of every directory/subdirectory starting from root directory
     * @Overloaded method
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

    /**
     * Overloaded method
     *
     * @param dir               the directory where you want to start from
     * @param directoriesToOmit name of any directory you don't want included
     * @param root              the directory you want to start the relative path from
     * @param keywordToPath     this is a map of all the keywords with its associated path
     * @return a json structure of your file system, along with the relative path of every directory/subdirectory starting from root directory
     */
    public static Map<String, Object> createJsonFromFileStructure(Path dir, List<String> directoriesToOmit, Path root, Map<String, String> keywordToPath) throws IOException {
        Map<String, Object> dirMap = new HashMap<>();
        Map<String, Object> subDirs = new HashMap<>();

        String relativePath = root.relativize(dir).toString();
        dirMap.put("path", relativePath);

        // Check if the current relative path matches any value in the keyword-to-path map
        String keyword = getKeyByValue(keywordToPath, relativePath);
        if (keyword != null) {
            dirMap.put("keyword", keyword);
        }

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path path : stream) {
                if (Files.isDirectory(path) && !directoriesToOmit.contains(path.getFileName().toString())) {
                    subDirs.put(path.getFileName().toString(), createJsonFromFileStructure(path, directoriesToOmit, root, keywordToPath));
                }
            }
        }

        if (!subDirs.isEmpty()) {
            dirMap.put("subdirectories", subDirs);
        }
        return dirMap;
    }

    // Utility method to get a key by its value in a map
    private static <K, V> K getKeyByValue(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (Objects.equals(entry.getValue(), value)) {
                return entry.getKey();
            }
        }
        return null;
    }
}

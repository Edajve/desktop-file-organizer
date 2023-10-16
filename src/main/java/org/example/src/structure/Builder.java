package org.example.src.structure;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Builder {
    /**
     *
     * @param dir the directory where you want to start from
     * @param directoriesToOmit name of any directory you don't want included
     * @param root the directory you want to start the relative path from
     * @return a json structure of your file system, along with the relative path of every directory/subdirectory starting from root directory
     * @throws IOException
     */
    public static Map<String, Object> buildFileStructure(Path dir, List<String> directoriesToOmit, Path root) throws IOException {
        Map<String, Object> dirMap = new HashMap<>();
        Map<String, Object> subDirs = new HashMap<>();

        dirMap.put("path", root.relativize(dir).toString());

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path path : stream) {
                if (Files.isDirectory(path) && !directoriesToOmit.contains(path.getFileName().toString())) {
                    subDirs.put(path.getFileName().toString(), buildFileStructure(path, directoriesToOmit, root));
                }
            }
        }

        if (!subDirs.isEmpty()) {
            dirMap.put("subdirectories", subDirs);
        }

        return dirMap;
    }
}

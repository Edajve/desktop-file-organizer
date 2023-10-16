package org.example.src.structure;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Directory {
    private final String name;
    private final int level;
    private final List<Directory> subdirectories;

    public Directory(String name, int level) {
        this.name = name;
        this.level = level;
        this.subdirectories = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public List<Directory> getSubdirectories() {
        return subdirectories;
    }

    public void addSubdirectory(Directory dir) {
        subdirectories.add(dir);
    }

    public static Map<String, Object> directoryToMap(Path dir, List<String> directoriesToOmit, Path root) throws IOException {
        Map<String, Object> dirMap = new HashMap<>();
        Map<String, Object> subDirs = new HashMap<>();

        dirMap.put("path", root.relativize(dir).toString());

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path path : stream) {
                if (Files.isDirectory(path) && !directoriesToOmit.contains(path.getFileName().toString())) {
                    subDirs.put(path.getFileName().toString(), directoryToMap(path, directoriesToOmit, root));
                }
            }
        }

        if (!subDirs.isEmpty()) {
            dirMap.put("subdirectories", subDirs);
        }

        return dirMap;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append("--");
        }
        sb.append(name).append("\n");
        for (Directory dir : subdirectories) {
            sb.append(dir.toString());
        }
        return sb.toString();
    }
}

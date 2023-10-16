package org.example.src.structure;

import org.example.src.constants.Ignore;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Directory {
    private final String name;
    private final int level;
    private final List<Directory> subdirectories;

    public Directory(String name, int level) {
        this.name = name;
        this.level = level;
        this.subdirectories = new ArrayList<>();
    }

    public Directory(String name) {
        this.name = name;
        this.level = 2;
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

    public Directory buildDirectoryTree(File rootFile, int level) {
        Directory directory = new Directory(rootFile.getName(), level);

        for (File file : rootFile.listFiles()) {
            if (file.isDirectory() && !file.getName().equals(Ignore.DirectoryName.CODE)) {
                directory.addSubdirectory(buildDirectoryTree(file, level + 1));
            }
        }

        return directory;
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

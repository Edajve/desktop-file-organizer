package org.example.src.structure;

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

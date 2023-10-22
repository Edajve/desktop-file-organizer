package org.example.src.utils;

import java.io.File;

public class Utility {
    public String extractKeywordFromFileName(File file) {
        return file.getName().split("-")[0];
    }

    public String stripKeyWord(String fileName) {
        return fileName.split("-")[1];
    }
}

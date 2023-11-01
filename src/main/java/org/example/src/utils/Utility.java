package org.example.src.utils;

import java.io.File;

public class Utility {
    public String extractKeywordFromFileName(File file) {
        return file.getName().split("-")[0];
    }

    public String stripKeyWord(String fileName) {
        String[] arrayOfNames = fileName.split("-");
        StringBuilder stringBuilder = new StringBuilder();

        if (arrayOfNames.length > 2) {
            for (int i = 1; i < arrayOfNames.length; i++) {
                if (i == arrayOfNames.length - 1) stringBuilder.append(arrayOfNames[i]);
                else {
                    stringBuilder.append(arrayOfNames[i]);
                    stringBuilder.append("-");
                }
            }
        } else {
            String[] splitWithSpaces = arrayOfNames[1].split(" ");
            for (int i = 0; i < splitWithSpaces.length; i++) {
                if (i == splitWithSpaces.length - 1) stringBuilder.append(splitWithSpaces[i]);
                else {
                    stringBuilder.append(splitWithSpaces[i]);
                    stringBuilder.append("-");
                }
            }
        }

        return stringBuilder.toString();
    }
}

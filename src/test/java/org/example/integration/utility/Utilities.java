package org.example.integration.utility;

import org.example.src.constants.PathConstants;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Utilities {
    public static void createFilesInTestDirectory(List<String> fileNames) throws IOException {
        for (String file : fileNames) {
            File f = new File(PathConstants.TEST_DIRECTORY_PATH + File.separator + file);
            System.out.println(f);
            boolean isFileCreated = f.createNewFile();
            if (isFileCreated) System.out.println("File created successfully at path " + f);
            else System.out.println("File NOT created successfully because it has already been created");
        }
    }

    public static void cleanOutTestDirectory() {
        File file = new File(PathConstants.TEST_DIRECTORY_PATH);
        File[] files = file.listFiles();
        assert files != null;
        System.out.println("Cleaning out test directory --------");
        for (File f : files) {
            boolean isFileDeleted = f.delete();
            if (isFileDeleted) System.out.println("deleted file" + f);
        }
    }
}

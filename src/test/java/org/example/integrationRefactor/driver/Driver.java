package org.example.integrationRefactor.driver;

import org.example.Main;
import org.example.src.constants.PathConstants;

import java.io.File;
import java.io.IOException;
import java.io.PipedInputStream;
import java.util.List;

public class Driver {
    private static Thread mainThread;

    /**
     * adds file under test directory
     */
    public static void createFile(List<String> fileNames) throws IOException {
        for (String file : fileNames) {
            File f = new File(PathConstants.TEST_DIRECTORY_PATH + File.separator + file);
            System.out.println(f);
            boolean isFileCreated = f.createNewFile();
            if (isFileCreated) System.out.println("File created successfully at path " + f);
            else System.out.println("File NOT created successfully because it has already been created");
        }
    }

    public static void cleanOutTestDirectory() {
        File directory = new File(PathConstants.TEST_DIRECTORY_PATH);
        System.out.println("Cleaning out test directory --------");
        deleteRecursively(directory);
    }

    private static void deleteRecursively(File file) {
        if (file.isDirectory()) {
            // Get all files and directories within this directory
            File[] files = file.listFiles();
            if (files != null) {
                for (File subfile : files) {
                    deleteRecursively(subfile);
                }
            }
        }
        // Now delete the file or empty directory
        boolean isDeleted = file.delete();
        if (isDeleted) {
            System.out.println("Deleted: " + file.getPath());
        } else {
            System.err.println("Could not delete: " + file.getPath());
        }
    }

    public static void runSystemUnderTest() throws InterruptedException {
        Thread.sleep(2000);
        mainThread = new Thread(() -> {
            Main.entry(new String[]{PathConstants.TEST_DIRECTORY_PATH}, null);
        });
        mainThread.start();

        try {
            // Delay for 2 seconds to allow the Main program to process
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Interrupted while waiting for file processing");
        }
    }

    public static void runSystemUnderTest(PipedInputStream pipedIn) throws InterruptedException {
        Thread.sleep(2000);
        mainThread = new Thread(() -> {
            Main.entry(new String[]{PathConstants.TEST_DIRECTORY_PATH}, pipedIn);
        });
        mainThread.start();

        try {
            // Delay for 2 seconds to allow the Main program to process
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Interrupted while waiting for file processing");
        }
    }

    public static File[] getAllFilesFromTestDirectory() {
        return new File(PathConstants.TEST_DIRECTORY_PATH).listFiles();
    }

    public static void createTestDirectory(String directoryName) {
        String testDirectoryPath = PathConstants.TEST_DIRECTORY_PATH + File.separator + directoryName;
        File file = new File(testDirectoryPath);
        boolean wasDirectoryMade = file.mkdir();
        if (wasDirectoryMade) System.out.println("directory: " + directoryName + "was made");
        else System.out.println("directory: " + directoryName + " was made not made because it is already made");
    }
}

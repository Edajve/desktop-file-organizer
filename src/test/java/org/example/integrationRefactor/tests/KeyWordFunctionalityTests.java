package org.example.integrationRefactor.tests;

import org.example.integrationRefactor.driver.BaseTest;
import org.example.integrationRefactor.driver.Driver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KeyWordFunctionalityTests extends BaseTest {
    @Test
    @DisplayName("Basic test for trash keyword deleting files")
    public void testing_trash_prefix() throws IOException, InterruptedException {
        setUp();
        Driver.createFile(Collections.singletonList("trash-test-file-one"));
        Driver.runSystemUnderTest();

        boolean listContainsTestFile = Arrays.stream(Driver.getAllFilesFromTestDirectory()).anyMatch(
                file -> file.getName().contains("trash-test-file-one")
        );
        assertFalse(listContainsTestFile);
        cleanUp();
    }

    @Test
    @DisplayName("Basic test for move keyword deleting files")
    public void testing_move_prefix() throws IOException, InterruptedException {
        setUp();
        Driver.createTestDirectory("move-test-directory");
        Driver.createFile(Collections.singletonList("testMoveKeyWord-test-file-one"));
        Driver.runSystemUnderTest();

        boolean wasFileSuccessfullyMoved = false;
        File[] allFiles = Driver.getAllFilesFromTestDirectory();

        for (File file : allFiles) {
            if (file.isDirectory() && file.getName().equals("move-test-directory")) {
                File[] filesInDirectory = file.listFiles();

                if (filesInDirectory != null) {
                    for (File singleFile : filesInDirectory) {
                        if (singleFile.getName().equals("test-file-one")) {
                            wasFileSuccessfullyMoved = true;
                            break; // No need to check further files
                        }
                    }
                }

                if (wasFileSuccessfullyMoved) break;
            }
        }

        // Assert that the file was successfully moved
        assertTrue(wasFileSuccessfullyMoved);
        cleanUp();
    }
}

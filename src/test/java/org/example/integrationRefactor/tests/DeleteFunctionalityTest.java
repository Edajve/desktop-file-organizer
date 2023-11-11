package org.example.integrationRefactor.tests;

import org.example.integrationRefactor.driver.BaseTest;
import org.example.integrationRefactor.driver.Driver;
import org.example.integrationRefactor.driver.SystemUnderTestRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeleteFunctionalityTest extends BaseTest {

    private SystemUnderTestRunner runner;

    @Test
    @DisplayName("Passing -d and then the file name should delete that file")
    public void testing_delete_prefix() throws IOException, InterruptedException {
        setUp();
        Driver.createFile(Collections.singletonList("file-to-delete-testie"));

        runner = new SystemUnderTestRunner();
        runner.runSystemUnderTest();

        Thread.sleep(1200);

        runner.sendCommandToSystemUnderTest("-d file-to-delete-testie");

        Thread.sleep(1000);

        Arrays.stream(Driver.getAllFilesFromTestDirectory()).forEach(
                file -> System.out.println(file.getName())
        );

        boolean isFileStillThere =
                Arrays.stream(Driver.getAllFilesFromTestDirectory()).anyMatch(file -> file.getName().equals("file-to-delete"));
        assertFalse(isFileStillThere);

        Thread.sleep(1000);
        cleanUp();
    }

    @Test
    @DisplayName("Passing -d all should delete all files")
    public void testing_delete_all_prefix() throws IOException, InterruptedException {
        setUp();
        Driver.createFile(Collections.singletonList("file-to-delete-testie"));
        Driver.createFile(Collections.singletonList("file-to-delete-testie2"));

        runner = new SystemUnderTestRunner();
        runner.runSystemUnderTest();

        Thread.sleep(1200);

        runner.sendCommandToSystemUnderTest("-d allTest");

        Thread.sleep(1000);

        Arrays.stream(Driver.getAllFilesFromTestDirectory()).forEach(
                file -> System.out.println(file.getName())
        );

        boolean didBothDelete = false;
        File[] allFilesFromTestDirectory = Driver.getAllFilesFromTestDirectory();
        int length = allFilesFromTestDirectory.length;
        assertEquals(0, length);
        Thread.sleep(1000);
        cleanUp();
    }
}

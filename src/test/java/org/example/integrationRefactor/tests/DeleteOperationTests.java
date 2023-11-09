package org.example.integrationRefactor.tests;

import org.example.integrationRefactor.driver.BaseTest;
import org.example.integrationRefactor.driver.Driver;
import org.example.integrationRefactor.driver.SystemUnderTestRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeleteOperationTests extends BaseTest {

    private SystemUnderTestRunner runner;

    @Test
    @DisplayName("Passing -d and then the file name should delete that file")
    public void testing_trash_prefix() throws IOException, InterruptedException {
        setUp();
        Driver.createFilesInTestDirectory(Collections.singletonList("file-to-delete-testie"));

        // Initialize the runner
        runner = new SystemUnderTestRunner();
        runner.runSystemUnderTest();

        Thread.sleep(1200);

        // Send the delete command to the running system
        runner.sendCommandToSystemUnderTest("-d file-to-delete-testie");

        // Wait for the system to process the command
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
}

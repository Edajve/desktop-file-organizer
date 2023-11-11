package org.example.integrationRefactor.tests;

import org.example.integrationRefactor.driver.BaseTest;
import org.example.integrationRefactor.driver.Driver;
import org.example.integrationRefactor.driver.SystemUnderTestRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;

public class RemoveFunctionalityTest extends BaseTest {
    @Test
    @DisplayName("Passing -r with a file and new name to name the file" +
            ", renames the file")
    public void testing_renaming_a_file() throws IOException, InterruptedException {
        setUp();
        Driver.createFile(Collections.singletonList("jeff"));

        SystemUnderTestRunner runner = new SystemUnderTestRunner();
        runner.runSystemUnderTest();

        Thread.sleep(1200);

        runner.sendCommandToSystemUnderTest("-r test jeff jeff-renamed");

        Thread.sleep(1000);
        cleanUp();
    }

    @Test
    @DisplayName("Passing -r with a file and new name to name the file then a file with no rename should end operation")
    public void testing_file_with_no_rename_argument() throws IOException, InterruptedException {
        setUp();
        Driver.createFile(Collections.singletonList("jeff"));
        Driver.createFile(Collections.singletonList("randomFile"));

        SystemUnderTestRunner runner = new SystemUnderTestRunner();
        runner.runSystemUnderTest();

        Thread.sleep(1200);

        runner.sendCommandToSystemUnderTest("-r test jeff jeff-renamed randomFile");

        Thread.sleep(1000);
        cleanUp();
    }
}

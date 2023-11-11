package org.example.integrationRefactor.tests;

import org.example.integrationRefactor.driver.BaseTest;
import org.example.integrationRefactor.driver.Driver;
import org.example.integrationRefactor.driver.SystemUnderTestRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;

public class MoveTest extends BaseTest {

    @Test
    @DisplayName("Passing -m with a file and a keyword, moves that file to a path based on they keyword")
    public void testing_move_argument() throws IOException, InterruptedException {
        setUp();
        Driver.createTestDirectory("mover-test");
        Driver.createFile(Collections.singletonList("lala"));

        SystemUnderTestRunner runner = new SystemUnderTestRunner();
        runner.runSystemUnderTest();

        Thread.sleep(1200);

        runner.sendCommandToSystemUnderTest("-m test lala aaa");

        Thread.sleep(1000);
        cleanUp();
    }

    @Test
    @DisplayName("Passing -m with a file and a keyword, moves that file to a path based on they keyword")
    public void testing_move_argument_with_no_keyword() throws IOException, InterruptedException {
        setUp();
        Driver.createTestDirectory("mover-test");
        Driver.createFile(Collections.singletonList("lala"));
        Driver.createFile(Collections.singletonList("lala-2"));

        SystemUnderTestRunner runner = new SystemUnderTestRunner();
        runner.runSystemUnderTest();

        Thread.sleep(1200);

        runner.sendCommandToSystemUnderTest("-m test lala aaa lala-2");

        Thread.sleep(1000);
        cleanUp();
    }
}

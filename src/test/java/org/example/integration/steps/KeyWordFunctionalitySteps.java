package org.example.integration.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Main;
import org.example.integration.utility.Utilities;
import org.example.src.constants.PathConstants;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class KeyWordFunctionalitySteps {

    private Thread mainThread;

    @Given("there is a file called {string}")
    public void thereIsAFileCalled(String fileName) throws IOException {
        Utilities.createFilesInTestDirectory(Collections.singletonList(fileName));

        File[] allFilesFromTestDirectory = Utilities.getAllFilesFromTestDirectory();
        boolean listContainsTestFile = Arrays.stream(
                allFilesFromTestDirectory).anyMatch(file -> file.getName().contains(fileName)
        );

        // verify we successfully added the test file
        assertAll("Assert the set up file is in the test directory",
                () -> assertTrue(listContainsTestFile, "File " + fileName + " was not correctly made")
        );
    }

    @When("the program is ran")
    public void theProgramIsRan() throws InterruptedException {
        Thread.sleep(2000);
        mainThread = new Thread(() -> {
            try {
                Main.main(new String[]{PathConstants.TEST_DIRECTORY_PATH});
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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

    @Then("the file {string} should be deleted")
    public void theFileShouldBeDeleted(String fileName) {
        boolean actual = true;
        boolean expected = true;
        for (File file : Utilities.getAllFilesFromTestDirectory()) {
            if (file.getName().equals(fileName)) {
                actual = false;
                break;
            }
        }

        assertEquals(expected, actual);
    }


    @Then("clean up files")
    public void clean_up() throws InterruptedException {
        Main.stopProgram();

        if (mainThread != null) {
            mainThread.join();
        }

        Utilities.cleanOutTestDirectory();
    }


    @Then("shut down program")
    public void shutDownProgram() throws InterruptedException {
        if (mainThread != null && mainThread.isAlive()) {
            Main.stopProgram();
            mainThread.join(); // Wait for the main program to finish
            assertTrue(true);
        }
    }
}

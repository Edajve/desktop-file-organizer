package org.example.integration.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Main;
import org.example.integration.utility.Utilities;
import org.example.src.constants.PathConstants;

import java.io.IOException;
import java.util.Collections;

public class KeyWordFunctionalitySteps {

    private Thread mainThread;

    @Given("there is a file called {string}")
    public void thereIsAFileCalled(String fileName) throws IOException {
        Utilities.createFilesInTestDirectory(Collections.singletonList(fileName));
    }

    @When("the program is ran")
    public void theProgramIsRan() {
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
        // validate the files have been deleted
    }


    @Then("clean up files")
    public void clean_up() {
        Utilities.cleanOutTestDirectory();
    }

    @Then("shut down program")
    public void shutDownProgram() throws InterruptedException {
        if (mainThread != null && mainThread.isAlive()) {
            Main.stopProgram();
            mainThread.join(); // Wait for the main program to finish
        }
    }
}

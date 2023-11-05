package org.example.integration.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.integration.utility.Utilities;

import java.io.IOException;
import java.util.Collections;

public class KeyWordFunctionalitySteps {

    @Given("there is a file called {string}")
    public void thereIsAFileCalled(String fileName) throws IOException {
        Utilities.createFilesOnDesktop(Collections.singletonList(fileName));
    }

    @When("program is ran the file {string} should be deleted")
    public void programIsRanTheFileShouldBeDeleted(String arg0) {

    }

    @Then("clean up files")
    public void clean_up() {
        // Write code here that turns the phrase above into concrete actions
        Utilities.cleanOutTestDirectory();
    }
}

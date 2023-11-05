package org.example.integration.steps;

import io.cucumber.java.en.Given;

public class BackGroundSteps {
    @Given("set up test")
    public static void set_up_test() {
        // Set up
        System.out.println("Set up here-------");
    }
}

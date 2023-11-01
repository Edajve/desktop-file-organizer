package org.example.integration.steps;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.integration.mock.Mock;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FakeTestSteps {
    private static Map<String, Object> scenarioContext;

    @Before
    public static void before_all() {
        scenarioContext = new HashMap<>();
    }

    @After
    public static void after_all() {
        scenarioContext.clear();
    }

    @Given("we have {string} and {string}")
    public void we_have_and(String string, String string2) {
        int firstNum = Integer.parseInt(string);
        int secondNum = Integer.parseInt(string2);
        scenarioContext.put("firstNum", firstNum);
        scenarioContext.put("secondNum", secondNum);
    }

    @When("we add them together")
    public void we_add_them_together() {
        int result = Mock.addNums((int) scenarioContext.get("firstNum"), (int) scenarioContext.get("secondNum"));
        scenarioContext.put("result", result);
    }

    @Then("it should equal {string}, hopefully")
    public void it_should_equal_hopefully(String string) {
        int result = (int) scenarioContext.get("result");
        System.out.println(result);
        assertEquals(string, result);
    }
}

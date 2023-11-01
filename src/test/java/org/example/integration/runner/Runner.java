package org.example.integration.runner;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.platform.engine.Cucumber;

@Cucumber
@CucumberOptions(
        features = "src/test/resources/features"
        // , glue = "path.to.your.step.definitions" // only if you need to specify a package other than the default
)
public class Runner {
}
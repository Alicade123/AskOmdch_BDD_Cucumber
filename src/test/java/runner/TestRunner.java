package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/cart_and_checkout",
        glue = {
                "step.definitions.cart_and_checkout"
                },
        plugin = {
                "pretty",
                "html:target/cucumber-report.html",
                "json:target/cucumber-reports/Cucumber.json"},
        monochrome = true
)
public class TestRunner {}


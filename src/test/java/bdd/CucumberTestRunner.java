package bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "bdd.steps",
        plugin = {"pretty", "html:target/cucumber-reports"}
)
@SpringBootTest
public class CucumberTestRunner {
}

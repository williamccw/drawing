package bdd

import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
    features = ["src/test/resources/cucumber/features"],
    plugin = ["pretty", "html:target/cucumber-report.html"],
    glue = ["bdd.StepDefs"]
)
class CucumberTest {

}
import io.cucumber.java8.En
import io.cucumber.java8.PendingException

class StepDefs: En {
    init {
        Given("barabrbarbar") {
            throw PendingException()
        }

        When("barabrbarbar") {
            throw PendingException()
        }

        Then("xxxdsfsdfs") {
            throw PendingException()
        }
    }
}
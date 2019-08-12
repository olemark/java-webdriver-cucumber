package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.ConverterPage;

import static org.assertj.core.api.Assertions.assertThat;

public class ConverterPageStepDefs {

    @Given("^I go to converter webpage$")
    public void iGoToConverterWebpage() {
        new ConverterPage().open();
    }

    @When("^I choose and click on \"([^\"]*)\"$")
    public void iChooseAndClickOn(String tab) throws Throwable {
        ConverterPage page = new ConverterPage();
        page.clickTab(tab);

    }

    @And("^I set options \"([^\"]*)\" to \"([^\"]*)\"$")
    public void iSetOptionsTo(String tabFromValue, String tabToValue) throws Throwable {
        ConverterPage page = new ConverterPage();
        page.selectFrom(tabFromValue);
        page.selectTo(tabToValue);

    }

    @Then("^I enter value into From field \"([^\"]*)\"$")
    public void iEnterValueIntoFromField(String fromValue) throws Throwable {
        ConverterPage page = new ConverterPage();
        page.enterFromValue(fromValue);
        page.getWaitForToValue();

    }

    @And("^I verify \"([^\"]*)\" result$")
    public void iVerifyResult(String toValue) throws Throwable {
        ConverterPage page = new ConverterPage();
        assertThat(page.toValueResult()).contains(toValue);
        System.out.println("Value verified: " + toValue);

    }
}

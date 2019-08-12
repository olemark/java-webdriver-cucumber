package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.SampleForm;
import pages.SampleResult;
import support.TestContext;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static pages.Page.clickWithJS;

public class FormStepDefs {
    @Given("^I open sample page$")
    public void iOpenSamplePage() {
        new SampleForm().open();



    }

    @When("^I fill out sample fields$")
    public void iFillOutSampleFields() throws Exception{
        SampleForm form = new SampleForm();
        HashMap<String,String> data = TestContext.getSample();  //declare variable 'data' to use data from.yml file

        form.fillUsername(data.get("username"));  //using keys from sample.yml file and variable "data"
        form.fillPassword(data.get("password"));
        assertThat(form.isConfirmPasswordDisabled()).isFalse(); // assertion that password is Disabled using boolean method "isConfirmPasswordDisabled()"
        form.fillConfirmPassword(data.get("password"));
        form.fillEmail(data.get("email"));
//        form.fillName(data.get("firstName"), data.get("lastName")); //using combined method "fillName"
        form.fillName(data.get("firstName"), data.get("middleName"), data.get("lastName")); //using combined method "fillName" with middle Name (polymorphizm)
        form.fillContactNameAndPhone(data.get("contactName"), data.get("contactPhone"));
        form.checkAgreedToPrivacyPolicy();



    }

    @And("^I submit the sample form$")
    public void iSubmitTheSampleForm() {
        SampleForm form = new SampleForm();
        form.clickSubmit();

    }

    @Then("^I verify all fields$")
    public void iVerifyAllFields() throws Exception {
        SampleResult result = new SampleResult();
        HashMap<String,String> data = TestContext.getSample();  //declare variable 'data' to use data from.yml file

        String resultText = result.getResult();

        assertThat(result.displayedLegendResult()).isTrue();
        assertThat(resultText).containsIgnoringCase(data.get("username"));
        assertThat(resultText).containsIgnoringCase(data.get("email"));
        assertThat(resultText).containsIgnoringCase(data.get("firstName"));
        assertThat(resultText).containsIgnoringCase(data.get("middleName"));
        assertThat(resultText).containsIgnoringCase(data.get("lastName"));
        assertThat(resultText).containsIgnoringCase(data.get("contactName"));
        assertThat(resultText).containsIgnoringCase(data.get("contactPhone"));

        assertThat(result.getAgreedToPrivacyPolicyResult()).isEqualTo("true");
        assertThat(result.getPasswordResult()).doesNotContain(data.get("password"));
        assertThat(result.getPasswordResult()).isEqualTo("[entered]");


    }
}
